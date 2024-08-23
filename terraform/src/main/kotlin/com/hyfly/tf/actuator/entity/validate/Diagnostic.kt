package com.hyfly.tf.actuator.entity.validate

import com.alibaba.fastjson2.annotation.JSONField

class Diagnostic {
    @JSONField(name = "severity")
    var severity: String? = null

    @JSONField(name = "summary")
    var summary: String? = null

    @JSONField(name = "detail")
    var detail: String? = null

    @JSONField(name = "range")
    var range: Range? = null

    @JSONField(name = "snippet")
    var snippet: Snippet? = null
}
