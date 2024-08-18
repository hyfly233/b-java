package com.hyfly.tf.actuator.entity.plan

import com.alibaba.fastjson2.annotation.JSONField

/**
 * github.com/hashicorp/terraform-json
 */
class Plan {
    @JSONField(name = "format_version")
    var formatVersion: String? = null

    @JSONField(name = "terraform_version")
    var terraformVersion: String? = null

    @JSONField(name = "variables")
    var variables: MutableMap<String, PlanVariable>? = null

    @JSONField(name = "planned_values")
    var plannedValues: StateValues? = null

    // The change operations for resources and data sources within this
    // plan.
    @JSONField(name = "resource_changes")
    var resourceChanges: MutableList<ResourceChange>? = null

    // The Terraform configuration used to make the plan.
    @JSONField(name = "configuration")
    var configuration: Config? = null
}
