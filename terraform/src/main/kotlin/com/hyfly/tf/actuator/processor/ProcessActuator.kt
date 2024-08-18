package com.hyfly.tf.actuator.processor

import com.hyfly.tf.actuator.entity.constants.TfCommand
import org.apache.commons.lang3.StringUtils
import org.slf4j.LoggerFactory
import java.io.ByteArrayOutputStream
import java.io.File
import java.nio.charset.StandardCharsets

class ProcessActuator {

    companion object {
        private const val TMP_WORKING_DIR = "./tmp"
        private val log = LoggerFactory.getLogger(ProcessActuator::class.java)

        @JvmStatic
        fun <T : BaseProcessor> syncSeqExecution(commands: MutableList<TfCommand>, t: T) {
            syncSeqExecution(commands, null, t)
        }

        @JvmStatic
        fun <T : BaseProcessor> syncSeqExecution(commands: MutableList<TfCommand>, workingDirPath: String?, t: T) {
            var workingDir = workingDirPath
            if (StringUtils.isBlank(workingDir)) {
                workingDir = TMP_WORKING_DIR
            }

            for (tfCommand in commands) {
                val command = tfCommand.getCommand()
                if (command.isEmpty()) {
                    break
                }
                val commandStr = command.joinToString(" ")
                log.info("ProcessActuator 开始执行命令: {}", commandStr)

                val pb = ProcessBuilder(command)
                pb.directory(File(workingDir!!))
                val process = pb.start()

                if (tfCommand.isLineParse == true) {
                    process.inputStream
                        .bufferedReader(StandardCharsets.UTF_8)
                        .use { reader ->
                            var line: String?
                            while (reader.readLine().also { line = it } != null) {
                                t.parse(line!!)
                            }
                        }
                } else {
                    process.inputStream
                        .use { inputStream ->
                            val buffer = ByteArray(2048)
                            var len: Int
                            val bos = ByteArrayOutputStream()
                            while (inputStream.read(buffer).also { len = it } != -1) {
                                bos.write(buffer, 0, len)
                            }
                            bos.close()
                            t.parse(bos.toString(StandardCharsets.UTF_8))
                        }
                }

                process.errorStream
                    .bufferedReader(StandardCharsets.UTF_8)
                    .use { reader ->
                        var line: String?
                        while (reader.readLine().also { line = it } != null) {
                            t.parseError(line!!)
                        }
                    }
                process.waitFor()
                if (t.hasErr) {
                    log.error("执行命令出错: {}, 错误详情: {}", commandStr, t.errMsg)
                    break
                }
            }
        }
    }
}
