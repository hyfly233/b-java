package com.hyfly.tf.generate.entity.bo.tfj

import com.alibaba.fastjson2.JSON
import com.alibaba.fastjson2.annotation.JSONField
import com.hyfly.tf.generate.entity.req.tfj.ComputeInstanceNetworkReq
import com.hyfly.tf.generate.entity.req.tfj.ComputeInstanceReq

data class ComputeInstanceBo(

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

    @JSONField(name = "flavor_ref")
    var flavorRef: String? = null,

    @JSONField(name = "image_ref")
    var imageRef: String? = null,

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
    var networks: List<ComputeInstanceNetworkBo>? = null,
) {
    /**
     * 将请求对象转换为业务对象
     *
     * @param req 请求对象
     * @return 业务对象
     */
    fun fromReq(req: ComputeInstanceReq): ComputeInstanceBo {
        this.name = req.name
        this.alias = req.alias
        this.description = req.description
        this.tenantId = req.tenantId
        this.userName = req.userName
        this.flavorRef = req.flavorId
        this.imageRef = req.imageId
        this.enableAdminPass = req.enableAdminPass
        this.adminPass = req.adminPass
        this.novaZoneName = req.novaZoneName
        this.novaZoneAliasUuid = req.novaZoneAliasUuid
        this.cinderZoneName = req.cinderZoneName
        this.cinderZoneAliasUuid = req.cinderZoneAliasUuid
        this.storagePoolName = req.storagePoolName
        this.volumeType = req.volumeType
        this.volumeFormat = req.volumeFormat
        this.storageTypeName = req.storageTypeName
        this.systemDiskSize = req.systemDiskSize
        this.securityGroups = req.securityGroups

        req.networks?.let {
            val list = mutableListOf<ComputeInstanceNetworkBo>()

            val json = JSON.toJSONString(it)
            val reqList = JSON.parseArray(json, ComputeInstanceNetworkReq::class.java)

            for (networkReq in reqList) {
                val networkBo = ComputeInstanceNetworkBo()
                networkBo.fromReq(networkReq)
                list.add(networkBo)
            }
            this.networks = list
        }

        return this
    }
}


