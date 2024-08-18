package com.hyfly.tf.actuator.entity.message

import com.alibaba.fastjson2.annotation.JSONField

class ChangeSummary {
    @JSONField(name = "add")
    val addCount: Int? = null

    @JSONField(name = "change")
    val changeCount: Int? = null

    @JSONField(name = "remove")
    val removeCount: Int? = null

    @JSONField(name = "import")
    val importCount: Int? = null

    /**
     * apply, plan, destroy
     */
    @JSONField(name = "operation")
    val operation: String? = null

    companion object {
        const val TYPE_SUFFIX: String = "_summary"

        const val CHANGE_TYPE: String = "change" + TYPE_SUFFIX
    }
}
