package com.hyfly.tf.actuator.entity.validate

import com.alibaba.fastjson2.annotation.JSONField

class Position {
    @JSONField(name = "line")
    val line: Int? = null

    @JSONField(name = "column")
    val column: Int? = null
}
