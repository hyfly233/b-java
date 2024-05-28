package com.hyfly.zabbix2vm.entity.bo;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpentsdbMetric {

    @JSONField(name = "metric")
    private String metric;

    @JSONField(name = "value")
    private Object value;

    @JSONField(name = "tags")
    private Map<String, String> tags;

    @JSONField(name = "timestamp")
    private Long timestamp;
}
