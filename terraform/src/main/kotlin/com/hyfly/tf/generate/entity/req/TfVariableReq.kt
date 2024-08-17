package com.hyfly.tf.generate.entity.req

data class TfVariableReq(

    /**
     * 变量类型
     */
    var type: String? = null,

    /**
     * 描述
     */
    var description: String? = null,

    /**
     * 默认值
     */
    var default: String? = null,

    /**
     * 是否可为空
     */
    var nullable: Boolean? = null,

    /**
     * 是否敏感
     */
    var sensitive: Boolean? = null
)
