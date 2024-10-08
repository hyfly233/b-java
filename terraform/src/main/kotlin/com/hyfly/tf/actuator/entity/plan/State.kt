package com.hyfly.tf.actuator.entity.plan

import com.alibaba.fastjson2.annotation.JSONField

/**
 * github.com/hashicorp/terraform-json
 *
 *
 * // State is the top-level representation of a Terraform state.
 * type State struct {
 * // useJSONNumber opts into the behavior of calling
 * // json.Decoder.UseNumber prior to decoding the state, which turns
 * // numbers into json.Numbers instead of float64s. Set it using
 * // State.UseJSONNumber.
 * useJSONNumber bool
 * <p>
 * // The version of the state format. This should always match the
 * // StateFormatVersion constant in this package, or else am
 * // unmarshal will be unstable.
 * FormatVersion string `json:"format_version,omitempty"`
 * <p>
 * // The Terraform version used to make the state.
 * TerraformVersion string `json:"terraform_version,omitempty"`
 * <p>
 * // The values that make up the state.
 * Values *StateValues `json:"values,omitempty"`
 * <p>
 * // Checks contains the results of any conditional checks when Values was
 * // last updated.
 * Checks []CheckResultStatic `json:"checks,omitempty"`
 * }
 */
class State {
    // The version of the state format. This should always match the
    // StateFormatVersion constant in this package, or else am
    // unmarshal will be unstable.
    @JSONField(name = "format_version")
    var formatVersion: String? = null

    // The Terraform version used to make the state.
    @JSONField(name = "terraform_version")
    var terraformVersion: String? = null

    // The values that make up the state.
    @JSONField(name = "values")
    var values: StateValues? = null
}
