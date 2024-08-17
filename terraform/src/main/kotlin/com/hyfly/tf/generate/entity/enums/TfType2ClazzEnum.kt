package com.hyfly.tf.generate.entity.enums

import com.hyfly.tf.generate.entity.req.tfj.*

enum class TfType2ClazzEnum(val type: String, val abbreviation: String, val clazz: Class<*>) {
    COMPUTE_INSTANCE("compute_instance", "hci", ComputeInstanceReq::class.java),
    COMPUTE_INTERFACE_ATTACH("compute_interface_attach", "hcia", ComputeInterfaceAttachReq::class.java),
    COMPUTE_VOLUME_ATTACH("compute_volume_attach", "hcva", ComputeVolumeAttachReq::class.java),
    SECURITY_GROUP("security_group", "hsg", SecurityGroupReq::class.java),
    VOLUME("volume", "hv", VolumeReq::class.java);

    companion object {
        fun getResourceType(clazz: Class<*>): String {
            for (value in entries) {
                if (value.clazz == clazz) {
                    return value.type
                }
            }
            throw IllegalArgumentException("Unsupported resource class: $clazz")
        }

        fun getResourceClass(type: String): Class<*> {
            for (value in entries) {
                if (value.type == type) {
                    return value.clazz
                }
            }
            throw IllegalArgumentException("Unsupported resource type: $type")
        }

        fun getAbbreviation(type: String): String {
            for (value in entries) {
                if (value.type == type) {
                    return value.abbreviation
                }
            }
            throw IllegalArgumentException("Unsupported resource type: $type")
        }
    }
}
