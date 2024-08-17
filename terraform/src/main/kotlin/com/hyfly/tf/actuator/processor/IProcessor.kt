package com.hyfly.tf.actuator.processor

interface IProcessor {

    fun parse(line: String?)

    fun parseError(line: String?)

    val errMsg: String?
}
