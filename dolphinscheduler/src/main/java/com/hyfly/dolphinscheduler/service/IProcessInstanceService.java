package com.hyfly.dolphinscheduler.service;

import com.hyfly.dolphinscheduler.sdk.common.PageInfo;
import com.hyfly.dolphinscheduler.sdk.instance.ProcessInstanceQueryResp;

public interface IProcessInstanceService {

    PageInfo<ProcessInstanceQueryResp> list(Long projectCode, Long processDefinitionCode);
}
