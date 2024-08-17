package com.hyfly.tf.generate.entity.req.tfj

import com.alibaba.fastjson2.annotation.JSONField

data class ComputeInterfaceAttachReq(

    @JSONField(name = "tenant_id")
    var tenantId: String? = null,

    @JSONField(name = "interface_id")
    var interfaceId: String? = null,

    @JSONField(name = "instance_id")
    var instanceId: String? = null,
)
