package com.hyfly.tf.generate.entity.enums

import com.hyfly.tf.generate.entity.request.h3c.*

enum class H3cTfType2ClazzEnum(val type: String, val abbreviation: String, val clazz: Class<*>) {
    COMPUTE_INSTANCE("h3c_compute_instance", "hci", H3cComputeInstanceReq::class.java),
    COMPUTE_INTERFACE_ATTACH("h3c_compute_interface_attach", "hcia", H3cComputeInterfaceAttachReq::class.java),
    COMPUTE_VOLUME_ATTACH("h3c_compute_volume_attach", "hcva", H3cComputeVolumeAttachReq::class.java),
    SECURITY_GROUP("h3c_security_group", "hsg", H3cSecurityGroupReq::class.java),
    VOLUME("h3c_volume", "hv", H3cVolumeReq::class.java);

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
