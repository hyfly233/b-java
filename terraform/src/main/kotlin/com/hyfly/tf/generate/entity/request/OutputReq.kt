package com.hyfly.tf.generate.entity.request

data class OutputReq(

    /**
     * 输出变量值 "${openstack_compute_instance_v2.ecs_name.id}"
     */
    var value: String? = null,

    /**
     * 输出变量描述
     */
    var description: String? = null
)
