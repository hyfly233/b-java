package com.hyfly.dolphinscheduler.sdk.process;

import com.alibaba.fastjson2.JSONObject;
import com.hyfly.dolphinscheduler.sdk.remote.RequestHttpEntity;
import com.hyfly.dolphinscheduler.sdk.task.AbstractTask;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TaskDefinition {

    private Long code;

    private Integer version;

    /**
     * the task node's name
     */
    private String name;

    /**
     * the task node's description
     */
    private String description;

    /**
     * get from {@link AbstractTask#getTaskType()}
     */
    private String taskType;

    private AbstractTask taskParams;

    /**
     * NO:the node will not execute;YES:the node will execute,default is YES
     */
    private String flag;

    private String taskPriority;

    private String workerGroup;

    private String failRetryTimes;

    private String failRetryInterval;

    private String timeoutFlag;

    private String timeoutNotifyStrategy;

    private Integer timeout = 0;

    /**
     * 延迟时间 分钟
     */
    private String delayTime = "0";

    private Long environmentCode = -1L;

    private String taskExecuteType;

    private Integer cpuQuota = -1;

    private Long memoryMax = -1L;

    /**
     * YES, NO *
     */
    private String isCache = "NO";

    /**
     * must rewrite,then {@link RequestHttpEntity#bodyToMap()} can transfer object to json string
     *
     * @return object json string
     */
    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
