package com.hyfly.tf.generate.service

import com.alibaba.fastjson2.JSONObject
import com.hyfly.tf.generate.entity.req.tfj.ComputeInstanceReq
import com.hyfly.tf.generate.entity.req.TfResourceReq
import com.hyfly.tf.generate.entity.req.tfj.VolumeReq

interface ITfHandleService {

    /**
     * 处理计算实例参数
     *
     * @param param 计算实例参数
     * @return 处理后的参数
     */
    fun handleParamsComputeInstance(param: ComputeInstanceReq): JSONObject

    /**
     * 处理安全组参数
     *
     * @param jsonObject 安全组参数
     * @return 处理后的参数
     */
    fun handleParamsSecurityGroup(jsonObject: JSONObject): JSONObject

    /**
     * 处理卷参数
     *
     * @param param 卷参数
     * @return 处理后的参数
     */
    fun handleParamsVolume(param: VolumeReq): JSONObject

    /**
     * 处理计算实例接口挂载卷参数
     *
     * @param tenantId 租户id
     * @param targetType 目标类型
     * @param sourceType 源类型
     * @param targetAlias 目标别名
     * @param sourceAlias 源别名
     * @return 处理后的参数
     */
    fun handleParamsComputeVolumeAttach(
        tenantId: String,
        targetType: String,
        sourceType: String,
        targetAlias: String,
        sourceAlias: String
    ): JSONObject

    /**
     * 处理计算实例接口绑定安全组
     *
     * @param sourceResource 源资源
     * @param targetType 目标类型
     * @param targetAlias 目标别名
     * @return 处理后的参数
     */
    fun handleComputeSecurityGroupAttach(
        sourceResource: TfResourceReq,
        targetType: String,
        targetAlias: String
    ): JSONObject?
}
