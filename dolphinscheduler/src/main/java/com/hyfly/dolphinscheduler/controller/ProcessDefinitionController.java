package com.hyfly.dolphinscheduler.controller;

import com.hyfly.dolphinscheduler.sdk.common.PageInfo;
import com.hyfly.dolphinscheduler.sdk.process.ProcessDefineResp;
import com.hyfly.dolphinscheduler.service.IProcessDefinitionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/processDefinition")
public class ProcessDefinitionController {

    private final IProcessDefinitionService processDefinitionService;

    public ProcessDefinitionController(IProcessDefinitionService processDefinitionService) {
        this.processDefinitionService = processDefinitionService;
    }

    @GetMapping("/list")
    public PageInfo<ProcessDefineResp> list(@RequestParam Long projectCode) {
        return processDefinitionService.list(projectCode);
    }

    @GetMapping("/detail")
    public ProcessDefineResp detail(@RequestParam Long projectCode, @RequestParam Long processDefinitionCode) {
        return processDefinitionService.detail(projectCode, processDefinitionCode);
    }
}
