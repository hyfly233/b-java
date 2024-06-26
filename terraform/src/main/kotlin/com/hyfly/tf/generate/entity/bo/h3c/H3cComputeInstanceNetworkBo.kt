package com.hyfly.tf.generate.entity.bo.h3c

import com.alibaba.fastjson2.annotation.JSONField
import com.hyfly.tf.generate.entity.request.h3c.H3cComputeInstanceNetworkReq

data class H3cComputeInstanceNetworkBo(

    @JSONField(name = "uuid")
    var uuid: String? = null,

    @JSONField(name = "enable_gateway")
    var enableGateway: Boolean? = null,

    @JSONField(name = "fixed_ip")
    var fixedIp: String? = null,
) {
    fun fromReq(req: H3cComputeInstanceNetworkReq): H3cComputeInstanceNetworkBo {
        this.uuid = req.uuid
        this.enableGateway = req.enableGateway
        this.fixedIp = req.fixedIp

        return this
    }
}
