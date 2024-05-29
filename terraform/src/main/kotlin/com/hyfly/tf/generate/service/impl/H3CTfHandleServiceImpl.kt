package com.hyfly.tf.generate.service.impl

import com.alibaba.fastjson2.JSON
import com.alibaba.fastjson2.JSONArray
import com.alibaba.fastjson2.JSONObject
import com.hyfly.tf.generate.entity.bo.h3c.H3cComputeInstanceBo
import com.hyfly.tf.generate.entity.bo.h3c.H3cVolumeBo
import com.hyfly.tf.generate.entity.bo.h3c.H3cVolumeMetadataBo
import com.hyfly.tf.generate.entity.request.ResourceReq
import com.hyfly.tf.generate.entity.request.h3c.H3cComputeInstanceReq
import com.hyfly.tf.generate.entity.request.h3c.H3cComputeVolumeAttachReq
import com.hyfly.tf.generate.entity.request.h3c.H3cVolumeReq
import com.hyfly.tf.generate.service.IH3cBoValidateService
import com.hyfly.tf.generate.service.IH3cTfHandleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class H3CTfHandleServiceImpl : IH3cTfHandleService {

    @Autowired
    private lateinit var boValidateService: IH3cBoValidateService

    override fun handleParamsH3cComputeInstance(param: H3cComputeInstanceReq): JSONObject {

        val bo = H3cComputeInstanceBo().fromReq(param)
        boValidateService.validateComputeInstance(bo)

        return JSON.parseObject(JSON.toJSONString(bo))
    }

    override fun handleParamsH3cSecurityGroup(jsonObject: JSONObject): JSONObject {

        // 将 jsonObject 中的 值为 null 的 key 移除
        val iterator = jsonObject.entries.iterator()
        while (iterator.hasNext()) {
            val entry = iterator.next()
            if (entry.value == null) {
                iterator.remove()
            }
        }

        // 判断 name user_id 是否为空
        if (jsonObject["name"] == null || jsonObject["user_id"] == null) {
            throw IllegalArgumentException("name or user_id is null")
        }

        return jsonObject
    }

    override fun handleParamsH3cVolume(param: H3cVolumeReq): JSONObject {
        val metadataBo = H3cVolumeMetadataBo(
            param.userName,
            param.cinderZoneAlias,
            param.cinderZoneAliasUuid,
            param.storageTypeName,
            param.storageTypeId,
            param.storagePoolName,
            param.volumeFormat
        )
        val volumeBo = H3cVolumeBo(
            param.tenantId,
            param.name,
            param.description,
            param.size,
            param.multiAttach,
            param.cinderZoneName,
            param.volumeType,
            metadataBo
        )
        return JSONObject.parseObject(JSON.toJSONString(volumeBo))
    }

    override fun handleParamsComputeVolumeAttach(
        tenantId: String,
        targetType: String,
        sourceType: String,
        targetAlias: String,
        sourceAlias: String
    ): JSONObject {
        // 给关联关系设值
        val relationObj = H3cComputeVolumeAttachReq(
            tenantId,
            this.getTemplateResourceId(targetType, targetAlias),
            this.getTemplateResourceId(sourceType, sourceAlias),
        )

        return JSON.parseObject(JSON.toJSONString(relationObj))
    }

    override fun handleComputeSecurityGroupAttach(
        sourceResource: ResourceReq,
        targetType: String,
        targetAlias: String
    ): JSONObject? {

        sourceResource.params?.let {
            val securityGroups = it["security_groups"]
            if (securityGroups is JSONArray) {
                securityGroups.add(this.getTemplateResourceId(targetType, targetAlias))
            }
        }

        return sourceResource.params
    }

    /**
     * 获取模板资源 id
     * eg: ${openstack_compute_instance_v2.instance1.id}
     *
     * @param resourceType 资源类型
     * @param alias 资源别名
     * @return 模板资源 id
     */
    private fun getTemplateResourceId(resourceType: String, alias: String): String {
        return "\${${resourceType}.${alias}.id}"
    }
}
