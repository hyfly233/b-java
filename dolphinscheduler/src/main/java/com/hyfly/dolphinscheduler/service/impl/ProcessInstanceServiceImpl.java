package com.hyfly.dolphinscheduler.service.impl;

import com.hyfly.dolphinscheduler.sdk.common.PageInfo;
import com.hyfly.dolphinscheduler.sdk.core.DolphinClient;
import com.hyfly.dolphinscheduler.sdk.instance.ProcessInstanceQueryResp;
import com.hyfly.dolphinscheduler.service.IProcessInstanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProcessInstanceServiceImpl implements IProcessInstanceService {

    private final DolphinClient dolphinClient;

    public ProcessInstanceServiceImpl(DolphinClient dolphinClient) {
        this.dolphinClient = dolphinClient;
    }

    @Override
    public PageInfo<ProcessInstanceQueryResp> list(Long projectCode, Long processDefinitionCode) {
        return dolphinClient.opsForProcessInst().page(null, null, projectCode, processDefinitionCode);
    }
}
