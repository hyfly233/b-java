package com.hyfly.tf.generate.entity.bo

import com.alibaba.fastjson2.annotation.JSONField

data class TfGenerateBo(

    @JSONField(name = "resource")
    var resource: Map<String, Any>? = null,

    @JSONField(name = "variable")
    var variable: Map<String, Any>? = null,

    @JSONField(name = "output")
    var output: Map<String, Any>? = null,

)
