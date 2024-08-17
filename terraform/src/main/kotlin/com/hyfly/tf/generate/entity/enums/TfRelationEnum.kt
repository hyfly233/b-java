package com.hyfly.tf.generate.entity.enums

import com.hyfly.tf.generate.entity.req.tfj.ComputeInterfaceAttachReq
import com.hyfly.tf.generate.entity.req.tfj.ComputeVolumeAttachReq

enum class TfRelationEnum(val source: String, val target: String, val isResource: Boolean, val clazz: Class<*>?) {
    COMPUTE_VOLUME_ATTACH(
        "compute_instance", "volume", true,
        ComputeVolumeAttachReq::class.java
    ),
    COMPUTE_INTERFACE_ATTACH(
        "compute_instance", "network_interface", true,
        ComputeInterfaceAttachReq::class.java
    ),
    COMPUTE_SECURITY_GROUP_ATTACH(
        "compute_instance", "security_group", false,
        null
    )
    ;

    companion object {
        fun getRelationClass(source: String, target: String): TfRelationEnum {
            for (value in entries) {
                if (value.source == source && value.target == target) {
                    return value
                }
            }
            throw IllegalArgumentException("Unsupported relation type: $source, $target")
        }
    }
}
