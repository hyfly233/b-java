package com.hyfly.tf.actuator.processor;

import com.hyfly.tf.actuator.entity.constants.TfCommand;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

@Slf4j
public class ProcessActuator {

    public static final String TMP_WORKING_DIR = "./tmp";

    public static <T extends BaseProcessor> void syncSeqExecution(LinkedList<TfCommand> commands, T t) throws Exception {
        syncSeqExecution(commands, null, t);
    }

    public static <T extends BaseProcessor> void syncSeqExecution(LinkedList<TfCommand> commands, String workingDirPath, T t) throws Exception {
        if (StringUtils.isBlank(workingDirPath)) {
            workingDirPath = TMP_WORKING_DIR;
        }

        for (TfCommand tfCommand : commands) {
            String[] command = tfCommand.getCommand();
            if (command.length == 0) {
                break;
            }
            String commandStr = String.join(" ", command);
            log.info("ProcessActuator 开始执行命令: {}", commandStr);

            ProcessBuilder pb = new ProcessBuilder(command);
            pb.directory(new File(workingDirPath));
            Process process = pb.start();

            if (tfCommand.getIsLineParse()) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (t != null) {
                            t.parse(line);
                        }
                    }
                }
            } else {
                try (InputStream inputStream = process.getInputStream()) {
                    byte[] buffer = new byte[2048];
                    int len;
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    while ((len = inputStream.read(buffer)) != -1) {
                        bos.write(buffer, 0, len);
                    }
                    bos.close();
                    if (t != null) {
                        t.parse(bos.toString(StandardCharsets.UTF_8));
                    }
                }
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (t != null) {
                        t.parseError(line);
                    }
                }
            }
            process.waitFor();
            if (t != null && t.isHasErr()) {
                log.error("执行命令出错: {}, 错误详情: {}", commandStr, t.getErrMsg());
                break;
            }
        }
    }
}
