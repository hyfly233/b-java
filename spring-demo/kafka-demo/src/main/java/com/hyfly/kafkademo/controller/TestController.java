package com.hyfly.kafkademo.controller;

import com.hyfly.kafkademo.provider.ProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
public class TestController {

    private final ProviderService providerService;

    public TestController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @PostMapping(path = "/inputTopic")
    public void test() {
        String uuid = UUID.randomUUID().toString();
        log.info("uuid: {}", uuid);
        providerService.sendMsgCallback("inputTopic", uuid + " msg from inputTopic");
    }
}
