package com.hyfly.tf.actuator.entity.plan

import com.alibaba.fastjson2.annotation.JSONField

/**
 * github.com/hashicorp/terraform-json
 *
 *
 * // ConfigModule describes a module in Terraform configuration.
 * type ConfigModule struct {
 * // The outputs defined in the module.
 * Outputs map[string]*ConfigOutput `json:"outputs,omitempty"`
 *
 *
 * // The resources defined in the module.
 * Resources []*ConfigResource `json:"resources,omitempty"`
 *
 *
 * // Any "module" stanzas within the specific module.
 * ModuleCalls map[string]*ModuleCall `json:"module_calls,omitempty"`
 *
 *
 * // The variables defined in the module.
 * Variables map[string]*ConfigVariable `json:"variables,omitempty"`
 * }
 */
class ConfigModule {
    // The outputs defined in the module.
    @JSONField(name = "outputs")
    var outputs: MutableMap<String, Any>? = null

    // The resources defined in the module.
    @JSONField(name = "resources")
    var resources: MutableList<Any>? = null

    // The variables defined in the module.
    @JSONField(name = "variables")
    var variables: MutableMap<String, ConfigVariable>? = null
}
