package com.hyfly.tf.generate.service.impl

import com.hyfly.tf.generate.entity.bo.h3c.H3cComputeInstanceBo
import com.hyfly.tf.generate.entity.request.OutputReq
import com.hyfly.tf.generate.service.IH3cBoValidateService
import org.springframework.stereotype.Service

@Service
class H3cBoValidateServiceImpl : IH3cBoValidateService {

    override fun validateOutputs(outputs: Map<String, OutputReq>) {
        for (entry in outputs.entries) {
            val value = entry.value.value
            if (value == null) {
                throw IllegalArgumentException("output value is null")
            } else {
                // 判断 value 是否符合 "${openstack_compute_instance_v2.ecs_name.id}" 格式
                if (!value.startsWith("\${") || !value.endsWith("}")) {
                    throw IllegalArgumentException("output value is not match format: \${}")
                }
            }
        }
    }

    override fun validateComputeInstance(bo: H3cComputeInstanceBo) {

        if (bo.adminPass != null && bo.enableAdminPass != "1") {
            throw IllegalArgumentException("enable_admin_pass is not 1, but admin_pass is not null")
        }


    }

}
