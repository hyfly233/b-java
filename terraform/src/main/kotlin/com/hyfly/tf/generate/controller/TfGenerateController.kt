package com.hyfly.tf.generate.controller

import com.hyfly.tf.generate.entity.req.TfGenerateReq
import com.hyfly.tf.generate.service.ITfGenerateService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tf")
class TfGenerateController {

    @Autowired
    private lateinit var tfGenerateService: ITfGenerateService

    @PostMapping("/generate")
    fun generateTfJson(@RequestBody req: TfGenerateReq): String? {
        return tfGenerateService.generateTfJson(req)
    }
}
