package com.hyfly.dolphinscheduler.sdk.task;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ConditionTask extends AbstractTask {

    @Override
    public String getTaskType() {
        return "CONDITIONS";
    }
}
