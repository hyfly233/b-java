package com.hyfly.tf.actuator.service.impl

import cn.hutool.core.io.FileUtil
import com.hyfly.tf.actuator.command.CommandActuator
import com.hyfly.tf.actuator.command.TfCommandFactory
import com.hyfly.tf.actuator.processor.*
import com.hyfly.tf.actuator.service.ITfPlanService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.File

@Service
class TfPlanServiceImpl : ITfPlanService {

    companion object {
        const val WORK_DIR: String = "./terraform_test"

        const val PLUGIN_DIR: String = "./plugins"
    }

    private val log = LoggerFactory.getLogger(TfPlanServiceImpl::class.java)

    override fun initAndPlan(): Map<String, Any> {
        // 创建工作目录
        val workspacePathFile = File(WORK_DIR)
        mkdir(workspacePathFile)

        // 判断插件目录是否存在
        val pluginPathFile = File(PLUGIN_DIR)
        if (!pluginPathFile.exists()) {
            log.error("插件目录不存在")
            return mapOf("msg" to "插件目录不存在")
        } else {
            val path = pluginPathFile.absolutePath
            log.info("获取 terraform 插件路径结束，路径：{}", path)

            var hasErrFlag = false
            val eb: StringBuilder = StringBuilder()

            // 初始化 processor
            val ip = InitProcessor()
            val pp = PlanJsonProcessor()
            val sp = ShowTfPlanJsonProcessor()

            try {
                // 执行 init、plan、show 命令
                val commands = mutableListOf(
                    TfCommandFactory.init()
                        .setPluginDir(path)
                        .setProcessor(ip),
                    TfCommandFactory.plan2Json()
                        .setProcessor(pp),
                    TfCommandFactory.showPlanJson()
                        .setProcessor(sp)
                )

                CommandActuator.syncSeqExecution(commands, WORK_DIR)
            } catch (e: Exception) {
                hasErrFlag = true

                log.error("\n执行 terraform init/plan/show 失败, 错误详情 \n{}", e.message)

                if (ip.hasErr) {
                    log.error("\n执行 terraform init 失败，错误信息：{}\n", ip.errMsg)
                    eb.append(ip.errMsg)
                }

                if (pp.hasErr) {
                    log.error("\n执行 terraform plan 失败，错误信息：\n{}", pp.errMsg)
                    eb.append(pp.errMsg)
                }

                if (sp.hasErr) {
                    log.error("\n执行 terraform show 失败，错误信息：\n{}", sp.errMsg)
                    eb.append(sp.errMsg)
                }
            }

            if (!hasErrFlag) {
                log.info("执行 terraform init/plan/show 成功")

                // 设置 plan 摘要
                val summary = pp.changeSummary

                val planJson = sp.planJson
                if (planJson.isNullOrBlank()) {
                    throw RuntimeException("terraform init/plan/show 生成的 planJson 为空")
                }

                return buildMap {
                    put("msg", "执行 terraform init/plan/show 成功")
                    summary?.let {
                        put("summary", summary)
                    }
                    put("planJson", planJson)
                }
            } else {
                return buildMap {
                    put("msg", "执行 terraform init/plan/show 失败")
                    put("error", eb.toString())
                }
            }
        }
    }

    override fun apply(): Map<String, Any> {
        var hasErrFlag = false
        val eb: StringBuilder = StringBuilder()

        // 初始化 processor
        val ajp = ApplyJsonProcessor()
        val sp = ShowTfPlanJsonProcessor()

        try {
            // 执行 apply、show 命令
            val commands = mutableListOf(
                TfCommandFactory.apply()
                    .setProcessor(ajp),
                TfCommandFactory.show()
                    .setProcessor(sp)
            )

            CommandActuator.syncSeqExecution(commands, WORK_DIR)
        } catch (e: Exception) {
            hasErrFlag = true

            log.error("\n执行 terraform apply/show 失败, 错误详情 \n{}", e.message)

            if (ajp.hasErr) {
                log.error("\n执行 terraform apply 失败，错误信息：\n{}", ajp.errMsg)
                eb.append(ajp.errMsg)
            }

            if (sp.hasErr) {
                log.error("\n执行 terraform show 失败，错误信息：\n{}", sp.errMsg)
                eb.append(sp.errMsg)
            }
        }

        if (!hasErrFlag) {
            return buildMap {
                put("msg", "执行 terraform apply/show 成功")
                put("error", eb.toString())
            }
        } else {
            return buildMap {
                put("msg", "执行 terraform apply/show 失败")
                put("error", eb.toString())
            }
        }
    }

    override fun destroy(): Map<String, Any> {
        var hasErrFlag = false
        val eb: StringBuilder = StringBuilder()

        // 初始化 processor
        val djp = DestroyJsonProcessor()
        val sp = ShowTfPlanJsonProcessor()

        try {
            // 执行 destroy、show 命令
            val commands = mutableListOf(
                TfCommandFactory.destroy()
                    .setProcessor(djp),
                TfCommandFactory.show()
                    .setProcessor(sp)
            )

            CommandActuator.syncSeqExecution(commands, WORK_DIR)
        } catch (e: Exception) {
            hasErrFlag = true

            log.error("\n执行 terraform destroy/show 失败, 错误详情 \n{}", e.message)

            if (djp.hasErr) {
                log.error("\n执行 terraform destroy 失败，错误信息：\n{}", djp.errMsg)
                eb.append(djp.errMsg)
            }

            if (sp.hasErr) {
                log.error("\n执行 terraform show 失败，错误信息：\n{}", sp.errMsg)
                eb.append(sp.errMsg)
            }
        }

        if (!hasErrFlag) {
            return buildMap {
                put("msg", "执行 terraform destroy/show 成功")
                put("error", eb.toString())
            }
        } else {
            return buildMap {
                put("msg", "执行 terraform destroy/show 失败")
                put("error", eb.toString())
            }
        }
    }

    private fun mkdir(pathFile: File?) {
        if (pathFile != null && !pathFile.exists()) {
            FileUtil.mkdir(pathFile)
        }
    }
}
