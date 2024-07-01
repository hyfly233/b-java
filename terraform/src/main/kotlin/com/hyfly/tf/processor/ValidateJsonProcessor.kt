package com.hyfly.tf.processor

import com.alibaba.fastjson2.JSON
import com.hyfly.tf.processor.entity.constants.MessageLevel
import com.hyfly.tf.processor.entity.message.Diagnostic
import com.hyfly.tf.processor.entity.message.MessageView
import com.hyfly.tf.processor.entity.validate.Validate
import org.apache.commons.lang3.StringUtils

data class ValidateJsonProcessor(
    var validate: Validate? = null
) : BaseProcessor() {

    override fun parse(line: String?) {
        if (completed) {
            return
        }

        if (StringUtils.isNotBlank(line)) {
            if (line!!.contains("@level") && line.contains("@message") &&
                line.contains("@module") && line.contains("@timestamp")
            ) {
                val view: MessageView = JSON.parseObject(line, MessageView::class.java)

                if (MessageLevel.ERROR == view.level) {
                    var message: String? = view.message
                    val diagnostic: Diagnostic? = view.diagnostic

                    if (message != null) {
                        if (diagnostic != null) {
                            message = message + ". " + diagnostic.detail
                        }

                        hasErr = true
                        errorBuilder.append(message.trim()).append("\n")
                    }
                }
            } else if (line.contains("error_count") && line.contains("warning_count")) {
                validate = JSON.parseObject(line, Validate::class.java)
                completed = true
            }
        }
    }

    override fun parseError(line: String?) {
        hasErr = true
        if (StringUtils.isNotBlank(line)) {
            errorBuilder.append(line!!.trim()).append("\n")
        }
    }
}
