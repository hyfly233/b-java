package com.hyfly.tf.actuator.entity.plan

import com.alibaba.fastjson2.annotation.JSONField

/**
 * github.com/hashicorp/terraform-json
 *
 *
 * // ResourceChange is a description of an individual change action
 * // that Terraform plans to use to move from the prior state to a new
 * // state matching the configuration.
 * type ResourceChange struct {
 * // The absolute resource address.
 * Address string `json:"address,omitempty"`
 *
 *
 * // The absolute address that this resource instance had
 * // at the conclusion of a previous plan.
 * PreviousAddress string `json:"previous_address,omitempty"`
 *
 *
 * // The module portion of the above address. Omitted if the instance
 * // is in the root module.
 * ModuleAddress string `json:"module_address,omitempty"`
 *
 *
 * // The resource mode.
 * Mode ResourceMode `json:"mode,omitempty"`
 *
 *
 * // The resource type, example: "aws_instance" for aws_instance.foo.
 * Type string `json:"type,omitempty"`
 *
 *
 * // The resource name, example: "foo" for aws_instance.foo.
 * Name string `json:"name,omitempty"`
 *
 *
 * // The instance key for any resources that have been created using
 * // "count" or "for_each". If neither of these apply the key will be
 * // empty.
 * //
 * // This value can be either an integer (int) or a string.
 * Index interface{} `json:"index,omitempty"`
 *
 *
 * // The name of the provider this resource belongs to. This allows
 * // the provider to be interpreted unambiguously in the unusual
 * // situation where a provider offers a resource type whose name
 * // does not start with its own name, such as the "googlebeta"
 * // provider offering "google_compute_instance".
 * ProviderName string `json:"provider_name,omitempty"`
 *
 *
 * // An identifier used during replacement operations, and can be
 * // used to identify the exact resource being replaced in state.
 * DeposedKey string `json:"deposed,omitempty"`
 *
 *
 * // The data describing the change that will be made to this object.
 * Change *Change `json:"change,omitempty"`
 * }
 */
class ResourceChange {
    // The absolute resource address.
    @JSONField(name = "address")
    var address: String? = null

    // The absolute address that this resource instance had
    // at the conclusion of a previous plan.
    @JSONField(name = "previous_address")
    var previousAddress: String? = null

    // The module portion of the above address. Omitted if the instance
    // is in the root module.
    @JSONField(name = "module_address")
    var moduleAddress: String? = null

    /**
     * The resource mode. todo link "data" "managed"
     */
    @JSONField(name = "mode")
    var mode: String? = null

    // The resource type, example: "aws_instance" for aws_instance.foo.
    @JSONField(name = "type")
    var type: String? = null

    // The resource name, example: "foo" for aws_instance.foo.
    @JSONField(name = "name")
    var name: String? = null

    // The instance key for any resources that have been created using
    // "count" or "for_each". If neither of these apply the key will be
    // empty.
    //
    // This value can be either an integer (int) or a string.
    @JSONField(name = "index")
    var index: Any? = null

    // The name of the provider this resource belongs to. This allows
    // the provider to be interpreted unambiguously in the unusual
    // situation where a provider offers a resource type whose name
    // does not start with its own name, such as the "googlebeta"
    // provider offering "google_compute_instance".
    @JSONField(name = "provider_name")
    var providerName: String? = null

    // An identifier used during replacement operations, and can be
    // used to identify the exact resource being replaced in state.
    @JSONField(name = "deposed")
    var deposedKey: String? = null

    // The data describing the change that will be made to this object.
    @JSONField(name = "change")
    var change: Change? = null
}
