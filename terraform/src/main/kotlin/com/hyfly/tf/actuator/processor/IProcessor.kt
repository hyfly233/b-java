package com.hyfly.tf.actuator.processor

interface IProcessor {

    /**
     * 解析输出
     */
    fun parse(line: String?)

    /**
     * 解析错误
     */
    fun parseError(line: String?)

    /**
     * 错误信息
     */
    val errMsg: String?
}
