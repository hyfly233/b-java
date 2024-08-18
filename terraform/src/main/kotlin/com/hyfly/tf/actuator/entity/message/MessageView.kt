package com.hyfly.tf.actuator.entity.message

import com.alibaba.fastjson2.annotation.JSONField

class MessageView {
    /**
     * info, error
     */
    @JSONField(name = "@level")
    val level: String? = null

    @JSONField(name = "@message")
    val message: String? = null

    @JSONField(name = "@module")
    val module: String? = null

    @JSONField(name = "@timestamp")
    val timestamp: String? = null

    /**
     * planned_change, change_summary
     */
    @JSONField(name = "type")
    val type: String? = null

    @JSONField(name = "changes")
    val changeSummary: ChangeSummary? = null

    @JSONField(name = "diagnostic")
    val diagnostic: Diagnostic? = null
}
