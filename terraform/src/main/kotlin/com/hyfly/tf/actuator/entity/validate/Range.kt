package com.hyfly.tf.actuator.entity.validate

import com.alibaba.fastjson2.annotation.JSONField

class Range {
    @JSONField(name = "filename")
    var filename: String? = null

    @JSONField(name = "start")
    var start: Position? = null

    @JSONField(name = "end")
    var end: Position? = null
}
