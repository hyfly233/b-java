package com.hyfly.tf.processor.entity.validate;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

@Data
public class Snippet {

    @JSONField(name = "context")
    private String context;

    @JSONField(name = "code")
    private String code;

    @JSONField(name = "start_line")
    private Integer startLine;
}
