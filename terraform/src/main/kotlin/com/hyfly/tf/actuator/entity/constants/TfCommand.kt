package com.hyfly.tf.actuator.entity.constants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;
import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class TfCommand {

    public static final String _VAR = "-var=";

    public static final String _PLUGIN_DIR = "-plugin-dir=";

    /**
     * 用于 ProcessActuator 是否按行解析
     */
    private Boolean isLineParse;

    /**
     * 基础命令, 例如: terraform init
     */
    private LinkedList<String> baseCommand;

    /**
     * 插件目录, 一般只给 terraform init 使用,例如: terraform init -plugin-dir=/path/to/plugin
     */
    private String pluginDir;

    /**
     * 变量, 例如: terraform plan -var="key=value"
     */
    private List<String> variables;

    public TfCommand(Boolean isLineParse, LinkedList<String> baseCommand) {
        this.isLineParse = isLineParse;
        this.baseCommand = baseCommand;
    }

    public TfCommand appendVariable(String var) {
        if (this.variables == null) {
            this.variables = new LinkedList<>();
        }
        this.variables.add(var);
        return this;
    }

    public TfCommand appendVariables(LinkedList<String> vars) {
        if (this.variables == null) {
            this.variables = new LinkedList<>();
        }
        this.variables.addAll(vars);
        return this;
    }

    public String[] getCommand() {
        LinkedList<String> command = new LinkedList<>(this.baseCommand);

        if (StringUtils.isNotBlank(this.pluginDir)) {
            command.add(_PLUGIN_DIR + this.pluginDir);
        }

        List<String> vars = this.variables;
        if (vars != null && !vars.isEmpty()) {
            for (String var : vars) {
                command.add(_VAR + var);
            }
        }

        return command.toArray(new String[0]);
    }
}
