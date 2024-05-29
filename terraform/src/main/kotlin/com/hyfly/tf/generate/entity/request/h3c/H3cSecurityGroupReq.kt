package com.hyfly.tf.generate.entity.request.h3c

import com.alibaba.fastjson2.annotation.JSONField

data class H3cSecurityGroupReq(

    @JSONField(name = "name")
    var name: String? = null,

    @JSONField(name = "user_id")
    var userId: String? = null,

    @JSONField(name = "description")
    var description: String? = null,
)
