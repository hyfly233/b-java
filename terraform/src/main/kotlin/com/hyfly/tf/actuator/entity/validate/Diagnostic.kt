package com.hyfly.tf.actuator.entity.validate

import com.alibaba.fastjson2.annotation.JSONField

class Diagnostic {
    @JSONField(name = "severity")
    val severity: String? = null

    @JSONField(name = "summary")
    val summary: String? = null

    @JSONField(name = "detail")
    val detail: String? = null

    @JSONField(name = "range")
    val range: Range? = null

    @JSONField(name = "snippet")
    val snippet: Snippet? = null
}
