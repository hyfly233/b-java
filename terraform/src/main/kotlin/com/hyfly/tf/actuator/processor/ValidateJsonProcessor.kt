package com.hyfly.tf.actuator.processor

import com.alibaba.fastjson2.JSON
import com.hyfly.tf.actuator.entity.message.MessageView
import com.hyfly.tf.actuator.entity.message.constants.MessageLevel
import com.hyfly.tf.actuator.entity.validate.Validate
import org.slf4j.LoggerFactory

class ValidateJsonProcessor : BaseProcessor() {
    private var validate: Validate? = null

    private val log = LoggerFactory.getLogger(ValidateJsonProcessor::class.java)

    override fun parse(line: String?) {
        log.debug("validate json parse --\n{}", line)

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

                validate?.let {
                    if (it.errorCount != null && it.errorCount!! > 0) {
                        hasErr = true

                        validate!!.diagnostics?.forEach { diagnostic ->
                            diagnostic.snippet?.let { snippet ->
                                errorBuilder.append(snippet.context)
                            }

                            errorBuilder.append(diagnostic.summary)
                                .append(" ")
                                .append(diagnostic.detail)
                                .append("\n")
                        }
                    }
                }
            }
        }
    }

    override fun parseError(line: String?) {
        hasErr = true
        if (!line.isNullOrEmpty()) {
            log.error("validate json parseError --\n{}", line)
            errorBuilder.append(line.trim()).append("\n")
        }
    }
}
