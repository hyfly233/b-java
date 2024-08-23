package com.hyfly.tf.actuator.processor

import com.hyfly.tf.actuator.entity.constants.TfCommand
import org.slf4j.LoggerFactory
import java.io.ByteArrayOutputStream
import java.io.File
import java.nio.charset.StandardCharsets

class ProcessActuator {

    companion object {
        private const val TMP_WORKING_DIR = "./tmp"

        private val log = LoggerFactory.getLogger(ProcessActuator::class.java)

        @JvmStatic
        @Throws(RuntimeException::class)
        fun syncSeqExecution(commands: MutableList<TfCommand>) {
            syncSeqExecution(commands, null)
        }

        @JvmStatic
        @Throws(RuntimeException::class)
        fun syncSeqExecution(commands: MutableList<TfCommand>, workingDirPath: String?) {
            var workingDir = workingDirPath
            if (workingDir.isNullOrBlank()) {
                workingDir = TMP_WORKING_DIR
            }

            for (tfCommand in commands) {
                val command = tfCommand.getCommand()
                if (command.isEmpty()) {
                    break
                }
                val t = tfCommand.processor

                val commandStr = command.joinToString(" ")
                log.info("ProcessActuator 开始执行命令: {}", commandStr)

                val pb = ProcessBuilder(command)
                pb.directory(File(workingDir))

                // 启动进程
                val process = pb.start()

                if (tfCommand.isLineParse == true) {
                    process.inputStream
                        .bufferedReader(StandardCharsets.UTF_8)
                        .use { reader ->
                            var line: String?
                            while (reader.readLine().also { line = it } != null) {
                                t?.parse(line!!)
                            }
                        }
                } else {
                    process.inputStream
                        .use { inputStream ->
                            val buffer = ByteArray(2048)
                            var len: Int

                            ByteArrayOutputStream()
                                .use { bos ->
                                    while (inputStream.read(buffer).also { len = it } != -1) {
                                        bos.write(buffer, 0, len)
                                    }
                                    t?.parse(bos.toString(StandardCharsets.UTF_8))
                                }
                        }
                }

                process.errorStream
                    .bufferedReader(StandardCharsets.UTF_8)
                    .use { reader ->
                        var line: String?
                        while (reader.readLine().also { line = it } != null) {
                            t?.hasErr = true
                            t?.parseError(line!!)
                        }
                    }

                // 等待进程执行完成
                val exitCode = process.waitFor()
                t?.exitCode = exitCode

                if (exitCode != 0) {
                    throw RuntimeException("ProcessActuator 执行 $commandStr 异常结束, 进程退出码: $exitCode")
                } else {
                    log.info("ProcessActuator 执行 $commandStr 正常结束, 进程退出码: 0")
                }
            }
        }
    }
}
