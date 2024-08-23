package com.hyfly.tf.actuator.processor

/**
 * exitCode != 0:
 * == 进程异常退出，认定为执行未完成、执行失败
 * exitCode == 0:
 * == 进程正常退出，不一定代表执行成功，需要根据输出内容判断
 * === 1.
 */
open class BaseProcessor : IProcessor {

    /**
     * ProcessBuilder 进程的退出码,0 为正常退出
     */
    var exitCode: Int = -1

    /**
     * 是否有错误
     */
    var hasErr: Boolean = false

    /**
     *  错误信息
     */
    val errorBuilder: StringBuilder = StringBuilder()

    override fun parse(line: String?) {
    }

    override fun parseError(line: String?) {
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
