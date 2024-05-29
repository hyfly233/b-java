package com.hyfly.tf.generate.entity.request

data class DependReq(
    /**
     * 依赖源资源
     */
    var source: String? = null,

    /**
     * 依赖目标资源
     */
    var target: String? = null
)
