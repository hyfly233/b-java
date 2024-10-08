package com.hyfly.tf.actuator.entity.schema

import com.alibaba.fastjson2.annotation.JSONField
import lombok.Data

/**
 * // The attributes defined at the particular level of this block.
 * Attributes map[string]*SchemaAttribute `json:"attributes,omitempty"`
 * <p>
 * // Any nested blocks within this particular block.
 * NestedBlocks map[string]*SchemaBlockType `json:"block_types,omitempty"`
 * <p>
 * // The description for this block and format of the description. If
 * // no kind is provided, it can be assumed to be plain text.
 * Description     string                `json:"description,omitempty"`
 * DescriptionKind SchemaDescriptionKind `json:"description_kind,omitempty"`
 * <p>
 * // If true, this block is deprecated.
 * Deprecated bool `json:"deprecated,omitempty"`
 */
@Data
class SchemaBlock {
    @JSONField(name = "attributes")
    var attributes: MutableMap<String, SchemaAttribute>? = null
}
