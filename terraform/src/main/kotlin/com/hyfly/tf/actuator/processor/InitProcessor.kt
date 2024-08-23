package com.hyfly.tf.actuator.processor

import org.slf4j.LoggerFactory

class InitProcessor : BaseProcessor() {

    private val log = LoggerFactory.getLogger(InitProcessor::class.java)

    /**
     * terraform init 的错误信息可直接由 parseError 方法解析
     */
    override fun parseError(line: String?) {
        hasErr = true

        if (!line.isNullOrEmpty()) {
            log.error("init parseError --\n{}", line)
            errorBuilder.append(line.trim()).append("\n")
        }
    }
}
