package com.hyfly.eventdemo;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Test01EventListener implements ApplicationListener<Test01Event> {

    @Override
    @SneakyThrows
    public void onApplicationEvent(Test01Event event) {
        String msg = event.getMsg();

        long start = System.currentTimeMillis();
        Thread.sleep(2000);
        long end = System.currentTimeMillis();

        log.info("Test01EventListener 处理消息: {} 花费 {}ms", msg, end - start);
    }
}
