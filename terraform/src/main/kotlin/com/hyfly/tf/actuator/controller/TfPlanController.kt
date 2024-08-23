package com.hyfly.tf.actuator.controller

import com.hyfly.tf.actuator.service.ITfPlanService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tf")
class TfPlanController(
    private val tfPlanService: ITfPlanService
) {

    @PostMapping("/initAndPlan")
    fun initAndPlan(): Map<String, Any> {
        return tfPlanService.initAndPlan()
    }

    @PostMapping("/apply")
    fun apply(): Map<String, Any> {
        return tfPlanService.apply()
    }

    @PostMapping("/destroy")
    fun destroy(): Map<String, Any> {
        return tfPlanService.destroy()
    }
}
