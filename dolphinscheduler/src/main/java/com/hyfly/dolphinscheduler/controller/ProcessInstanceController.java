package com.hyfly.dolphinscheduler.controller;

import com.hyfly.dolphinscheduler.sdk.common.PageInfo;
import com.hyfly.dolphinscheduler.sdk.instance.ProcessInstanceQueryResp;
import com.hyfly.dolphinscheduler.service.IProcessInstanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/processInstance")
public class ProcessInstanceController {

    private final IProcessInstanceService processInstanceService;

    public ProcessInstanceController(IProcessInstanceService processInstanceService) {
        this.processInstanceService = processInstanceService;
    }

    @GetMapping("/list")
    public PageInfo<ProcessInstanceQueryResp> list(@RequestParam Long projectCode, @RequestParam Long processDefinitionCode) {
        return processInstanceService.list(projectCode, processDefinitionCode);
    }

}
