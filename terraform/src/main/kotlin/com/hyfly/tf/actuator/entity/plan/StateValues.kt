package com.hyfly.tf.actuator.entity.plan

import com.alibaba.fastjson2.annotation.JSONField

/**
 * github.com/hashicorp/terraform-json
 * <p>
 * // StateValues is the common representation of resolved values for both the
 * // prior state (which is always complete) and the planned new state.
 * type StateValues struct {
 * // The Outputs for this common state representation.
 * Outputs map[string]*StateOutput `json:"outputs,omitempty"`
 * <p>
 * // The root module in this state representation.
 * RootModule *StateModule `json:"root_module,omitempty"`
 * }
 */
class StateValues {
    // The Outputs for this common state representation.
    @JSONField(name = "outputs")
    var outputs: MutableMap<String, StateOutput>? = null

    // The root module in this state representation.
    @JSONField(name = "root_module")
    var rootModule: StateModule? = null
}
