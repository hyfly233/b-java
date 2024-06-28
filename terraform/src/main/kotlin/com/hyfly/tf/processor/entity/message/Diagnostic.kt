package com.hyfly.tf.processor.entity.message

import com.alibaba.fastjson2.annotation.JSONField

data class Diagnostic(
    
    /**
     * unknown, error, warning
     * [com.hyfly.tf.processor.entity.constants.DiagnosticSeverity]
     */
    @JSONField(name = "severity")
    var severity: String? = null,

    @JSONField(name = "summary")
    var summary: String? = null,

    @JSONField(name = "detail")
    var detail: String? = null,

    @JSONField(name = "address")
    var address: Any? = null,

    @JSONField(name = "range")
    var range: Any? = null,

    @JSONField(name = "snippet")
    var snippet: Any? = null
)
