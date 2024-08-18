package com.hyfly.tf.actuator.entity.message

import com.alibaba.fastjson2.annotation.JSONField

/**
 * github.com/hashicorp/terraform/json/diagnostic.go
 */
class Diagnostic {
    /**
     * unknown, error, warning
     */
    @JSONField(name = "severity")
    val severity: String? = null

    @JSONField(name = "summary")
    val summary: String? = null

    @JSONField(name = "detail")
    val detail: String? = null

    @JSONField(name = "address")
    val address: Any? = null

    @JSONField(name = "range")
    val range: Any? = null

    @JSONField(name = "snippet")
    val snippet: Any? = null
}
