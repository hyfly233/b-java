package com.hyfly.tf.generate.service

import com.hyfly.tf.generate.entity.bo.h3c.H3cComputeInstanceBo
import com.hyfly.tf.generate.entity.request.OutputReq

interface IH3cBoValidateService {

    /**
     * 验证输出参数
     *
     * @param outputs 输出参数
     */
    fun validateOutputs(outputs: Map<String, OutputReq>)

    /**
     * 验证计算实例
     *
     * @param bo 计算实例
     */
    fun validateComputeInstance(bo: H3cComputeInstanceBo)

}
