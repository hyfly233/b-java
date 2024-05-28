package com.hyfly.zabbix2vm.entity.bo;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VmQueryRangeResponse {

    @JSONField(name = "status")
    private String status;

    @JSONField(name = "data")
    private VmQueryData data;
}
