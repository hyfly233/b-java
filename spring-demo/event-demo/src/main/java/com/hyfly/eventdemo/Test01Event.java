package com.hyfly.eventdemo;

import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@Getter
@ToString
public class Test01Event extends ApplicationEvent {

    private final String msg;

    public Test01Event(Object source, String msg) {
        super(source);

        this.msg = msg;
    }
}
