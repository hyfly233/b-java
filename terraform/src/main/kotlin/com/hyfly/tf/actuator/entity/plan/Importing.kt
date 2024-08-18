package com.hyfly.tf.actuator.entity.plan

import com.alibaba.fastjson2.annotation.JSONField

/**
 * github.com/hashicorp/terraform-json
 *
 *
 * // Importing is a nested object for the resource import metadata.
 * type Importing struct {
 * // The original ID of this resource used to target it as part of planned
 * // import operation.
 * ID string `json:"id,omitempty"`
 * }
 */
class Importing {
    // The original ID of this resource used to target it as part of planned
    // import operation.
    @JSONField(name = "id")
    var id: String? = null
}
