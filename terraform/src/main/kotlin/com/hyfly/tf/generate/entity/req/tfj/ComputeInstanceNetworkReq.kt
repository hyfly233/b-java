package com.hyfly.tf.generate.entity.req.tfj

import com.alibaba.fastjson2.annotation.JSONField

data class ComputeInstanceNetworkReq(

    @JSONField(name = "uuid")
    var uuid: String? = null,

    @JSONField(name = "enable_gateway")
    var enableGateway: Boolean? = null,

    @JSONField(name = "fixed_ip")
    var fixedIp: String? = null,
)
