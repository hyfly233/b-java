package com.hyfly.tf.generate.entity.request.h3c

import com.alibaba.fastjson2.annotation.JSONField

data class H3cComputeVolumeAttachReq(

    @JSONField(name = "tenant_id")
    var tenantId: String? = null,

    @JSONField(name = "volume_id")
    var volumeId: String? = null,

    @JSONField(name = "instance_id")
    var instanceId: String? = null,
)
