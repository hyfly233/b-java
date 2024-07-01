package com.hyfly.tf.processor.entity.validate

import com.alibaba.fastjson2.annotation.JSONField

data class Diagnostic(
    @JSONField(name = "severity")
    var severity: String? = null,

    @JSONField(name = "summary")
    var summary: String? = null,

    @JSONField(name = "detail")
    var detail: String? = null,

    @JSONField(name = "range")
    var range: Range? = null,

    @JSONField(name = "snippet")
    var snippet: Snippet? = null
)
