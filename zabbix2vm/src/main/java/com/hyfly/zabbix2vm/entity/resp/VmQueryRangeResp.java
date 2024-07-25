package com.hyfly.zabbix2vm.entity.resp;

import com.alibaba.fastjson2.annotation.JSONField;
import com.hyfly.zabbix2vm.entity.bo.VmQueryData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VmQueryRangeResp {

    @JSONField(name = "status")
    private String status;

    @JSONField(name = "data")
    private VmQueryData data;
}
