package com.hyfly.tf.actuator.processor

import com.alibaba.fastjson2.JSON
import com.hyfly.tf.actuator.entity.message.ChangeSummary
import com.hyfly.tf.actuator.entity.plan.Plan
import org.slf4j.LoggerFactory

class ShowTfPlanJsonProcessor : BaseProcessor() {

    var planJson: String? = null

    private val log = LoggerFactory.getLogger(ShowTfPlanJsonProcessor::class.java)

    override fun parse(line: String?) {
        log.debug(line)

        if (this.completed) {
            return
        }

        if (!line.isNullOrEmpty()) {
            if (line.contains("format_version") && line.contains("terraform_version") &&
                line.contains("planned_values") && line.contains("configuration")
            ) {
                // 解析 tfplan 的 json 格式数据
                val plan = JSON.parseObject(line, Plan::class.java)
                if (plan != null) {
                    log.info("执行 Terraform plan 命令并生成执行计划成功")

                    // todo 判断标准不太准确
                    this.completed = true
                    planJson = line
                }
            }
        }
    }

    override fun parseError(line: String?) {
        log.error(line)

        hasErr = true
        if (!line.isNullOrEmpty()) {
            errorBuilder.append(line.trim()).append("\n")
        }
    }
}
