package com.hyfly.tf.processor.entity.plan

import com.alibaba.fastjson2.annotation.JSONField

data class PlanVariable(
    @JSONField(name = "value")
    var value: Any? = null
)
