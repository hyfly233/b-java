package com.hyfly.tf.actuator.processor

import com.alibaba.fastjson2.JSON
import com.hyfly.tf.actuator.entity.message.ChangeSummary
import com.hyfly.tf.actuator.entity.message.MessageView
import com.hyfly.tf.actuator.entity.message.constants.MessageLevel
import com.hyfly.tf.actuator.entity.message.constants.MessageType
import org.slf4j.LoggerFactory

class PlanJsonProcessor : BaseProcessor() {

    var changeSummary: ChangeSummary? = null

    private val log = LoggerFactory.getLogger(PlanJsonProcessor::class.java)

    /**
     * 解析 plan 命令的输出
     * 包括执行计划细节、错误信息和执行计划概要等
     */
    override fun parse(line: String?) {
        log.debug("plan json parse --\n{}", line)

        if (this.exitCode == 0) {
            return
        }

        if (!line.isNullOrEmpty()) {
            if (line.contains("@level") && line.contains("@message") &&
                line.contains("@module") && line.contains("@timestamp")
            ) {
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
                        if (MessageType.CHANGE_SUMMARY == view.type) {
                            val summary = view.changeSummary
                            if (summary != null) {
                                log.info("获取 Terraform plan 命令的执行计划详情")
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
            log.error("plan json parseError --\n{}", line)
            errorBuilder.append(line.trim()).append("\n")
        }
    }
}
