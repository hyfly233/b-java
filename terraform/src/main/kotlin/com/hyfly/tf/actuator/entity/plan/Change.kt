package com.hyfly.tf.actuator.entity.plan

import com.alibaba.fastjson2.annotation.JSONField

/**
 * github.com/hashicorp/terraform-json
 *
 *
 * // Change is the representation of a proposed change for an object.
 * type Change struct {
 * // The action to be carried out by this change.
 * Actions Actions `json:"actions,omitempty"`
 *
 *
 * // Before and After are representations of the object value both
 * // before and after the action. For create and delete actions,
 * // either Before or After is unset (respectively). For no-op
 * // actions, both values will be identical. After will be incomplete
 * // if there are values within it that won't be known until after
 * // apply.
 * Before interface{} `json:"before,"`
 * After  interface{} `json:"after,omitempty"`
 *
 *
 * // A deep object of booleans that denotes any values that are
 * // unknown in a resource. These values were previously referred to
 * // as "computed" values.
 * //
 * // If the value cannot be found in this map, then its value should
 * // be available within After, so long as the operation supports it.
 * AfterUnknown interface{} `json:"after_unknown,omitempty"`
 *
 *
 * // BeforeSensitive and AfterSensitive are object values with similar
 * // structure to Before and After, but with all sensitive leaf values
 * // replaced with true, and all non-sensitive leaf values omitted. These
 * // objects should be combined with Before and After to prevent accidental
 * // display of sensitive values in user interfaces.
 * BeforeSensitive interface{} `json:"before_sensitive,omitempty"`
 * AfterSensitive  interface{} `json:"after_sensitive,omitempty"`
 *
 *
 * // Importing contains the import metadata about this operation. If importing
 * // is present (ie. not null) then the change is an import operation in
 * // addition to anything mentioned in the actions field. The actual contents
 * // of the Importing struct is subject to change, so downstream consumers
 * // should treat any values in here as strictly optional.
 * Importing *Importing `json:"importing,omitempty"`
 *
 *
 * // GeneratedConfig contains any HCL config generated for this resource
 * // during planning as a string.
 * //
 * // If this is populated, then Importing should also be populated but this
 * // might change in the future. However, not all Importing changes will
 * // contain generated config.
 * GeneratedConfig string `json:"generated_config,omitempty"`
 * }
 */
class Change {
    /**
     * The action to be carried out by this change.
     */
    @JSONField(name = "actions")
    var actions: MutableList<String>? = null

    // Before and After are representations of the object value both
    // before and after the action. For create and delete actions,
    // either Before or After is unset (respectively). For no-op
    // actions, both values will be identical. After will be incomplete
    // if there are values within it that won't be known until after
    // apply.
    @JSONField(name = "before")
    var before: Any? = null

    @JSONField(name = "after")
    var after: Any? = null

    // A deep object of booleans that denotes any values that are
    // unknown in a resource. These values were previously referred to
    // as "computed" values.
    //
    // If the value cannot be found in this map, then its value should
    // be available within After, so long as the operation supports it.
    @JSONField(name = "after_unknown")
    var afterUnknown: Any? = null

    // BeforeSensitive and AfterSensitive are object values with similar
    // structure to Before and After, but with all sensitive leaf values
    // replaced with true, and all non-sensitive leaf values omitted. These
    // objects should be combined with Before and After to prevent accidental
    // display of sensitive values in user interfaces.
    @JSONField(name = "before_sensitive")
    var beforeSensitive: Any? = null

    @JSONField(name = "after_sensitive")
    var afterSensitive: Any? = null

    // Importing contains the import metadata about this operation. If importing
    // is present (ie. not null) then the change is an import operation in
    // addition to anything mentioned in the actions field. The actual contents
    // of the Importing struct is subject to change, so downstream consumers
    // should treat any values in here as strictly optional.
    @JSONField(name = "importing")
    var importing: Importing? = null

    // GeneratedConfig contains any HCL config generated for this resource
    // during planning as a string.
    //
    // If this is populated, then Importing should also be populated but this
    // might change in the future. However, not all Importing changes will
    // contain generated config.
    @JSONField(name = "generated_config")
    var generatedConfig: String? = null
}
