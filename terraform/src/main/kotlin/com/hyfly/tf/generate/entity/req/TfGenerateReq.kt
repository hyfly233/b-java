package com.hyfly.tf.generate.entity.req

data class TfGenerateReq(
    /**
     * 云平台类型
     */
    var producerType: String? = null,

    /**
     * 租户 ID
     */
    var tenantId: String? = null,

    /**
     * 资源列表
     */
    var resources: Set<TfResourceReq>? = null,

    /**
     * 资源关联关系
     */
    var relations: Set<TfRelationReq>? = null,

    /**
     * 资源依赖关系
     */
    var depends: Set<TfDependReq>? = null,

    /**
     * 变量列表
     */
    var variables: Map<String, TfVariableReq>? = null,

    /**
     * 输出列表
     */
    var outputs: Map<String, TfOutputReq>? = null
)
