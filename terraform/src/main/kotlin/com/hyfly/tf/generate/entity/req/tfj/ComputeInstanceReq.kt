package com.hyfly.tf.generate.entity.req.tfj

import com.alibaba.fastjson2.annotation.JSONField

data class ComputeInstanceReq(

    @JSONField(name = "name")
    var name: String? = null,

    @JSONField(name = "alias")
    var alias: String? = null,

    @JSONField(name = "description")
    var description: String? = null,

    @JSONField(name = "tenant_id")
    var tenantId: String? = null,

    @JSONField(name = "user_name")
    var userName: String? = null,

    @JSONField(name = "flavor_id")
    var flavorId: String? = null,

    @JSONField(name = "image_id")
    var imageId: String? = null,

    @JSONField(name = "enable_admin_pass")
    var enableAdminPass: String? = null,

    @JSONField(name = "admin_pass")
    var adminPass: String? = null,

    @JSONField(name = "nova_zone_name")
    var novaZoneName: String? = null,

    @JSONField(name = "nova_zone_alias_uuid")
    var novaZoneAliasUuid: String? = null,

    @JSONField(name = "cinder_zone_name")
    var cinderZoneName: String? = null,

    @JSONField(name = "cinder_zone_alias_uuid")
    var cinderZoneAliasUuid: String? = null,

    @JSONField(name = "storage_pool_name")
    var storagePoolName: String? = null,

    @JSONField(name = "volume_type")
    var volumeType: String? = null,

    @JSONField(name = "volume_format")
    var volumeFormat: String? = null,

    @JSONField(name = "storage_type_name")
    var storageTypeName: String? = null,

    @JSONField(name = "system_disk_size")
    var systemDiskSize: Int? = null,

    @JSONField(name = "security_groups")
    var securityGroups: List<String>? = null,

    @JSONField(name = "networks")
    var networks: List<ComputeInstanceNetworkReq>? = null,
)
