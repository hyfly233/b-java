package com.hyfly.tf.generate.service

import com.hyfly.tf.generate.entity.bo.tfj.ComputeInstanceBo
import com.hyfly.tf.generate.entity.req.TfOutputReq

interface ITfjBoValidateService {

    /**
     * 验证输出参数
     *
     * @param outputs 输出参数
     */
    fun validateOutputs(outputs: Map<String, TfOutputReq>)

    /**
     * 验证计算实例
     *
     * @param bo 计算实例
     */
    fun validateComputeInstance(bo: ComputeInstanceBo)

}
