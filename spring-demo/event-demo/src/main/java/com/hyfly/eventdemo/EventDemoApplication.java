package com.hyfly.eventdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@EnableAsync
@RestController
@RequestMapping("/event")
@SpringBootApplication
public class EventDemoApplication {

    private final ApplicationContext applicationContext;

    public EventDemoApplication(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static void main(String[] args) {
        SpringApplication.run(EventDemoApplication.class, args);
    }

    @GetMapping("/test01")
    public String test01() {
        long start = System.currentTimeMillis();

        // 同步发布事件
        applicationContext.publishEvent(new Test01Event(this, "test01"));

        long end = System.currentTimeMillis();
        log.info("test01 花费 {}ms", end - start);

        return "test01";
    }

    @GetMapping("/test02")
    public String test02() {
        long start = System.currentTimeMillis();

        // 异步发布事件
        applicationContext.publishEvent(new Test02Event("test02"));

        long end = System.currentTimeMillis();
        log.info("test02 花费 {}ms", end - start);

        return "test02";
    }
}
