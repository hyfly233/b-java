package com.hyfly.tf.actuator.processor

import com.alibaba.fastjson2.JSON
import com.hyfly.tf.actuator.entity.message.ChangeSummary
import com.hyfly.tf.actuator.entity.message.MessageView
import com.hyfly.tf.actuator.entity.message.constants.MessageLevel
import org.slf4j.LoggerFactory

class ApplyJsonProcessor : BaseProcessor() {

    var changeSummary: ChangeSummary? = null

    private val log = LoggerFactory.getLogger(ApplyJsonProcessor::class.java)

    override fun parse(line: String?) {
        log.debug("apply json parse --\n{}", line)

        if (!line.isNullOrEmpty()) {
            if (line.contains("hook")) {
                // 处理 log 信息

            } else if (line.contains("@level") && line.contains("@message") &&
                line.contains("@module") && line.contains("@timestamp")
            ) {
                // 处理 message 信息
                val view = JSON.parseObject(line, MessageView::class.java)

                if (view != null) {
                    if (MessageLevel.ERROR == view.level) {
                        hasErr = true

                        var message = view.message
                        val diagnostic = view.diagnostic

                        if (!message.isNullOrBlank()) {
                            if (diagnostic != null) {
                                message = message + ". " + diagnostic.detail
                            }

                            errorBuilder.append(message.trim()).append("\n")
                        }
                    } else if (MessageLevel.INFO == view.level) {
                        if (view.type == "change_summary") {
                            val summary = view.changeSummary
                            if (summary != null) {
                                log.info("获取 Terraform apply 命令的执行计划详情")
                                this.changeSummary = summary
                            }
                        }
                    }
                }
            }
        }
    }

    override fun parseError(line: String?) {
        hasErr = true

        if (!line.isNullOrEmpty()) {
            log.error("apply json parseError --\n{}", line)
            errorBuilder.append(line.trim()).append("\n")
        }
    }
}
