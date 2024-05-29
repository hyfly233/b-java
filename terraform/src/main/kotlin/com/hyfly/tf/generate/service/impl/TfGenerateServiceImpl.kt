package com.hyfly.tf.generate.service.impl

import com.alibaba.fastjson2.JSON
import com.alibaba.fastjson2.JSONObject
import com.hyfly.tf.generate.entity.bo.TfGenerateBo
import com.hyfly.tf.generate.entity.enums.H3cTfRelationEnum
import com.hyfly.tf.generate.entity.enums.H3cTfType2ClazzEnum
import com.hyfly.tf.generate.entity.enums.ProviderTypeEnum
import com.hyfly.tf.generate.entity.request.*
import com.hyfly.tf.generate.entity.request.h3c.H3cComputeInstanceReq
import com.hyfly.tf.generate.entity.request.h3c.H3cComputeVolumeAttachReq
import com.hyfly.tf.generate.entity.request.h3c.H3cSecurityGroupReq
import com.hyfly.tf.generate.entity.request.h3c.H3cVolumeReq
import com.hyfly.tf.generate.service.IH3cBoValidateService
import com.hyfly.tf.generate.service.IH3cTfHandleService
import com.hyfly.tf.generate.service.ITfGenerateService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class TfGenerateServiceImpl : ITfGenerateService {

    @Autowired
    private lateinit var h3cTfHandle: IH3cTfHandleService

    @Lazy
    @Autowired
    private lateinit var boValidateService: IH3cBoValidateService

    override fun generateTfJson(req: TfGenerateReq): String? {

        val producerType = req.producerType
        val tenantId = req.tenantId
        val resources = req.resources
        val relations = req.relations
        val variables = req.variables
        val outputs = req.outputs

        var processedResourceMap: MutableMap<String, Any>? = null

        // 处理资源
        this.handleTfResources(producerType, resources)?.let {
            processedResourceMap = HashMap(it)
            processedResourceMap?.putAll(it)
        }

        // 处理关联关系
        this.handleTfRelations(producerType, tenantId, resources, relations)?.let {
            if (processedResourceMap == null) {
                processedResourceMap = HashMap(it)
            } else {
                processedResourceMap?.putAll(it)
            }
        }

        val tfGenerateBo = TfGenerateBo()
        // 设置 resource
        processedResourceMap?.let {
            tfGenerateBo.resource = it
        }

        // 处理变量
        this.handleTfVariables(variables)?.let {
            tfGenerateBo.variable = it
        }

        // 处理输出
        this.handleTfOutputs(outputs)?.let {
            tfGenerateBo.output = it
        }

        return JSON.toJSONString(tfGenerateBo)
    }

    /**
     * 处理资源，如：openstack_compute_instance_v2、openstack_compute_volume_attach_v2
     *
     * @param producerType 云厂商类型
     * @param resources 资源列表
     * @return 处理后的资源
     */
    private fun handleTfResources(
        producerType: String?,
        resources: Set<ResourceReq>?,
    ): Map<String, Any>? {
        if (producerType != null && resources != null) {
            val processedResourceSet = HashSet<ResourceReq>()

            for (resource in resources) {
                val resourceType = resource.type
                val resourceAlias = resource.alias
                val resourceParams = resource.params

                if (resourceType != null && resourceAlias != null && resourceParams != null) {
                    val resourceClass = H3cTfType2ClazzEnum.getResourceClass(resourceType)
                    val paramsClazz = JSON.to(resourceClass, resourceParams)

                    var processedParams: JSONObject? = null

                    if (ProviderTypeEnum.H3C.type == producerType) {
                        if (paramsClazz is H3cComputeInstanceReq) {
                            processedParams = h3cTfHandle.handleParamsH3cComputeInstance(paramsClazz)
                        } else if (paramsClazz is H3cSecurityGroupReq) {
                            processedParams = h3cTfHandle.handleParamsH3cSecurityGroup(resourceParams)
                        } else if (paramsClazz is H3cVolumeReq) {
                            processedParams = h3cTfHandle.handleParamsH3cVolume(paramsClazz)
                        }
                    } else if (ProviderTypeEnum.HUAWEICLOUD.type == producerType) {
                        throw IllegalArgumentException("暂不支持华为云")
                    }

                    if (processedParams == null) {
                        continue
                    }

                    // 替换原有的 params
                    resource.params = processedParams

                    processedResourceSet.add(resource)
                }
            }

            if (processedResourceSet.isNotEmpty()) {
                // 处理 resources
                val type2ResourceMap = HashMap<String, Any>()
                HashMap(processedResourceSet
                    .filter { it.type != null }
                    .groupBy { it.type!! }
                ).forEach { (type, resourceVoList) ->
                    val alias2ResourceMap = HashMap<String, Any>()
                    resourceVoList
                        .filter { it.alias != null }
                        .forEach {
                            alias2ResourceMap[it.alias!!] = it.params!!
                        }
                    type2ResourceMap[type] = alias2ResourceMap
                }

                return type2ResourceMap
            }
        }

        return null
    }

    /**
     * 处理关联关系
     * eg1: 云主机与云硬盘的绑定关系，需要转换为 openstack_compute_volume_attach_v2
     * eg2: 云主机与安全组的绑定关系，需要将安全组的 id 传入云主机的 security_groups 参数中
     *
     * @param producerType 云厂商类型
     * @param resources 资源列表
     * @param relations 关联关系列表
     */
    private fun handleTfRelations(
        producerType: String?,
        tenantId: String?,
        resources: Set<ResourceReq>?,
        relations: Set<RelationReq>?
    ): Map<String, Any>? {
        var type2ResourceMap: Map<String, Any>? = null

        if (producerType != null && resources != null && relations != null) {
            val processedResourceSet = HashSet<ResourceReq>()

            val alias2ResourceMap = resources.associateBy({ it.alias }, { it })

            for (relation in relations) {
                val sourceAlias = relation.source
                val targetAlias = relation.target

                if (sourceAlias == null || targetAlias == null) {
                    continue
                }

                val sourceResource = alias2ResourceMap[sourceAlias]
                val targetResource = alias2ResourceMap[targetAlias]

                if (sourceResource == null || targetResource == null) {
                    continue
                }
                // 获取 source 和 target 的 type
                val sourceType = sourceResource.type
                val targetType = targetResource.type

                if (sourceType == null || targetType == null) {
                    continue
                }

                // 通过 type 获取对应的 clazz
                val relationEnum = H3cTfRelationEnum.getRelationClass(sourceType, targetType)

                var relationResourceType: String? = null
                var processedParams: JSONObject? = null

                // 通过 clazz 获取对应的关联关系类
                if (ProviderTypeEnum.H3C.type == producerType) {
                    if (tenantId == null) {
                        throw IllegalArgumentException("h3c 类型的资源 tenantId 不能为空")
                    }

                    if (relationEnum.isResource) {
                        // eg1: 云主机与云硬盘的绑定关系，需要转换为 openstack_compute_volume_attach_v2
                        relationResourceType = H3cTfType2ClazzEnum.getResourceType(relationEnum.clazz!!)

                        if (relationEnum.clazz == H3cComputeVolumeAttachReq::class.java) {
                            // 给关联关系设值
                            processedParams = h3cTfHandle.handleParamsComputeVolumeAttach(
                                tenantId,
                                targetType,
                                sourceType,
                                targetAlias,
                                sourceAlias
                            )
                        }
                    } else {
                        // eg2: 云主机与安全组的绑定关系，需要将安全组的 id 传入云主机的 security_groups 参数中
                        if (relationEnum == H3cTfRelationEnum.COMPUTE_SECURITY_GROUP_ATTACH) {
                            processedParams =
                                h3cTfHandle.handleComputeSecurityGroupAttach(sourceResource, targetType, targetAlias)

                            // 替换原有的 params
                            sourceResource.params = processedParams
                        }
                    }
                } else if (ProviderTypeEnum.HUAWEICLOUD.type == producerType) {
                    throw IllegalArgumentException("暂不支持华为云")
                }

                if (relationResourceType == null || processedParams == null) {
                    continue
                }

                processedResourceSet.add(
                    ResourceReq(
                        relationResourceType,
                        randomGenerateAlias(H3cTfType2ClazzEnum.getAbbreviation(relationResourceType)),
                        processedParams
                    )
                )

            }

            // 将关联关系放入 type2ResourceMap
            if (processedResourceSet.isNotEmpty()) {
                type2ResourceMap = HashMap()
                HashMap(processedResourceSet
                    .filter { it.type != null }
                    .groupBy { it.type!! }
                ).forEach { (type, resourceVoList) ->
                    val alias2RelationResourceMap = HashMap<String, Any>()
                    resourceVoList
                        .filter { it.alias != null }
                        .forEach {
                            alias2RelationResourceMap[it.alias!!] = it.params!!
                        }
                    type2ResourceMap[type] = alias2RelationResourceMap
                }
            }
        }

        return type2ResourceMap
    }

    /**
     * 处理tf变量
     *
     * @param variableMap 变量map
     * @return 处理后的变量map
     */
    private fun handleTfVariables(variableMap: Map<String, VariableReq>?): Map<String, Any>? {
        return variableMap
    }

    /**
     * 处理tf输出
     *
     * @param outputs 输出map
     * @return 处理后的输出map
     */
    private fun handleTfOutputs(outputs: Map<String, OutputReq>?): Map<String, Any>? {

        if (!outputs.isNullOrEmpty()) {
            boValidateService.validateOutputs(outputs)
        }

        return outputs
    }

    /**
     * 随机生成资源别名
     *
     * @param type 资源类型
     * @return 资源别名
     */
    private fun randomGenerateAlias(type: String): String {
        val randomString = (1..5)
            .map { Random.nextInt(0, 26) }  // Generate a random integer between 0 and 25.
            .map { it + 'a'.code }  // Convert it to an ASCII value of a lowercase letter.
            .map { it.toChar() }  // Convert the ASCII value to a char.
            .joinToString("")  // Join all chars into a string.
        return "$type-$randomString"
    }
}
