package com.hyfly.tf.actuator.service.impl

import cn.hutool.core.io.FileUtil
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

    override fun initAndPlan() {
        // 创建工作目录
        val workspacePathFile = File(WORK_DIR)
        mkdir(workspacePathFile)

        // 判断插件目录是否存在
        val pluginPathFile = File(PLUGIN_DIR)
        if (!pluginPathFile.exists()) {
            log.error("插件目录不存在")
            return
        } else {
            val path = pluginPathFile.absolutePath
            log.info("获取 terraform 插件路径结束，路径：{}", path)

            val initProcessor = InitProcessor()
            val planProcessor = PlanJsonProcessor()
            val showProcessor = ShowTfPlanJsonProcessor()

            try {
                // 执行 init、plan、show 命令
                val commands = mutableListOf(
                    TfCommandFactory.createBaseInit()
                        .setPluginDir(path)
                        .setProcessor(initProcessor),
                    TfCommandFactory.createBasePlan2Json()
                        .setProcessor(planProcessor),
                    TfCommandFactory.createBaseShowPlanJson()
                        .setProcessor(showProcessor)
                )

                ProcessActuator.syncSeqExecution(commands, WORK_DIR)
            } catch (e: Exception) {
                log.debug("执行 terraform init/plan 失败", e)

                planProcessor.hasErr = true
                planProcessor.errorBuilder.append(e.message)
            }

//            if (!processor.completed) {
//                val msg = "Terraform 命令未完成, 错误详情:" + processor.errMsg
//                log.error(msg)
//            } else {
//                if (processor.hasErr) {
//                    // plan 的异常处理
//                    val error = processor.errMsg
//                    log.error("执行 terraform plan 失败，错误信息：{}", error)
//                } else {
//                    log.info("执行 terraform plan 成功")
//
//                    val planJson = showProcessor.planJson
//                    if (StringUtils.isBlank(planJson)) {
//                        throw RuntimeException("planJson 为空")
//                    }
//
//                    // 设置 plan 摘要
//                    val summary = planProcessor.changeSummary
//                    if (summary != null) {
//                        log.info("\nTerraform plan 命令的执行的概要:\n {} \n -------", summary)
//                    }
//                }
//            }
        }
    }

    override fun apply() {
//        // 执行 apply、show 命令
//        val commands = mutableListOf(
//            TfCommandFactory.createBaseApply(),
//            TfCommandFactory.createBaseShowPlanJson()
//        )
//
//        val processor = ApplyJsonProcessor()
//
//        try {
//            ProcessActuator.syncSeqExecution(commands, WORK_DIR)
//        } catch (e: Exception) {
//            log.error("执行 terraform 失败", e)
//            processor.hasErr = true
//            processor.errorBuilder.append(e.message)
//        }
//
//        if (!processor.completed) {
//            val msg = "Terraform 命令未完成, 错误详情:" + processor.errMsg
//            log.error(msg)
//        } else {
//            if (processor.hasErr) {
//                // plan 的异常处理
//                val error = processor.errMsg
//                log.error("执行 terraform apply 失败，错误信息：{}", error)
//            } else {
//                log.info("执行 terraform apply 成功")
//
//                // 设置 plan 摘要
//                val summary = processor.changeSummary
//                if (summary != null) {
//                    log.info("\nTerraform plan 命令的执行的概要:\n {} \n -------", summary)
//                }
//            }
//        }
    }

    override fun destroy() {
//        // 执行 destroy、show 命令
//        val commands = mutableListOf(
//            TfCommandFactory.createBaseDestroy(),
//            TfCommandFactory.createBaseShowPlanJson()
//        )
//
//        val processor = PlanJsonProcessor()
//
//        try {
//            ProcessActuator.syncSeqExecution(commands, WORK_DIR)
//        } catch (e: Exception) {
//            log.error("执行 terraform 失败", e)
//            processor.hasErr = true
//            processor.errorBuilder.append(e.message)
//        }
//
//        if (!processor.completed) {
//            val msg = "Terraform 命令未完成, 错误详情:" + processor.errMsg
//            log.error(msg)
//        } else {
//            if (processor.hasErr) {
//                // plan 的异常处理
//                val error = processor.errMsg
//                log.error("执行 terraform apply 失败，错误信息：{}", error)
//            } else {
//                log.info("执行 terraform apply 成功")
//            }
//        }
    }

    private fun mkdir(pathFile: File?) {
        if (pathFile != null && !pathFile.exists()) {
            FileUtil.mkdir(pathFile)
        }
    }
}
