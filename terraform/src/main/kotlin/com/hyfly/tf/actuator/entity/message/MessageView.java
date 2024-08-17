package com.hyfly.tf.actuator.entity.message;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

@Data
public class MessageView {

    /**
     * info, error
     */
    @JSONField(name = "@level")
    private String level;

    @JSONField(name = "@message")
    private String message;

    @JSONField(name = "@module")
    private String module;

    @JSONField(name = "@timestamp")
    private String timestamp;

    /**
     * planned_change, change_summary
     */
    @JSONField(name = "type")
    private String type;

    @JSONField(name = "changes")
    private ChangeSummary changeSummary;

    @JSONField(name = "diagnostic")
    private Diagnostic diagnostic;
}
