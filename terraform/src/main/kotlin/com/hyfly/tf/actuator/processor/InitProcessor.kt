package com.hyfly.tf.actuator.processor

import org.slf4j.LoggerFactory

class InitProcessor : BaseProcessor() {

    private val log = LoggerFactory.getLogger(InitProcessor::class.java)

    /**
     * terraform init 的错误信息可直接由 parseError 方法解析
     */
    override fun parseError(line: String?) {
        log.error("init parseError --\n{}", line)

        hasErr = true
        if (!line.isNullOrEmpty()) {
            errorBuilder.append(line.trim()).append("\n")
        }
    }
}
