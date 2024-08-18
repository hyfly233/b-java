package com.hyfly.tf.actuator.processor;

import lombok.Data;

@Data
public class BaseProcessor implements IProcessor {

    protected boolean hasErr = false;

    protected boolean completed = false;

    protected final StringBuilder errorBuilder;

    BaseProcessor() {
        errorBuilder = new StringBuilder();
    }

    @Override
    public void parse(String line) {
        System.out.println(line);
    }

    @Override
    public void parseError(String line) {
        System.out.println(line);
    }

    @Override
    public String getErrMsg() {
        if (hasErr) {
            return errorBuilder.toString().trim();
        }
        return null;
    }
}
