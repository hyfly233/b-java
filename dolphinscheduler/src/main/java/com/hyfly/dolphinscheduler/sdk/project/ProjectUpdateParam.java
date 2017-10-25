package com.hyfly.dolphinscheduler.sdk.project;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProjectUpdateParam {

    @JSONField(name = "projectCode")
    private Long projectCode;

    @JSONField(name = "projectName")
    private String projectName;

    @JSONField(name = "description")
    private String description;

    @JSONField(name = "userName")
    private String userName;
}
