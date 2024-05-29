package com.hyfly.tf.generate.entity.enums

import com.hyfly.tf.generate.entity.request.h3c.H3cComputeInterfaceAttachReq
import com.hyfly.tf.generate.entity.request.h3c.H3cComputeVolumeAttachReq

enum class H3cTfRelationEnum(val source: String, val target: String, val isResource: Boolean, val clazz: Class<*>?) {
    COMPUTE_VOLUME_ATTACH(
        "h3c_compute_instance", "h3c_volume", true,
        H3cComputeVolumeAttachReq::class.java
    ),
    COMPUTE_INTERFACE_ATTACH(
        "h3c_compute_instance", "h3c_network_interface", true,
        H3cComputeInterfaceAttachReq::class.java
    ),
    COMPUTE_SECURITY_GROUP_ATTACH(
        "h3c_compute_instance", "h3c_security_group", false,
        null
    )
    ;

    companion object {
        fun getRelationClass(source: String, target: String): H3cTfRelationEnum {
            for (value in entries) {
                if (value.source == source && value.target == target) {
                    return value
                }
            }
            throw IllegalArgumentException("Unsupported relation type: $source, $target")
        }
    }
}
