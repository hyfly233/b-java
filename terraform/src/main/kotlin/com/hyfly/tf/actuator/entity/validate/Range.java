package com.hyfly.tf.actuator.entity.validate;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

@Data
public class Range {

    @JSONField(name = "filename")
    private String filename;

    @JSONField(name = "start")
    private Position start;

    @JSONField(name = "end")
    private Position end;
}
