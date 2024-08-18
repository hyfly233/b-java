package com.hyfly.tf.actuator.entity

class PlanResourceItem {
    /**
     * 操作类型：add, change, destroy
     */
    val action: String? = null

    /**
     * 资源模式：resource
     */
    val model: String? = null

    /**
     * 资源名称
     */
    val resourceName: String? = null

    /**
     * 资源类型
     */
    val resourceType: String? = null

    val planFields: MutableList<PlanResourceField>? = null
}
