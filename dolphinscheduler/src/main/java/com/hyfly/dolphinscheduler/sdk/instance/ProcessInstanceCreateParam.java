package com.hyfly.dolphinscheduler.sdk.instance;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * org.apache.dolphinscheduler.api.controller.ExecutorController::startProcessInstance
 * process instance create param
 */
@Data
@Accessors(chain = true)
public class ProcessInstanceCreateParam {

    /**
     * 失败策略
     * <p>
     * END, CONTINUE
     */
    private String failureStrategy;

    private Long processDefinitionCode;

    /**
     * 流程实例优先级
     */
    private String processInstancePriority;

    /**
     * 定时时间,空字符串表示当前天
     * <p>
     * Example : 2022-04-06 00:00:00,2022-04-06 00:00:00
     */
    private String scheduleTime;

    private Long warningGroupId;

    private String warningType;

    /**
     * o or 1
     */
    private Integer dryRun;

    /**
     * env code
     */
    private String environmentCode;

    /**
     * 指令类型
     */
    private String execType;

    private String expectedParallelismNumber;

    /**
     * 运行模式
     * <p>
     * run mode,value:RUN_MODE_SERIAL,RUN_MODE_PARALLEL
     */
    private String runMode;

    private String startNodeList;

    private String startParams;

    /**
     * 任务依赖类型
     */
    private String taskDependType;

    /**
     * worker group
     */
    private String workerGroup;

    private String tenantCode;
}
