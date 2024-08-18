package com.hyfly.tf.actuator.entity.schema

import com.alibaba.fastjson2.annotation.JSONField

class Schema {
    @JSONField(name = "version")
    var version: Int? = null

    @JSONField(name = "block")
    var block: SchemaBlock? = null
}
