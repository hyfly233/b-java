package com.hyfly.dolphinscheduler.controller;

import com.hyfly.dolphinscheduler.sdk.common.PageInfo;
import com.hyfly.dolphinscheduler.sdk.project.ProjectCreateParam;
import com.hyfly.dolphinscheduler.sdk.project.ProjectInfoResp;
import com.hyfly.dolphinscheduler.sdk.project.ProjectUpdateParam;
import com.hyfly.dolphinscheduler.service.IProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/project")
public class ProjectController {

    private final IProjectService projectService;

    public ProjectController(IProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/create")
    public ProjectInfoResp create(@RequestBody ProjectCreateParam param) {
        return projectService.createProject(param);
    }

    @PutMapping("/update")
    public ProjectInfoResp update(@RequestBody ProjectUpdateParam param) {
        return projectService.updateProject(param);
    }

    @GetMapping("/list")
    public PageInfo<ProjectInfoResp> list() {
        return projectService.listProject();
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam Long projectCode) {
        if (projectService.deleteProject(projectCode)) {
            return "success";
        } else {
            return "fail";
        }
    }
}
