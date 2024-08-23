package com.hyfly.tf.actuator.entity.validate

import com.alibaba.fastjson2.annotation.JSONField

class Position {
    @JSONField(name = "line")
    var line: Int? = null

    @JSONField(name = "column")
    var column: Int? = null
}
