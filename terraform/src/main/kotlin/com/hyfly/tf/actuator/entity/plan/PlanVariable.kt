package com.hyfly.tf.actuator.entity.plan

import com.alibaba.fastjson2.annotation.JSONField

/**
 * github.com/hashicorp/terraform-json
 *
 * type PlanVariable struct {
 * // The value for this variable at plan time.
 * Value interface{} `json:"value,omitempty"`
 * }
 */
class PlanVariable {
    @JSONField(name = "value")
    var value: Any? = null
}
