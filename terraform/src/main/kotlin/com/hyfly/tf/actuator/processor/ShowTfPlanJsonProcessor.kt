package com.hyfly.tf.actuator.processor

import com.alibaba.fastjson2.JSON
import com.hyfly.tf.actuator.entity.plan.Plan
import org.slf4j.LoggerFactory

class ShowTfPlanJsonProcessor : BaseProcessor() {

    var planJson: String? = null

    private val log = LoggerFactory.getLogger(ShowTfPlanJsonProcessor::class.java)

    override fun parse(line: String?) {
        log.debug("show tf plan json parse --\n{}", line)

        if (!line.isNullOrEmpty()) {
            if (line.contains("format_version") && line.contains("terraform_version") &&
                line.contains("planned_values") && line.contains("configuration")
            ) {
                // 解析 tfplan 的 json 格式数据
                val plan = JSON.parseObject(line, Plan::class.java)
                if (plan != null) {
                    planJson = line
                }
            }
        }
    }

    override fun parseError(line: String?) {
        hasErr = true
        if (!line.isNullOrEmpty()) {
            log.error("show tf plan json parseError --\n{}", line)
            errorBuilder.append(line.trim()).append("\n")
        }
    }
}
