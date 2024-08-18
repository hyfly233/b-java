package com.hyfly.tf.actuator.entity.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceSummary {

    private String resourceId;

    private String resourceAlias;

    private String resourceType;
}
