package com.hyfly.actuator.entity.validate;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.util.List;

@Data
public class Validate {

    @JSONField(name = "valid")
    private Boolean valid;

    @JSONField(name = "error_count")
    private Integer errorCount;

    @JSONField(name = "warning_count")
    private Integer warningCount;

    @JSONField(name = "diagnostics")
    private List<Diagnostic> diagnostics;
}
