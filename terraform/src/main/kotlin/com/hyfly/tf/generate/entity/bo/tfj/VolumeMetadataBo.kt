package com.hyfly.tf.generate.entity.bo.tfj

import com.alibaba.fastjson2.annotation.JSONField

data class VolumeMetadataBo(

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
