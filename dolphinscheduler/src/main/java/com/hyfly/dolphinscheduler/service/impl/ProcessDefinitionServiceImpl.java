package com.hyfly.dolphinscheduler.service.impl;

import com.hyfly.dolphinscheduler.sdk.common.PageInfo;
import com.hyfly.dolphinscheduler.sdk.core.DolphinClient;
import com.hyfly.dolphinscheduler.sdk.process.ProcessDefineResp;
import com.hyfly.dolphinscheduler.service.IProcessDefinitionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProcessDefinitionServiceImpl implements IProcessDefinitionService {

    private final DolphinClient dolphinClient;

    public ProcessDefinitionServiceImpl(DolphinClient dolphinClient) {
        this.dolphinClient = dolphinClient;
    }

    @Override
    public PageInfo<ProcessDefineResp> list(Long projectCode) {
        return dolphinClient.opsForProcess().page(projectCode, null, null, null);
    }

    @Override
    public ProcessDefineResp detail(Long projectCode, Long processDefinitionCode) {
//        return dolphinClient.opsForProcess().detail(projectCode, processDefinitionCode);
        return null;
    }

}
