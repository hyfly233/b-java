package com.hyfly.tf.actuator.service

interface ITfPlanService {

    /**
     * 添加执行计划
     *
     * @param request 执行计划参数
     * @return 执行计划
     */
    fun initAndPlan(): Map<String, Any>

    fun apply(): Map<String, Any>

    fun destroy(): Map<String, Any>
}
