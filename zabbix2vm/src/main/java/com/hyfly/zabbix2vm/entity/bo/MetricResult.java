package com.hyfly.zabbix2vm.entity.bo;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetricResult {

    @JSONField(name = "values")
    List<List<String>> values;
    @JSONField(name = "metric")
    private Map<String, String> metric;
}
