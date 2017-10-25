package com.hyfly.dolphinscheduler.service.impl;

import com.hyfly.dolphinscheduler.sdk.common.PageInfo;
import com.hyfly.dolphinscheduler.sdk.core.DolphinClient;
import com.hyfly.dolphinscheduler.sdk.project.ProjectCreateParam;
import com.hyfly.dolphinscheduler.sdk.project.ProjectInfoResp;
import com.hyfly.dolphinscheduler.sdk.project.ProjectUpdateParam;
import com.hyfly.dolphinscheduler.service.IProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProjectServiceImpl implements IProjectService {

    private final DolphinClient dolphinClient;

    public ProjectServiceImpl(DolphinClient dolphinClient) {
        this.dolphinClient = dolphinClient;
    }

    @Override
    public ProjectInfoResp createProject(ProjectCreateParam param) {
        return dolphinClient.opsForProject().create(param);
    }

    @Override
    public ProjectInfoResp updateProject(ProjectUpdateParam param) {
        return dolphinClient.opsForProject().update(param);
    }

    @Override
    public PageInfo<ProjectInfoResp> listProject() {
        return dolphinClient.opsForProject().page(null, null, null);
    }

    @Override
    public Boolean deleteProject(Long projectCode) {
        return dolphinClient.opsForProject().delete(projectCode);
    }
}
