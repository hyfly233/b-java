package com.hyfly.tf.actuator.entity.message

import com.alibaba.fastjson2.JSONObject
import com.alibaba.fastjson2.annotation.JSONField

class LogView {
    /**
     * com.hyfly.tf.actuator.entity.message.constants.MessageType
     */
    @JSONField(name = "type")
    var type: String? = null

    @JSONField(name = "@message")
    var message: String? = null

    @JSONField(name = "diagnostic")
    var diagnostic: Diagnostic? = null

    @JSONField(name = "outputs")
    var outputs: JSONObject? = null

    @JSONField(name = "hook")
    var hook: JSONObject? = null

}
