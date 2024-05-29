package com.hyfly.tf.generate.service

import com.hyfly.tf.generate.entity.request.TfGenerateReq

interface ITfGenerateService {

    /**
     * 生成Terraform Json
     *
     * @param req 请求参数
     * @return Terraform Json
     */
    fun generateTfJson(req: TfGenerateReq): String?
}
