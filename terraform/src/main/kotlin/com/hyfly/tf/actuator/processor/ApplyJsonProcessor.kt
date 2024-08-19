package com.hyfly.tf.actuator.processor

import com.hyfly.tf.actuator.entity.message.ChangeSummary
import org.slf4j.LoggerFactory

class ApplyJsonProcessor : BaseProcessor() {

    var changeSummary: ChangeSummary? = null

    private val log = LoggerFactory.getLogger("ApplyJsonProcessor")

    override fun parse(line: String?) {

    }

    override fun parseError(line: String?) {

    }

}
