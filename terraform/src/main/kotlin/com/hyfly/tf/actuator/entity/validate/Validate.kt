package com.hyfly.tf.actuator.entity.validate

import com.alibaba.fastjson2.annotation.JSONField

class Validate {
    @JSONField(name = "valid")
    val valid: Boolean? = null

    @JSONField(name = "error_count")
    val errorCount: Int? = null

    @JSONField(name = "warning_count")
    val warningCount: Int? = null

    @JSONField(name = "diagnostics")
    val diagnostics: MutableList<Diagnostic>? = null
}
