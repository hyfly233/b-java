package com.hyfly.tf.processor

open class BaseProcessor(

    /**
     * 是否有错误
     */
    protected var hasErr: Boolean = false,

    /**
     * 是否已完成
     */
    protected var completed: Boolean = false,

    /**
     * 错误信息
     */
    protected var errorBuilder: StringBuilder = StringBuilder()
) : IProcessor {

    override fun parse(line: String?) {
        line?.let { println(it) }
    }

    override fun parseError(line: String?) {
        line?.let { println(it) }
    }

    override fun getErrMsg(): String? {
        if (hasErr) {
            return errorBuilder.toString()
        }

        return null
    }
}
