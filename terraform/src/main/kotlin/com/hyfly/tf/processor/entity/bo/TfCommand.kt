package com.hyfly.tf.processor.entity.bo

import org.apache.commons.lang3.StringUtils
import java.util.*

data class TfCommand(

    /**
     * 用于 ProcessActuator 是否按行解析
     */
    var isLineParse: Boolean = false,

    /**
     * 基础命令, 例如: terraform init
     */
    var baseCommand: LinkedList<String> = LinkedList(),

    /**
     * 插件目录, 一般只给 terraform init 使用,例如: terraform init -plugin-dir=/path/to/plugin
     */
    var pluginDir: String? = null,

    /**
     * 变量, 例如: terraform plan -var="key=value"
     */
    var variables: MutableList<String>? = null
) {
    companion object {
        const val _VAR = "-var="
        const val _PLUGIN_DIR = "-plugin-dir="
    }

    constructor(isLineParse: Boolean, baseCommand: LinkedList<String>) : this(isLineParse, baseCommand, null, null)

    fun appendVariable(var_: String): TfCommand {
        if (variables == null) {
            variables = LinkedList()
        }
        variables!!.add(var_)
        return this
    }

    fun appendVariables(vars: LinkedList<String>): TfCommand {
        if (variables == null) {
            variables = LinkedList()
        }
        variables!!.addAll(vars)
        return this
    }

    fun getCommand(): Array<String> {
        val command = LinkedList(baseCommand)

        if (StringUtils.isNotBlank(pluginDir)) {
            command.add(_PLUGIN_DIR + pluginDir)
        }

        variables?.let { vars ->
            if (vars.isNotEmpty()) {
                vars.forEach { s ->
                    command.add(_VAR + s)
                }
            }
        }

        return command.toTypedArray()
    }

}
