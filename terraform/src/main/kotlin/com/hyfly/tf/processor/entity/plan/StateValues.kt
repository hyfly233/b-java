package com.hyfly.tf.processor.entity.plan

import com.alibaba.fastjson2.annotation.JSONField

data class StateValues(
    // The Outputs for this common state representation.
    @JSONField(name = "outputs")
    var outputs: Map<String?, StateOutput?>? = null,

    // The root module in this state representation.
    @JSONField(name = "root_module")
    val rootModule: StateModule? = null
)
