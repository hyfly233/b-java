package com.hyfly.dolphinscheduler.test.project;

import com.hyfly.dolphinscheduler.DolphinSchedulerApplication;
import com.hyfly.dolphinscheduler.sdk.common.PageInfo;
import com.hyfly.dolphinscheduler.sdk.core.DolphinClient;
import com.hyfly.dolphinscheduler.sdk.project.ProjectCreateParam;
import com.hyfly.dolphinscheduler.sdk.project.ProjectInfoResp;
import com.hyfly.dolphinscheduler.sdk.project.ProjectUpdateParam;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest(classes = DolphinSchedulerApplication.class)
public class ProjectTest {

    private static final String PROJECT_NAME = "test_project";

    private final DolphinClient dolphinClient;


    public ProjectTest(@Autowired DolphinClient dolphinClient) {
        this.dolphinClient = dolphinClient;
    }

    @Test
    public void testCreateProject() {

        ProjectCreateParam param = new ProjectCreateParam();
        param.setProjectName(PROJECT_NAME).setDescription("created by dolphinscheduler java sdk");
        ProjectInfoResp projectInfoResp = dolphinClient.opsForProject().create(param);
        System.out.println(projectInfoResp);
        Assert.assertEquals(PROJECT_NAME, projectInfoResp.getName());
    }

    @Test
    public void testListProject() {
        PageInfo<ProjectInfoResp> pageInfo = dolphinClient.opsForProject().page(null, null, null);
        pageInfo.getTotalList().forEach(System.out::println);
    }

    @Test
    public void testUpdateProject() {
        PageInfo<ProjectInfoResp> pageInfo = dolphinClient.opsForProject().page(null, null, null);
        ProjectInfoResp projectInfo = pageInfo.getTotalList().get(0);
        ProjectUpdateParam updateParam = new ProjectUpdateParam();
        String newDescription = "updated by dolphinscheduler java sdk";
        updateParam
                .setProjectName(PROJECT_NAME)
                .setProjectCode(projectInfo.getCode())
                .setUserName(projectInfo.getUserName())
                .setDescription(newDescription);
        ProjectInfoResp newProjectInfo = dolphinClient.opsForProject().update(updateParam);
        Assert.assertEquals(newDescription, newProjectInfo.getDescription());
    }

    @Test
    public void testDeleteProject() {
        PageInfo<ProjectInfoResp> pageInfo = dolphinClient.opsForProject().page(null, null, PROJECT_NAME);
        // get test project code
        long code = pageInfo.getTotalList().get(0).getCode();
        dolphinClient.opsForProject().delete(code);
    }
}
