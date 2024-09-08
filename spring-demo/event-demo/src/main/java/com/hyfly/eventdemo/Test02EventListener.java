package com.hyfly.eventdemo;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Test02EventListener {

    @Async
    @SneakyThrows
    @EventListener(Test02Event.class)
    public void handlerTest02Event(Test02Event event) {
        String msg = event.getMsg();

        long start = System.currentTimeMillis();
        Thread.sleep(2000);
        long end = System.currentTimeMillis();

        log.info("Test02EventListener 处理消息: {} 花费 {}ms", msg, end - start);
    }
}
