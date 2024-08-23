package com.hyfly.tf.actuator.entity.plan

import com.alibaba.fastjson2.annotation.JSONField

/**
 * github.com/hashicorp/terraform-json
 *
 *
 * // StateOutput represents an output value in a common state
 * // representation.
 * type StateOutput struct {
 * // Whether or not the output was marked as sensitive.
 * Sensitive bool `json:"sensitive"`
 *
 *
 * // The value of the output.
 * Value interface{} `json:"value,omitempty"`
 *
 *
 * // The type of the output.
 * Type cty.Type `json:"type,omitempty"`
 * }
 */
class StateOutput {
    // Whether or not the output was marked as sensitive.
    @JSONField(name = "sensitive")
    var sensitive = false

    // The value of the output.
    @JSONField(name = "value")
    var value: Any? = null

    // The type of the output.
    @JSONField(name = "type")
    var type: String? = null
}
