package com.hyfly.dolphinscheduler.service;

import com.hyfly.dolphinscheduler.sdk.common.PageInfo;
import com.hyfly.dolphinscheduler.sdk.process.ProcessDefineResp;

public interface IProcessDefinitionService {

    PageInfo<ProcessDefineResp> list(Long projectCode);

    ProcessDefineResp detail(Long projectCode, Long processDefinitionCode);
}
