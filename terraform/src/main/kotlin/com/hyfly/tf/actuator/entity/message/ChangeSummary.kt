package com.hyfly.tf.actuator.entity.message

import com.alibaba.fastjson2.annotation.JSONField

class ChangeSummary {
    @JSONField(name = "add")
    var addCount: Int? = null

    @JSONField(name = "change")
    var changeCount: Int? = null

    @JSONField(name = "remove")
    var removeCount: Int? = null

    @JSONField(name = "import")
    var importCount: Int? = null

    /**
     * apply, plan, destroy
     */
    @JSONField(name = "operation")
    var operation: String? = null

    companion object {
        const val TYPE_SUFFIX: String = "_summary"

        const val CHANGE_TYPE: String = "change" + TYPE_SUFFIX
    }

    fun validate(): Boolean {
        if (addCount == null || addCount!! < 0) return false
        if (changeCount == null || changeCount!! < 0) return false
        if (removeCount == null || removeCount!! < 0) return false
        if (importCount == null || importCount!! < 0) return false
        if (operation.isNullOrBlank()) return false
        return true
    }

    fun handleError(): String {
        val errors = mutableListOf<String>()
        if (addCount == null || addCount!! < 0) errors.add("Invalid add count")
        if (changeCount == null || changeCount!! < 0) errors.add("Invalid change count")
        if (removeCount == null || removeCount!! < 0) errors.add("Invalid remove count")
        if (importCount == null || importCount!! < 0) errors.add("Invalid import count")
        if (operation.isNullOrBlank()) errors.add("Invalid operation")
        return errors.joinToString(", ")
    }
}
