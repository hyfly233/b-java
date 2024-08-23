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
    var severity: String? = null

    @JSONField(name = "summary")
    var summary: String? = null

    @JSONField(name = "detail")
    var detail: String? = null

    @JSONField(name = "address")
    var address: Any? = null

    @JSONField(name = "range")
    var range: Any? = null

    @JSONField(name = "snippet")
    var snippet: Any? = null
}
