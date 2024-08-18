package com.hyfly.tf.actuator.processor

open class BaseProcessor : IProcessor {

    var hasErr: Boolean = false

    var completed: Boolean = false

    val errorBuilder: StringBuilder = StringBuilder()

    override fun parse(line: String?) {
        println(line)
    }

    override fun parseError(line: String?) {
        println(line)
    }

    override val errMsg: String?
        get() {
            return if (hasErr) {
                errorBuilder.toString().trim()
            } else {
                null
            }
        }
}
