package com.hyfly.dolphinscheduler.sdk.project;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * create project param
 */
@Data
@Accessors(chain = true)
public class ProjectCreateParam {

    /**
     * project name
     */
    @JSONField(name = "projectName")
    private String projectName;

    /**
     * project description
     */
    @JSONField(name = "description")
    private String description;
}
