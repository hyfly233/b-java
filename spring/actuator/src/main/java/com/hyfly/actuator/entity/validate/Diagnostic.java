package com.hyfly.actuator.entity.validate;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

@Data
public class Diagnostic {

    @JSONField(name = "severity")
    private String severity;

    @JSONField(name = "summary")
    private String summary;

    @JSONField(name = "detail")
    private String detail;

    @JSONField(name = "range")
    private Range range;

    @JSONField(name = "snippet")
    private Snippet snippet;
}
