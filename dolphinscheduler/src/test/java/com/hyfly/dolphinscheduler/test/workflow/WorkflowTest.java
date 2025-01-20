package com.hyfly.dolphinscheduler.test.workflow;

import com.hyfly.dolphinscheduler.DolphinSchedulerApplication;
import com.hyfly.dolphinscheduler.sdk.common.PageInfo;
import com.hyfly.dolphinscheduler.sdk.core.DolphinClient;
import com.hyfly.dolphinscheduler.sdk.process.ProcessDefineParam;
import com.hyfly.dolphinscheduler.sdk.process.ProcessDefineResp;
import com.hyfly.dolphinscheduler.sdk.process.TaskDefinition;
import com.hyfly.dolphinscheduler.sdk.task.HttpTask;
import com.hyfly.dolphinscheduler.sdk.task.ShellTask;
import com.hyfly.dolphinscheduler.sdk.util.TaskDefinitionUtils;
import com.hyfly.dolphinscheduler.sdk.util.TaskLocationUtils;
import com.hyfly.dolphinscheduler.sdk.util.TaskRelationUtils;
import com.hyfly.dolphinscheduler.sdk.util.TaskUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@Slf4j
@SpringBootTest(classes = DolphinSchedulerApplication.class)
public class WorkflowTest {

    public static final String WORKFLOW_NAME = "test-dag2";

    public static final Long PROJECT_CODE = 130451874725376L;

    public static final String TENANT_CODE = "root";

    private final DolphinClient dolphinClient;

    public WorkflowTest(@Autowired DolphinClient dolphinClient) {
        this.dolphinClient = dolphinClient;
    }

    /**
     * create simple workflow like: shellTask -> httpTask
     *
     * <p>1.generate task code
     *
     * <p>2.create tasks
     *
     * <p>3.create task definitions
     *
     * <p>4.create task relations
     *
     * <p>5.create process create parm
     *
     * <p>
     */
    @Test
    public void testCreateProcessDefinition() {

        List<Long> taskCodes = dolphinClient.opsForProcessDefinition().generateTaskCode(PROJECT_CODE, 2);

        // build shell task
        ShellTask shellTask = new ShellTask();
        shellTask.setRawScript("echo 'hello dolphin scheduler java sdk'");
        TaskDefinition shellTaskDefinition =
                TaskDefinitionUtils.createDefaultTaskDefinition(taskCodes.get(0), shellTask);

        // build http task
        HttpTask httpTask = new HttpTask();
        httpTask
                .setUrl("http://www.baidu.com")
                .setHttpMethod("GET")
                .setHttpCheckCondition("STATUS_CODE_DEFAULT")
                .setCondition("")
                .setConditionResult(TaskUtils.createEmptyConditionResult());
        TaskDefinition httpTaskDefinition =
                TaskDefinitionUtils.createDefaultTaskDefinition(taskCodes.get(1), httpTask);

        ProcessDefineParam pcr = new ProcessDefineParam();
        pcr.setName(WORKFLOW_NAME)
                .setLocations(TaskLocationUtils.horizontalLocation(taskCodes.toArray(new Long[0])))
                .setDescription("test-dag-description")
                .setTenantCode(TENANT_CODE)
                .setTimeout("0")
                .setExecutionType(ProcessDefineParam.EXECUTION_TYPE_PARALLEL)
                .setTaskDefinitionJson(Arrays.asList(shellTaskDefinition, httpTaskDefinition))
                .setTaskRelationJson(TaskRelationUtils.oneLineRelation(taskCodes.toArray(new Long[0])))
                .setGlobalParams(null);

        System.out.println(dolphinClient.opsForProcessDefinition().create(PROJECT_CODE, pcr));
    }

    @Test
    public void testPage() {
        PageInfo<ProcessDefineResp> pageInfo = dolphinClient.opsForProcessDefinition().page(PROJECT_CODE, null, null, WORKFLOW_NAME);
        List<ProcessDefineResp> page = pageInfo.getTotalList();

        int expectedWorkflowNumber = 1;
        Assert.assertEquals(expectedWorkflowNumber, page.size());
    }

    @Test
    public void testOnlineWorkflow() {
        PageInfo<ProcessDefineResp> pageInfo = dolphinClient.opsForProcessDefinition().page(PROJECT_CODE, null, null, WORKFLOW_NAME);
        List<ProcessDefineResp> page = pageInfo.getTotalList();
        Assert.assertTrue(dolphinClient.opsForProcessDefinition().online(PROJECT_CODE, page.get(0).getCode()));
    }

    @Test
    public void testOfflineWorkflow() {
        PageInfo<ProcessDefineResp> pageInfo = dolphinClient.opsForProcessDefinition().page(PROJECT_CODE, null, null, WORKFLOW_NAME);
        List<ProcessDefineResp> page = pageInfo.getTotalList();
        Assert.assertTrue(dolphinClient.opsForProcessDefinition().offline(PROJECT_CODE, page.get(0).getCode()));
    }

    /**
     * the workflow must in offline state
     */
    @Test
    public void testDeleteWorkflow() {
        PageInfo<ProcessDefineResp> pageInfo = dolphinClient.opsForProcessDefinition().page(PROJECT_CODE, null, null, WORKFLOW_NAME);
        List<ProcessDefineResp> page = pageInfo.getTotalList();
        Assert.assertTrue(dolphinClient.opsForProcessDefinition().delete(PROJECT_CODE, page.get(0).getCode()));
    }
}
