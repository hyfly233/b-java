package com.hyfly.tf.actuator.processor

import org.slf4j.LoggerFactory

class InitProcessor : BaseProcessor() {

    private val log = LoggerFactory.getLogger(InitProcessor::class.java)

    override fun parse(line: String?) {
        log.debug("init parse --\n{}", line)
    }

    override fun parseError(line: String?) {
        log.error("init parseError --\n{}", line)

        hasErr = true
        if (!line.isNullOrEmpty()) {
            errorBuilder.append(line.trim()).append("\n")
        }
    }
}
