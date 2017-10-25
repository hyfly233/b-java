package com.hyfly.dolphinscheduler.sdk.schedule;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ScheduleDefineParam {

    private Schedule schedule;

    private String failureStrategy = "END";

    private String warningType = "NONE";

    private String processInstancePriority = "MEDIUM";

    private String warningGroupId = "0";

    private String workerGroup = "default";

    private String environmentCode = "";

    private Long processDefinitionCode;

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
