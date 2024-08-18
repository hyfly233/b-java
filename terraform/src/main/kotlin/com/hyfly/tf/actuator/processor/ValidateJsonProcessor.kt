package com.hyfly.tf.actuator.processor;

import com.alibaba.fastjson2.JSON;
import com.hyfly.tf.actuator.entity.message.Diagnostic;
import com.hyfly.tf.actuator.entity.message.MessageView;
import com.hyfly.tf.actuator.entity.message.constants.MessageLevel;
import com.hyfly.tf.actuator.entity.validate.Validate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

@Data
@EqualsAndHashCode(callSuper = true)
public class ValidateJsonProcessor extends BaseProcessor {

    private Validate validate;

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
                }
            } else if (line.contains("error_count") && line.contains("warning_count")) {
                validate = JSON.parseObject(line, Validate.class);
                completed = true;
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
