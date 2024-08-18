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
    private fun initAndPlan() {
        tfPlanService.initAndPlan()
    }

    @PostMapping("/apply")
    private fun apply() {
        tfPlanService.apply()
    }

    @PostMapping("/destroy")
    private fun destroy() {
        tfPlanService.destroy()
    }
}
