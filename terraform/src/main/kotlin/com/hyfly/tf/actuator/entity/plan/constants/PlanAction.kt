package com.hyfly.tf.actuator.entity.plan.constants;

import java.util.ArrayList;
import java.util.List;

public class PlanAction {

    public static final String NOOP = "no-op";

    public static final String CREATE = "create";

    public static final String READ = "read";

    public static final String UPDATE = "update";

    public static final String DELETE = "delete";

    /**
     * 资源重建替换，对应的 actions 为 ["delete", "create"]
     */
    public static final String REPLACE = "replace";

    public static final List<String> REPLACE_ACTIONS = new ArrayList<String>() {{
        add(DELETE);
        add(CREATE);
    }};
}
