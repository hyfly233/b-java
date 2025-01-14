package com.hyfly.dolphinscheduler.service;

import com.hyfly.dolphinscheduler.sdk.common.PageInfo;
import com.hyfly.dolphinscheduler.sdk.project.ProjectCreateParam;
import com.hyfly.dolphinscheduler.sdk.project.ProjectInfoResp;
import com.hyfly.dolphinscheduler.sdk.project.ProjectUpdateParam;

public interface IProjectService {

    ProjectInfoResp createProject(ProjectCreateParam param);

    ProjectInfoResp updateProject(ProjectUpdateParam param);

    PageInfo<ProjectInfoResp> listProject();

    Boolean deleteProject(Long projectCode);

}
