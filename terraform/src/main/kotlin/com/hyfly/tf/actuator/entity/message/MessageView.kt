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
}
