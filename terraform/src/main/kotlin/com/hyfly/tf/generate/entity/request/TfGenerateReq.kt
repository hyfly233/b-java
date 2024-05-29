package com.hyfly.tf.generate.entity.request

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
    var resources: Set<ResourceReq>? = null,

    /**
     * 资源关联关系
     */
    var relations: Set<RelationReq>? = null,

    /**
     * 资源依赖关系
     */
    var depends: Set<DependReq>? = null,

    /**
     * 变量列表
     */
    var variables: Map<String, VariableReq>? = null,

    /**
     * 输出列表
     */
    var outputs: Map<String, OutputReq>? = null
)
