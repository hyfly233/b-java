package com.hyfly.dolphinscheduler.service;

import com.hyfly.dolphinscheduler.sdk.common.PageInfo;
import com.hyfly.dolphinscheduler.sdk.taskinstance.TaskInstanceQueryResp;

public interface ITaskInstanceService {

    PageInfo<TaskInstanceQueryResp> list(Long projectCode, Long processInstanceId);
}
