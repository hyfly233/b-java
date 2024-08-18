package com.hyfly.tf.actuator.processor

import com.alibaba.fastjson2.JSON
import com.hyfly.tf.actuator.entity.message.ChangeSummary
import com.hyfly.tf.actuator.entity.message.MessageView
import com.hyfly.tf.actuator.entity.message.constants.MessageLevel
import com.hyfly.tf.actuator.entity.message.constants.MessageType
import com.hyfly.tf.actuator.entity.plan.Plan
import org.slf4j.LoggerFactory

class PlanJsonProcessor : BaseProcessor() {
    var planJson: String? = null

    var changeSummary: ChangeSummary? = null

    private val log = LoggerFactory.getLogger(PlanJsonProcessor::class.java)

    override fun parse(line: String?) {
        if (this.completed) {
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
            } else if (line.contains("format_version") && line.contains("terraform_version") &&
                line.contains("planned_values") && line.contains("configuration")
            ) {
                // 解析 tfplan 的 json 格式数据
                val plan = JSON.parseObject(line, Plan::class.java)
                if (plan != null) {
                    log.info("执行 Terraform plan 命令并生成执行计划成功")
                    this.completed = true
                    planJson = line
                }
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
