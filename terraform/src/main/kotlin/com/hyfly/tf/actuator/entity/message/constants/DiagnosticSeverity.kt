package com.hyfly.tf.actuator.entity.message.constants

/**
 * github.com/hashicorp/terraform/json/diagnostic.go
 */
object DiagnosticSeverity {
    const val UNKNOWN: String = "unknown"
    const val ERROR: String = "error"
    const val WARNING: String = "warning"
    const val INFO: String = "info"
    const val HINT: String = "hint"
}
