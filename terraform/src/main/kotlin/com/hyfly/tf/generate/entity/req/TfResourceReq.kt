package com.hyfly.tf.generate.entity.req

import com.alibaba.fastjson2.JSONObject

data class TfResourceReq(
    /**
     * 资源类型
     */
    var type: String? = null,

    /**
     * 资源别名
     */
    var alias: String? = null,

    /**
     * 资源参数
     */
    var params: JSONObject? = null
)
