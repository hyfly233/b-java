package com.hyfly.actuator.processor;

public interface IProcessor {

    void parse(String line);

    void parseError(String line);

    String getErrMsg();
}
