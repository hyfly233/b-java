package com.hyfly.actuator.processor;

import com.alibaba.fastjson2.JSON;
import com.hyfly.actuator.entity.message.Diagnostic;
import com.hyfly.actuator.entity.message.MessageView;
import com.hyfly.actuator.entity.message.constants.MessageLevel;
import com.hyfly.actuator.entity.message.constants.MessageType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

@Data
@EqualsAndHashCode(callSuper = true)
public class ApplyJsonProcessor extends BaseProcessor {
    @Override
    public void parse(String line) {
        if (completed) {
            return;
        }

        if (StringUtils.isNotBlank(line)) {
            if (line.contains("@level") && line.contains("@message") &&
                    line.contains("@module") && line.contains("@timestamp")) {
                MessageView view = JSON.parseObject(line, MessageView.class);

                if (MessageLevel.ERROR.equals(view.getLevel())) {
                    String message = view.getMessage();
                    Diagnostic diagnostic = view.getDiagnostic();
                    if (diagnostic != null) {
                        message = message + ". " + diagnostic.getDetail();
                    }

                    hasErr = true;
                    errorBuilder.append(message.trim()).append("\n");

                } else if (MessageLevel.INFO.equals(view.getLevel())) {
                    if (MessageType.CHANGE_SUMMARY.equals(view.getType())) {
                        String message = view.getMessage();
                        if (StringUtils.isNotBlank(message)) {
                            message = message.toLowerCase();
                            if (message.contains("apply complete")) {
                                completed = true;
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void parseError(String line) {
        hasErr = true;
        if (StringUtils.isNotBlank(line)) {
            errorBuilder.append(line.trim()).append("\n");
        }
    }
}
