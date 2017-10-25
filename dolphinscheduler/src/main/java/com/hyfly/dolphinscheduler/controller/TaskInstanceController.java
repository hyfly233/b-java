package com.hyfly.dolphinscheduler.controller;

import com.hyfly.dolphinscheduler.sdk.common.PageInfo;
import com.hyfly.dolphinscheduler.sdk.taskinstance.TaskInstanceQueryResp;
import com.hyfly.dolphinscheduler.service.ITaskInstanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/taskInstance")
public class TaskInstanceController {

    private final ITaskInstanceService taskInstanceService;

    public TaskInstanceController(ITaskInstanceService taskInstanceService) {
        this.taskInstanceService = taskInstanceService;
    }

    @GetMapping("/list")
    public PageInfo<TaskInstanceQueryResp> list(@RequestParam Long projectCode, @RequestParam Long processInstanceId) {
        return taskInstanceService.list(projectCode, processInstanceId);
    }
}
