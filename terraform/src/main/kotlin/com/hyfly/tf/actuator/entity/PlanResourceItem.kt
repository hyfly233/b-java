package com.hyfly.tf.actuator.entity

class PlanResourceItem {
    /**
     * 操作类型：add, change, destroy
     */
    var action: String? = null

    /**
     * 资源模式：resource
     */
    var model: String? = null

    /**
     * 资源名称
     */
    var resourceName: String? = null

    /**
     * 资源类型
     */
    var resourceType: String? = null

    var planFields: MutableList<PlanResourceField>? = null
}
