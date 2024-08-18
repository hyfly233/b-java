package com.hyfly.tf.actuator.entity.plan

import com.alibaba.fastjson2.annotation.JSONField

/**
 * github.com/hashicorp/terraform-json
 *
 *
 * // ProviderConfig describes a provider configuration instance.
 * type ProviderConfig struct {
 * // The name of the provider, ie: "aws".
 * Name string `json:"name,omitempty"`
 *
 *
 * // The fully-specified name of the provider, ie: "registry.terraform.io/hashicorp/aws".
 * FullName string `json:"full_name,omitempty"`
 *
 *
 * // The alias of the provider, ie: "us-east-1".
 * Alias string `json:"alias,omitempty"`
 *
 *
 * // The address of the module the provider is declared in.
 * ModuleAddress string `json:"module_address,omitempty"`
 *
 *
 * // Any non-special configuration values in the provider, indexed by
 * // key.
 * Expressions map[string]*Expression `json:"expressions,omitempty"`
 *
 *
 * // The defined version constraint for this provider.
 * VersionConstraint string `json:"version_constraint,omitempty"`
 * }
 */
class ProviderConfig {
    // The name of the provider, ie: "aws".
    @JSONField(name = "name")
    var name: String? = null

    // The fully-specified name of the provider, ie: "registry.terraform.io/hashicorp/aws".
    @JSONField(name = "full_name")
    var fullName: String? = null

    // The alias of the provider, ie: "us-east-1".
    @JSONField(name = "alias")
    var alias: String? = null

    // The address of the module the provider is declared in.
    @JSONField(name = "module_address")
    var moduleAddress: String? = null

    // Any non-special configuration values in the provider, indexed by
    // key.
    @JSONField(name = "expressions")
    var expressions: MutableMap<String, Expression>? = null

    // The defined version constraint for this provider.
    var versionConstraint: String? = null
}
