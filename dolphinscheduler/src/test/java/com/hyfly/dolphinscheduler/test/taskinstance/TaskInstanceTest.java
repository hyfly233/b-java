package com.hyfly.dolphinscheduler.test.taskinstance;

import com.hyfly.dolphinscheduler.DolphinSchedulerApplication;
import com.hyfly.dolphinscheduler.sdk.common.PageInfo;
import com.hyfly.dolphinscheduler.sdk.core.DolphinClient;
import com.hyfly.dolphinscheduler.sdk.taskinstance.TaskInstanceQueryResp;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest(classes = DolphinSchedulerApplication.class)
public class TaskInstanceTest {

    public static final Long PROJECT_CODE = 130451874725376L;

    private final DolphinClient dolphinClient;

    public TaskInstanceTest(@Autowired DolphinClient dolphinClient) {
        this.dolphinClient = dolphinClient;
    }

    @Test
    public void testPage() {
        Long processInstanceId = 1L;

        PageInfo<TaskInstanceQueryResp> pageInfo = dolphinClient.opsForTaskInstance().page(PROJECT_CODE, null, null, processInstanceId);
        List<TaskInstanceQueryResp> taskInstanceQueryResps = pageInfo.getTotalList();

        taskInstanceQueryResps.forEach(System.out::println);
    }

    @Test
    public void testQueryLog() {
        Long taskInstanceId = 1L;
        String log = dolphinClient.opsForTaskInstance().queryLog(PROJECT_CODE, null, null, taskInstanceId);

        System.out.println(log);
    }
}
