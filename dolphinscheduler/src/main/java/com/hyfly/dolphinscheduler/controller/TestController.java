package com.hyfly.dolphinscheduler.controller;

import com.github.weaksloth.dolphins.project.ProjectInfoResp;
import com.hyfly.dolphinscheduler.service.ITestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class TestController {

    private final ITestService testService;

    public TestController(ITestService testService) {
        this.testService = testService;
    }

    @GetMapping("/listProject")
    public List<ProjectInfoResp> listProject() {
        return testService.listProject();
    }
}
