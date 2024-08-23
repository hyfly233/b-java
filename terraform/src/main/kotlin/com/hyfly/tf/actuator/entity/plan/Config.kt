package com.hyfly.tf.actuator.entity.plan

import com.alibaba.fastjson2.annotation.JSONField

/**
 * github.com/hashicorp/terraform-json
 * <p>
 * // Config represents the complete configuration source.
 * type Config struct {
 * // A map of all provider instances across all modules in the
 * // configuration.
 * //
 * // The index for this field is opaque and should not be parsed. Use
 * // the individual fields in ProviderConfig to discern actual data
 * // about the provider such as name, alias, or defined module.
 * ProviderConfigs map[string]*ProviderConfig `json:"provider_config,omitempty"`
 * <p>
 * // The root module in the configuration. Any child modules descend
 * // off of here.
 * RootModule *ConfigModule `json:"root_module,omitempty"`
 * }
 */
class Config {
    // A map of all provider instances across all modules in the
    // configuration.
    //
    // The index for this field is opaque and should not be parsed. Use
    // the individual fields in ProviderConfig to discern actual data
    // about the provider such as name, alias, or defined module.
    @JSONField(name = "provider_config")
    var providerConfigs: MutableMap<String, ProviderConfig>? = null

    // The root module in the configuration. Any child modules descend
    // off of here.
    @JSONField(name = "root_module")
    var rootModule: ConfigModule? = null
}
