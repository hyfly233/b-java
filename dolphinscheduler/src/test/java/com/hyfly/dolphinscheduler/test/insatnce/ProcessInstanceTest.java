package com.hyfly.dolphinscheduler.test.insatnce;

import com.hyfly.dolphinscheduler.DolphinSchedulerApplication;
import com.hyfly.dolphinscheduler.sdk.common.PageInfo;
import com.hyfly.dolphinscheduler.sdk.core.DolphinClient;
import com.hyfly.dolphinscheduler.sdk.instance.ProcessInstanceCreateParam;
import com.hyfly.dolphinscheduler.sdk.instance.ProcessInstanceQueryResp;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest(classes = DolphinSchedulerApplication.class)
public class ProcessInstanceTest {

    public static final Long PROCESS_DEFINITION_CODE = 11386905142912L;

    public static final Long PROJECT_CODE = 130451874725376L;


    private final DolphinClient dolphinClient;

    public ProcessInstanceTest(@Autowired DolphinClient dolphinClient) {
        this.dolphinClient = dolphinClient;
    }

    /**
     * the workflow must in online state,otherwise will cause error
     */
    @Test
    public void testStartInstance() {

        ProcessInstanceCreateParam startParam = new ProcessInstanceCreateParam();
        startParam
                .setProcessDefinitionCode(PROCESS_DEFINITION_CODE)
                .setScheduleTime("")
                .setFailureStrategy("CONTINUE")
                .setWarningType("NONE")
                .setWarningGroupId(0L)
                .setExecType("")
                .setStartNodeList("")
                .setTaskDependType("TASK_POST")
                .setRunMode("RUN_MODE_SERIAL")
                .setProcessInstancePriority("MEDIUM")
                .setWorkerGroup("default")
                .setEnvironmentCode("")
                .setStartParams("")
                .setExpectedParallelismNumber("")
                .setDryRun(0);
        Assert.assertTrue(dolphinClient.opsForProcessInst().start(PROJECT_CODE, startParam));
    }

    @Test
    public void testReRun() {
        Long instanceId = 31L;
        Assert.assertTrue(dolphinClient.opsForProcessInst().reRun(PROJECT_CODE, instanceId));
    }

    @Test
    public void testPage() {
        PageInfo<ProcessInstanceQueryResp> pageInfo = dolphinClient
                .opsForProcessInst()
                .page(null, null, PROJECT_CODE, PROCESS_DEFINITION_CODE);

        pageInfo.getTotalList()
                .forEach(System.out::println);
    }

    @Test
    public void testDelete() {
        Long instanceId = 31L;
        Assert.assertTrue(dolphinClient.opsForProcessInst().delete(PROJECT_CODE, instanceId));
    }
}
