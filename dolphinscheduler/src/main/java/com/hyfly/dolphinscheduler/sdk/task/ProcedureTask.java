package com.hyfly.dolphinscheduler.sdk.task;

import com.hyfly.dolphinscheduler.sdk.process.Parameter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Collections;
import java.util.List;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ProcedureTask extends AbstractTask {

    /**
     * datasource type
     */
    private String type;

    /**
     * datasource id
     */
    private Integer datasource;

    private String method;

    /**
     * resource list
     */
    private List<ResourceInfo> resourceList = Collections.emptyList();

    private List<Parameter> localParams = Collections.emptyList();

    @Override
    public String getTaskType() {
        return "PROCEDURE";
    }
}
