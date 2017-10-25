package com.hyfly.dolphinscheduler.sdk.process;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * dolphin scheduler define process param
 *
 * <p>dolphin scheduler use post form type to create process,so in fact every attribute is string
 * type
 *
 * <p>but in order to develop easier,we use TaskLocation,TaskDefinition,TaskRelation object,and
 * rewrite toString method
 *
 * <p>so that,when begin to send request,we will transfer this object to json string
 */
@Data
@Accessors(chain = true)
public class ProcessDefineParam {

    public static final String EXECUTION_TYPE_PARALLEL = "PARALLEL";
    public static final String EXECUTION_TYPE_SERIAL_WAIT = "SERIAL_WAIT";
    public static final String EXECUTION_TYPE_SERIAL_DISCARD = "SERIAL_DISCARD";
    public static final String EXECUTION_TYPE_SERIAL_PRIORITY = "SERIAL_PRIORITY";

    /**
     * workflow name
     */
    @JSONField(name = "name")
    private String name;

    /**
     * location
     */
    @JSONField(name = "locations")
    private List<TaskLocation> locations;

    @JSONField(name = "taskDefinitionJson")
    private List<TaskDefinition> taskDefinitionJson;

    @JSONField(name = "taskRelationJson")
    private List<TaskRelation> taskRelationJson;

    /**
     * tenant code
     */
    @JSONField(name = "tenantCode")
    private String tenantCode;

    /**
     * desc for workflow
     */
    @JSONField(name = "description")
    private String description;

    /**
     * PARALLEL,SERIAL_WAIT,SERIAL_DISCARD,SERIAL_PRIORITY
     *
     * <p>@see org.apache.dolphinscheduler.common.enums.ProcessExecutionTypeEnum
     */
    @JSONField(name = "executionType")
    private String executionType;

    /**
     * global params
     */
    @JSONField(name = "globalParams")
    private List<Parameter> globalParams;

    @JSONField(name = "timeout")
    private String timeout;

    public static ProcessDefineParam newParallelInstance() {
        return new ProcessDefineParam().setExecutionType(EXECUTION_TYPE_PARALLEL);
    }

    public static ProcessDefineParam newSerialWaitInstance() {
        return new ProcessDefineParam().setExecutionType(EXECUTION_TYPE_SERIAL_WAIT);
    }

    public static ProcessDefineParam newSerialDiscardInstance() {
        return new ProcessDefineParam().setExecutionType(EXECUTION_TYPE_SERIAL_DISCARD);
    }

    public static ProcessDefineParam newSerialPriorityInstance() {
        return new ProcessDefineParam().setExecutionType(EXECUTION_TYPE_SERIAL_PRIORITY);
    }
}
