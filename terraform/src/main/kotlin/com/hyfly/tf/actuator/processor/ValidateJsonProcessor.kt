package com.hyfly.tf.actuator.processor

import com.alibaba.fastjson2.JSON
import com.hyfly.tf.actuator.entity.message.MessageView
import com.hyfly.tf.actuator.entity.message.constants.MessageLevel
import com.hyfly.tf.actuator.entity.validate.Validate

class ValidateJsonProcessor : BaseProcessor() {
    private var validate: Validate? = null

    override fun parse(line: String?) {
        if (completed) {
            return
        }

        if (!line.isNullOrEmpty()) {
            if (line.contains("@level") && line.contains("@message") &&
                line.contains("@module") && line.contains("@timestamp")
            ) {
                val view = JSON.parseObject(line, MessageView::class.java)

                if (MessageLevel.ERROR == view.level) {
                    hasErr = true

                    var message = view.message

                    if (!message.isNullOrEmpty()) {
                        val diagnostic = view.diagnostic
                        if (diagnostic != null) {
                            message = message + ". " + diagnostic.detail
                        }

                        errorBuilder.append(message.trim()).append("\n")
                    }
                }
            } else if (line.contains("error_count") && line.contains("warning_count")) {
                validate = JSON.parseObject(
                    line,
                    Validate::class.java
                )
                completed = true
            }
        }
    }

    override fun parseError(line: String?) {
        hasErr = true
        if (!line.isNullOrEmpty()) {
            errorBuilder.append(line.trim()).append("\n")
        }
    }
}
