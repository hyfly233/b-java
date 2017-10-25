package com.hyfly.dolphinscheduler.service.impl;

import com.hyfly.dolphinscheduler.sdk.common.PageInfo;
import com.hyfly.dolphinscheduler.sdk.core.DolphinClient;
import com.hyfly.dolphinscheduler.sdk.taskinstance.TaskInstanceQueryResp;
import com.hyfly.dolphinscheduler.service.ITaskInstanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TaskInstanceServiceImpl implements ITaskInstanceService {

    private final DolphinClient dolphinClient;

    public TaskInstanceServiceImpl(DolphinClient dolphinClient) {
        this.dolphinClient = dolphinClient;
    }

    @Override
    public PageInfo<TaskInstanceQueryResp> list(Long projectCode, Long processInstanceId) {
        return dolphinClient.opsForTaskInstance().page(projectCode, null, null, processInstanceId);
    }
}
