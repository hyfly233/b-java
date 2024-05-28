package com.hyfly.zabbix2vm.entity.bo;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VmQueryData {

    @JSONField(name = "resultType")
    private String resultType;

    @JSONField(name = "result")
    private List<MetricResult> result;
}
