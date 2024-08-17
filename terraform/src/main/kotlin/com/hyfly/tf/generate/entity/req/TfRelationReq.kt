package com.hyfly.tf.generate.entity.req

data class TfRelationReq(
    /**
     * 关联源资源
     */
    var source: String? = null,

    /**
     * 关联目标资源
     */
    var target: String? = null
)
