package com.hyfly.tf.processor

import com.alibaba.fastjson2.JSON
import com.hyfly.tf.processor.entity.constants.MessageLevel
import com.hyfly.tf.processor.entity.constants.MessageType
import com.hyfly.tf.processor.entity.message.Diagnostic
import com.hyfly.tf.processor.entity.message.MessageView
import org.apache.commons.lang3.StringUtils
import java.util.*

class ApplyJsonProcessor : BaseProcessor() {

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
                } else if (MessageLevel.INFO == view.level) {
                    if (MessageType.CHANGE_SUMMARY == view.type) {
                        var message: String? = view.message
                        if (StringUtils.isNotBlank(message)) {
                            message = message!!.lowercase(Locale.getDefault())
                            if (message.contains("apply complete")) {
                                completed = true
                            }
                        }
                    }
                }
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
