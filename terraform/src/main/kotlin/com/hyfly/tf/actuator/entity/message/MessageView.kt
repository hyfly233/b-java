package com.hyfly.tf.actuator.entity.message

import com.alibaba.fastjson2.annotation.JSONField

class MessageView {
    /**
     * info, error
     */
    @JSONField(name = "@level")
    var level: String? = null

    @JSONField(name = "@message")
    var message: String? = null

    @JSONField(name = "@module")
    var module: String? = null

    @JSONField(name = "@timestamp")
    var timestamp: String? = null

    /**
     * planned_change, change_summary
     */
    @JSONField(name = "type")
    var type: String? = null

    @JSONField(name = "changes")
    var changeSummary: ChangeSummary? = null

    @JSONField(name = "diagnostic")
    var diagnostic: Diagnostic? = null

    fun validate(): Boolean {
        if (level.isNullOrBlank()) return false
        if (message.isNullOrBlank()) return false
        if (module.isNullOrBlank()) return false
        if (timestamp.isNullOrBlank()) return false
        if (type.isNullOrBlank()) return false
        return true
    }

    fun handleError(): String {
        val errors = mutableListOf<String>()
        if (level.isNullOrBlank()) errors.add("Invalid level")
        if (message.isNullOrBlank()) errors.add("Invalid message")
        if (module.isNullOrBlank()) errors.add("Invalid module")
        if (timestamp.isNullOrBlank()) errors.add("Invalid timestamp")
        if (type.isNullOrBlank()) errors.add("Invalid type")
        return errors.joinToString(", ")
    }
}
