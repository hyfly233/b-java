package com.hyfly.tf.actuator.entity.plan

import com.alibaba.fastjson2.annotation.JSONField

/**
 * github.com/hashicorp/terraform-json
 *
 *
 * // StateResource is the representation of a resource in the common
 * // state representation.
 * type StateResource struct {
 * // The absolute resource address.
 * Address string `json:"address,omitempty"`
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
 * // The version of the resource type schema the "values" property
 * // conforms to.
 * SchemaVersion uint64 `json:"schema_version,"`
 *
 *
 * // The JSON representation of the attribute values of the resource,
 * // whose structure depends on the resource type schema. Any unknown
 * // values are omitted or set to null, making them indistinguishable
 * // from absent values.
 * AttributeValues map[string]interface{} `json:"values,omitempty"`
 *
 *
 * // The JSON representation of the sensitivity of the resource's
 * // attribute values. Only attributes which are sensitive
 * // are included in this structure.
 * SensitiveValues json.RawMessage `json:"sensitive_values,omitempty"`
 *
 *
 * // The addresses of the resources that this resource depends on.
 * DependsOn []string `json:"depends_on,omitempty"`
 *
 *
 * // If true, the resource has been marked as tainted and will be
 * // re-created on the next update.
 * Tainted bool `json:"tainted,omitempty"`
 *
 *
 * // DeposedKey is set if the resource instance has been marked Deposed and
 * // will be destroyed on the next apply.
 * DeposedKey string `json:"deposed_key,omitempty"`
 * }
 */
class StateResource {
    // The absolute resource address.
    @JSONField(name = "address")
    var address: String? = null

    // The resource mode. todo link "data" "managed"
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

    // The version of the resource type schema the "values" property
    // conforms to.
    @JSONField(name = "schema_version")
    var schemaVersion: Long? = null

    // The JSON representation of the attribute values of the resource,
    // whose structure depends on the resource type schema. Any unknown
    // values are omitted or set to null, making them indistinguishable
    // from absent values.
    @JSONField(name = "values")
    var values: MutableMap<String, Any>? = null

    // The JSON representation of the sensitivity of the resource's
    // attribute values. Only attributes which are sensitive
    // are included in this structure.
    @JSONField(name = "sensitive_values")
    var sensitiveValues: Any? = null

    // The addresses of the resources that this resource depends on.
    @JSONField(name = "depends_on")
    var dependsOn: MutableList<String>? = null

    // If true, the resource has been marked as tainted and will be
    // re-created on the next update.
    @JSONField(name = "tainted")
    var tainted: Boolean? = null

    // DeposedKey is set if the resource instance has been marked Deposed and
    // will be destroyed on the next apply.
    @JSONField(name = "deposed_key")
    var deposedKey: String? = null
}
