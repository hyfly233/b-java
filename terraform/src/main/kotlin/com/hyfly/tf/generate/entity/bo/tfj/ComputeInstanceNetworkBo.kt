package com.hyfly.tf.generate.entity.bo.tfj

import com.alibaba.fastjson2.annotation.JSONField
import com.hyfly.tf.generate.entity.req.tfj.ComputeInstanceNetworkReq

data class ComputeInstanceNetworkBo(

    @JSONField(name = "uuid")
    var uuid: String? = null,

    @JSONField(name = "enable_gateway")
    var enableGateway: Boolean? = null,

    @JSONField(name = "fixed_ip")
    var fixedIp: String? = null,
) {
    fun fromReq(req: ComputeInstanceNetworkReq): ComputeInstanceNetworkBo {
        this.uuid = req.uuid
        this.enableGateway = req.enableGateway
        this.fixedIp = req.fixedIp

        return this
    }
}
