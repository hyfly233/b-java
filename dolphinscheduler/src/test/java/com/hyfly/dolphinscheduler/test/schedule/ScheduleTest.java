package com.hyfly.dolphinscheduler.test.schedule;

import com.hyfly.dolphinscheduler.DolphinSchedulerApplication;
import com.hyfly.dolphinscheduler.sdk.common.PageInfo;
import com.hyfly.dolphinscheduler.sdk.core.DolphinClient;
import com.hyfly.dolphinscheduler.sdk.schedule.ScheduleDefineParam;
import com.hyfly.dolphinscheduler.sdk.schedule.ScheduleInfoResp;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest(classes = DolphinSchedulerApplication.class)
public class ScheduleTest {

    public static final Long PROJECT_CODE = 130451874725376L;

    public static final Long WORKFLOW_CODE = 11386905160832L;

    private final DolphinClient dolphinClient;

    public ScheduleTest(DolphinClient dolphinClient) {
        this.dolphinClient = dolphinClient;
    }

    /**
     * the workflow must in online state
     */
    @Test
    public void testCreate() {
        ScheduleDefineParam scheduleDefineParam = new ScheduleDefineParam();
        scheduleDefineParam
                .setProcessDefinitionCode(WORKFLOW_CODE)
                .setSchedule(
                        new ScheduleDefineParam.Schedule()
                                .setStartTime("2023-10-27 00:00:00")
                                .setEndTime("2024-09-20 00:00:00")
                                .setCrontab("0 0 * * * ? *"));
        ScheduleInfoResp scheduleInfoResp =
                dolphinClient.opsForSchedule().create(PROJECT_CODE, scheduleDefineParam);
        System.out.println(scheduleInfoResp);
    }

    @Test
    public void testGetByProject() {
        PageInfo<ScheduleInfoResp> pageInfo = dolphinClient.opsForSchedule().getByWorkflowCode(PROJECT_CODE, WORKFLOW_CODE);
        List<ScheduleInfoResp> resp = pageInfo.getTotalList();

        System.out.println(resp);
        Assert.assertEquals(1, resp.size());
    }

    @Test
    public void testOnline() {
        PageInfo<ScheduleInfoResp> pageInfo = dolphinClient.opsForSchedule().getByWorkflowCode(PROJECT_CODE, WORKFLOW_CODE);
        List<ScheduleInfoResp> resp = pageInfo.getTotalList();

        long id = resp.get(0).getId();
        Assert.assertTrue(dolphinClient.opsForSchedule().online(PROJECT_CODE, id));
    }

    @Test
    public void testOffline() {
        PageInfo<ScheduleInfoResp> pageInfo = dolphinClient.opsForSchedule().getByWorkflowCode(PROJECT_CODE, WORKFLOW_CODE);
        List<ScheduleInfoResp> resp = pageInfo.getTotalList();

        long id = resp.get(0).getId();
        Assert.assertTrue(dolphinClient.opsForSchedule().offline(PROJECT_CODE, id));
    }

    @Test
    public void testDelete() {
        PageInfo<ScheduleInfoResp> pageInfo = dolphinClient.opsForSchedule().getByWorkflowCode(PROJECT_CODE, WORKFLOW_CODE);
        List<ScheduleInfoResp> resp = pageInfo.getTotalList();

        long id = resp.get(0).getId();
        Assert.assertTrue(dolphinClient.opsForSchedule().delete(PROJECT_CODE, id));
    }
}
