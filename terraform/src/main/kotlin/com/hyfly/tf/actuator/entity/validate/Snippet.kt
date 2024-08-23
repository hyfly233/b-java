package com.hyfly.tf.actuator.entity.validate

import com.alibaba.fastjson2.annotation.JSONField

class Snippet {
    @JSONField(name = "context")
    var context: String? = null

    @JSONField(name = "code")
    var code: String? = null

    @JSONField(name = "start_line")
    var startLine: Int? = null
}
