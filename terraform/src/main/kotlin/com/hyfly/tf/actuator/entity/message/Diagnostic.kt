package com.hyfly.tf.actuator.entity.message;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * github.com/hashicorp/terraform/json/diagnostic.go
 */
@Data
public class Diagnostic {

    /**
     * unknown, error, warning
     */
    @JSONField(name = "severity")
    private String severity;

    @JSONField(name = "summary")
    private String summary;

    @JSONField(name = "detail")
    private String detail;

    @JSONField(name = "address")
    private Object address;

    @JSONField(name = "range")
    private Object range;

    @JSONField(name = "snippet")
    private Object snippet;
}
