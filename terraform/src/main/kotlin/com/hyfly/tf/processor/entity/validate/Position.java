package com.hyfly.tf.processor.entity.validate;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

@Data
public class Position {

    @JSONField(name = "line")
    private Integer line;

    @JSONField(name = "column")
    private Integer column;
}
