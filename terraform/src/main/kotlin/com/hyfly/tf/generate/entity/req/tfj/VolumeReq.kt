package com.hyfly.tf.generate.entity.req.tfj

import com.alibaba.fastjson2.annotation.JSONField

data class VolumeReq(

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

    // metadata

    @JSONField(name = "user_name")
    var userName: String? = null,

    @JSONField(name = "cinder_zone_alias")
    var cinderZoneAlias: String? = null,

    @JSONField(name = "cinder_zone_alias_uuid")
    var cinderZoneAliasUuid: String? = null,

    @JSONField(name = "storage_type_name")
    var storageTypeName: String? = null,

    @JSONField(name = "storage_type_id")
    var storageTypeId: String? = null,

    @JSONField(name = "storage_pool_name")
    var storagePoolName: String? = null,

    @JSONField(name = "volume_format")
    var volumeFormat: String? = null

)
