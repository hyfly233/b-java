package com.hyfly.tf.generate.service

import com.alibaba.fastjson2.JSONObject
import com.hyfly.tf.generate.entity.request.ResourceReq
import com.hyfly.tf.generate.entity.request.h3c.H3cComputeInstanceReq
import com.hyfly.tf.generate.entity.request.h3c.H3cVolumeReq

interface IH3cTfHandleService {

    /**
     * 处理H3C计算实例参数
     *
     * @param param H3C计算实例参数
     * @return 处理后的参数
     */
    fun handleParamsH3cComputeInstance(param: H3cComputeInstanceReq): JSONObject

    /**
     * 处理H3C安全组参数
     *
     * @param jsonObject H3C安全组参数
     * @return 处理后的参数
     */
    fun handleParamsH3cSecurityGroup(jsonObject: JSONObject): JSONObject

    /**
     * 处理H3C卷参数
     *
     * @param param H3C卷参数
     * @return 处理后的参数
     */
    fun handleParamsH3cVolume(param: H3cVolumeReq): JSONObject

    /**
     * 处理H3C计算实例接口挂载卷参数
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
     * 处理H3C计算实例接口绑定安全组
     *
     * @param sourceResource 源资源
     * @param targetType 目标类型
     * @param targetAlias 目标别名
     * @return 处理后的参数
     */
    fun handleComputeSecurityGroupAttach(
        sourceResource: ResourceReq,
        targetType: String,
        targetAlias: String
    ): JSONObject?
}
