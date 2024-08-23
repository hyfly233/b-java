package com.hyfly.tf.actuator.entity.plan

import com.alibaba.fastjson2.annotation.JSONField

/**
 * github.com/hashicorp/terraform-json
 *
 * // ConfigVariable defines a variable as defined in configuration.
 * type ConfigVariable struct {
 * // The defined default value of the variable.
 * Default interface{} `json:"default,omitempty"`
 *
 * // The defined text description of the variable.
 * Description string `json:"description,omitempty"`
 *
 * // Whether the variable is marked as sensitive
 * Sensitive bool `json:"sensitive,omitempty"`
 * }
 */
class ConfigVariable {
    // The defined default value of the variable.
    @JSONField(name = "default")
    var defaultValue: Any? = null

    // The defined text description of the variable.
    @JSONField(name = "description")
    var description: String? = null

    // Whether the variable is marked as sensitive
    @JSONField(name = "sensitive")
    var sensitive = false
}
