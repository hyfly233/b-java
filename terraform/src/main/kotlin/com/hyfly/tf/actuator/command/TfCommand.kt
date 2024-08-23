package com.hyfly.tf.actuator.command

import com.hyfly.tf.actuator.processor.BaseProcessor

data class TfCommand(
    /**
     * 用于 ProcessActuator 是否按行解析
     */
    var isLineParse: Boolean? = false,

    /**
     * 基础命令, 例如: terraform init
     */
    var baseCommand: MutableList<String> = mutableListOf(),

    /**
     * 插件目录, 一般只给 terraform init 使用,例如: terraform init -plugin-dir=/path/to/plugin
     */
    var pluginDir: String? = null,

    /**
     * 变量, 例如: terraform plan -var="key=value"
     */
    var variables: MutableList<String>? = null,

    /**
     * 对命令执行结果进行解析的处理器
     */
    var processor: BaseProcessor? = null
) {

    companion object {
        const val VAR = "-var="
        const val PLUGIN_DIR = "-plugin-dir="
    }

    constructor(isLineParse: Boolean?, baseCommand: MutableList<String>) : this(
        isLineParse,
        baseCommand,
        null,
        null,
        null
    )

    fun setPluginDir(pluginDir: String): TfCommand {
        this.pluginDir = pluginDir
        return this
    }

    fun appendVariable(variable: String): TfCommand {
        if (this.variables == null) {
            this.variables = mutableListOf()
        }
        this.variables!!.add(variable)
        return this
    }

    fun appendVariables(vars: List<String>): TfCommand {
        if (this.variables == null) {
            this.variables = mutableListOf()
        }
        this.variables!!.addAll(vars)
        return this
    }

    fun <T : BaseProcessor> setProcessor(t: T): TfCommand {
        this.processor = t
        return this
    }

    fun getCommand(): MutableList<String> {
        val command = this.baseCommand

        if (!this.pluginDir.isNullOrBlank()) {
            command.add(PLUGIN_DIR + this.pluginDir)
        }

        val vars = this.variables
        if (!vars.isNullOrEmpty()) {
            for (variable in vars) {
                command.add(VAR + variable)
            }
        }

        return command
    }
}
