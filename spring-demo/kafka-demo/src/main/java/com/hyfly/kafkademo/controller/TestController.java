package com.hyfly.kafkademo.controller;

import com.hyfly.kafkademo.producer.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
public class TestController {

    private final ProducerService producerService;

    public TestController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping(path = "/ping")
    public String ping() {
        return "pong";
    }

    @PostMapping(path = "/inputTopic01")
    public void test01() {
        String uuid = UUID.randomUUID().toString();
        log.info("uuid01: {}", uuid);
        producerService.sendMsgCallback("inputTopic01", uuid + " msg from inputTopic01");
    }

    @PostMapping(path = "/inputTopic02")
    public void test02() {
        String uuid = UUID.randomUUID().toString();
        log.info("uuid02: {}", uuid);
        producerService.sendMsgCallback("inputTopic02", "msg from inputTopic02 " + uuid);
    }
}
