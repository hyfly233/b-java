package com.hyfly.tf.actuator.entity.plan;


import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * github.com/hashicorp/terraform-json
 */
@Data
public class Plan {

    @JSONField(name = "format_version")
    private String formatVersion;

    @JSONField(name = "terraform_version")
    private String terraformVersion;

    @JSONField(name = "variables")
    private Map<String, PlanVariable> variables;

    @JSONField(name = "planned_values")
    private StateValues plannedValues;

    // The change operations for resources and data sources within this
    // plan.
    @JSONField(name = "resource_changes")
    private List<ResourceChange> resourceChanges;

    // The Terraform configuration used to make the plan.
    @JSONField(name = "configuration")
    private Config configuration;
}
