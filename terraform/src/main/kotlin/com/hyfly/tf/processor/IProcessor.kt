package com.hyfly.tf.processor

interface IProcessor {

    fun parse(line: String?)

    fun parseError(line: String?)

    fun getErrMsg(): String?
}
