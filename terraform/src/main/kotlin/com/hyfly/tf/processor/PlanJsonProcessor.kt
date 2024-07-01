package com.hyfly.tf.processor

import com.alibaba.fastjson2.JSON
import com.hyfly.tf.processor.entity.constants.MessageLevel
import com.hyfly.tf.processor.entity.constants.MessageType
import com.hyfly.tf.processor.entity.message.ChangeSummary
import com.hyfly.tf.processor.entity.message.MessageView
import com.hyfly.tf.processor.entity.plan.Plan
import org.apache.commons.lang3.StringUtils
import org.slf4j.LoggerFactory

data class PlanJsonProcessor(
    var planJson: String? = null,

    var changeSummary: ChangeSummary? = null
) : BaseProcessor() {

    companion object {
        private val log = LoggerFactory.getLogger(PlanJsonProcessor::class.java)
    }

    override fun parse(line: String?) {
        if (completed) return

        if (StringUtils.isNotBlank(line)) {
            if (line!!.contains("@level") && line.contains("@message") &&
                line.contains("@module") && line.contains("@timestamp")
            ) {
                val view = JSON.parseObject(line, MessageView::class.java)

                when (view.level) {
                    MessageLevel.ERROR -> {
                        var message = view.message ?: ""
                        view.diagnostic?.let {
                            message += ". ${it.detail}"
                        }

                        hasErr = true
                        errorBuilder.append(message.trim()).append("\n")
                    }

                    MessageLevel.INFO -> {
                        if (MessageType.CHANGE_SUMMARY == view.type) {
                            view.changeSummary?.let {
                                log.info("获取 Terraform plan 命令的执行计划详情")
                                this.changeSummary = it
                            }
                        }
                    }
                }
            } else if (line.contains("format_version") && line.contains("terraform_version") &&
                line.contains("planned_values") && line.contains("configuration")
            ) {
                JSON.parseObject(line, Plan::class.java)?.let {
                    log.info("执行 Terraform plan 命令并生成执行计划成功")
                    completed = true
                    planJson = line
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
