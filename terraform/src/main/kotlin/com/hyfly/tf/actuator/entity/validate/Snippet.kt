package com.hyfly.tf.actuator.entity.validate

import com.alibaba.fastjson2.annotation.JSONField

class Snippet {
    @JSONField(name = "context")
    val context: String? = null

    @JSONField(name = "code")
    val code: String? = null

    @JSONField(name = "start_line")
    val startLine: Int? = null
}
