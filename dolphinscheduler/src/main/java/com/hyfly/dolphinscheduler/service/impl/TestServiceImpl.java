package com.hyfly.dolphinscheduler.service.impl;

import com.github.weaksloth.dolphins.core.DolphinClient;
import com.github.weaksloth.dolphins.project.ProjectInfoResp;
import com.hyfly.dolphinscheduler.service.ITestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TestServiceImpl implements ITestService {

    private final DolphinClient dolphinClient;

    public TestServiceImpl(DolphinClient dolphinClient) {
        this.dolphinClient = dolphinClient;
    }

    @Override
    public List<ProjectInfoResp> listProject() {
        return dolphinClient.opsForProject().page(1, 10, "test");
    }
}
