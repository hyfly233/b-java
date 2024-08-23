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
}
