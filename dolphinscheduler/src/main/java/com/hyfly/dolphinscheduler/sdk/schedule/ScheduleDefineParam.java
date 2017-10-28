package com.hyfly.dolphinscheduler.sdk.schedule;

import com.alibaba.fastjson2.JSONObject;
import com.hyfly.dolphinscheduler.sdk.constants.FailureStrategy;
import com.hyfly.dolphinscheduler.sdk.constants.Priority;
import com.hyfly.dolphinscheduler.sdk.constants.WarningType;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ScheduleDefineParam {

    private Schedule schedule;

    private String failureStrategy = FailureStrategy.END;

    private String warningType = WarningType.NONE;

    private String processInstancePriority = Priority.MEDIUM;

    private String warningGroupId = "0";

    private String workerGroup = "default";

    private String environmentCode = "";

    private Long processDefinitionCode;

    private String tenantCode;

    @Data
    @Accessors(chain = true)
    public static class Schedule {
        private String startTime;
        private String endTime;
        private String crontab;
        private String timezoneId = "Asia/Shanghai"; // default time zone

        @Override
        public String toString() {
            return JSONObject.toJSONString(this);
        }
    }
}
