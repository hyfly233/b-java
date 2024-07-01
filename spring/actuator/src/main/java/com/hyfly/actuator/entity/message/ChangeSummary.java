package com.hyfly.actuator.entity.message;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

@Data
public class ChangeSummary {

    public static final String TYPE_SUFFIX = "_summary";

    public static final String CHANGE_TYPE = "change" + TYPE_SUFFIX;

    @JSONField(name = "add")
    private Integer addCount;

    @JSONField(name = "change")
    private Integer changeCount;

    @JSONField(name = "remove")
    private Integer removeCount;

    @JSONField(name = "import")
    private Integer importCount;

    /**
     * apply, plan, destroy
     * {@link com.hyfly.actuator.entity.message.constants.SummaryOperation}
     */
    @JSONField(name = "operation")
    private String operation;
}
