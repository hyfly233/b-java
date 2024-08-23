package com.hyfly.tf.actuator.entity.validate

import com.alibaba.fastjson2.annotation.JSONField

class Validate {
    @JSONField(name = "valid")
    var valid: Boolean? = null

    @JSONField(name = "error_count")
    var errorCount: Int? = null

    @JSONField(name = "warning_count")
    var warningCount: Int? = null

    @JSONField(name = "diagnostics")
    var diagnostics: MutableList<Diagnostic>? = null
}
