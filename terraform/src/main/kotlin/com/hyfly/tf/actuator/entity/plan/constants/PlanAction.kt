package com.hyfly.tf.actuator.entity.plan.constants

object PlanAction {
    const val NOOP: String = "no-op"

    const val CREATE: String = "create"

    const val READ: String = "read"

    const val UPDATE: String = "update"

    const val DELETE: String = "delete"

    /**
     * 资源重建替换，对应的 actions 为 ["delete", "create"]
     */
    const val REPLACE: String = "replace"

    val REPLACE_ACTIONS: List<String> = mutableListOf(DELETE, CREATE)
}
