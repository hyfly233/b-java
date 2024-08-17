package com.hyfly.tf.generate.entity.bo.tfj

import com.alibaba.fastjson2.annotation.JSONField

data class VolumeBo(

    @JSONField(name = "tenant_id")
    var tenantId: String? = null,

    @JSONField(name = "name")
    var name: String? = null,

    @JSONField(name = "description")
    var description: String? = null,

    @JSONField(name = "size")
    var size: Int? = null,

    @JSONField(name = "multi_attach")
    var multiAttach: Boolean? = null,

    @JSONField(name = "cinder_zone_name")
    var cinderZoneName: String? = null,

    @JSONField(name = "volume_type")
    var volumeType: String? = null,

    @JSONField(name = "metadata")
    var metadata: VolumeMetadataBo? = null

)
