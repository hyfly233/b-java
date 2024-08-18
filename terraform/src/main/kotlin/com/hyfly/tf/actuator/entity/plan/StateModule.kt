package com.hyfly.tf.actuator.entity.plan

import com.alibaba.fastjson2.annotation.JSONField

/**
 * github.com/hashicorp/terraform-json
 * <p>
 * // StateModule is the representation of a module in the common state
 * // representation. This can be the root module or a child module.
 * type StateModule struct {
 * // All resources or data sources within this module.
 * Resources []*StateResource `json:"resources,omitempty"`
 * <p>
 * // The absolute module address, omitted for the root module.
 * Address string `json:"address,omitempty"`
 * <p>
 * // Any child modules within this module.
 * ChildModules []*StateModule `json:"child_modules,omitempty"`
 * }
 */
class StateModule {
    // All resources or data sources within this module.
    @JSONField(name = "resources")
    var resources: MutableList<StateResource>? = null

    // The absolute module address, omitted for the root module.
    @JSONField(name = "address")
    var address: String? = null

    // Any child modules within this module.
    @JSONField(name = "child_modules")
    var childModules: MutableList<StateModule>? = null
}
