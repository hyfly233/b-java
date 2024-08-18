package com.hyfly.tf.actuator.entity.validate

import com.alibaba.fastjson2.annotation.JSONField

class Range {
    @JSONField(name = "filename")
    val filename: String? = null

    @JSONField(name = "start")
    val start: Position? = null

    @JSONField(name = "end")
    val end: Position? = null
}
