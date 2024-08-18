package com.hyfly.tf.actuator.processor;

import com.alibaba.fastjson2.JSON;
import com.hyfly.tf.actuator.entity.message.ChangeSummary;
import com.hyfly.tf.actuator.entity.message.Diagnostic;
import com.hyfly.tf.actuator.entity.message.MessageView;
import com.hyfly.tf.actuator.entity.message.constants.MessageLevel;
import com.hyfly.tf.actuator.entity.message.constants.MessageType;
import com.hyfly.tf.actuator.entity.plan.Plan;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Data
@Slf4j
@EqualsAndHashCode(callSuper = true)
public class PlanJsonProcessor extends BaseProcessor {

    private String planJson;

    private ChangeSummary changeSummary;

    @Override
    public void parse(String line) {
        if (completed) {
            return;
        }

        if (StringUtils.isNotBlank(line)) {
            if (line.contains("@level") && line.contains("@message") &&
                    line.contains("@module") && line.contains("@timestamp")) {
                MessageView view = JSON.parseObject(line, MessageView.class);

                if (MessageLevel.ERROR.equals(view.getLevel())) {
                    // TODO: 2023/10/7 处理错误信息
                    String message = view.getMessage();
                    Diagnostic diagnostic = view.getDiagnostic();
                    if (diagnostic != null) {
                        message = message + ". " + diagnostic.getDetail();
                    }

                    hasErr = true;
                    errorBuilder.append(message.trim()).append("\n");

                } else if (MessageLevel.INFO.equals(view.getLevel())) {
                    if (MessageType.CHANGE_SUMMARY.equals(view.getType())) {
                        ChangeSummary summary = view.getChangeSummary();
                        if (summary != null) {
                            log.info("获取 Terraform plan 命令的执行计划详情");
                            this.changeSummary = summary;
                        }
                    }
                }
            } else if (line.contains("format_version") && line.contains("terraform_version") &&
                    line.contains("planned_values") && line.contains("configuration")) {
                // 解析 tfplan 的 json 格式数据
                Plan plan = JSON.parseObject(line, Plan.class);
                if (plan != null) {
                    log.info("执行 Terraform plan 命令并生成执行计划成功");
                    completed = true;
                    planJson = line;
                }
            }
        }
    }

    @Override
    public void parseError(String line) {
        hasErr = true;
        if (StringUtils.isNotBlank(line)) {
            errorBuilder.append(line.trim()).append("\n");
        }
    }
}
