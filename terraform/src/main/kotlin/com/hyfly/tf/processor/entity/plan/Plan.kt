package com.hyfly.tf.processor.entity.plan

import com.alibaba.fastjson2.annotation.JSONField

data class Plan(
    @JSONField(name = "format_version")
    var formatVersion: String? = null,

    @JSONField(name = "terraform_version")
    val terraformVersion: String? = null,

    @JSONField(name = "variables")
    val variables: Map<String, PlanVariable>? = null,

    @JSONField(name = "planned_values")
    val plannedValues: StateValues? = null,

    // The change operations for resources and data sources within this
    // plan.
    @JSONField(name = "resource_changes")
    val resourceChanges: List<ResourceChange>? = null,

    // The Terraform configuration used to make the plan.
    @JSONField(name = "configuration")
    val configuration: Config? = null
)
