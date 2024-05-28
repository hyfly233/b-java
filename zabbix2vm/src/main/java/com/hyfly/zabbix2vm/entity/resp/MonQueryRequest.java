package com.hyfly.zabbix2vm.entity.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonQueryRequest {

    /**
     * 监控项指标名称(base64编码)
     */
    private String metricName;

    /**
     * 监控项指标标签
     */
    private Map<String, String> tags;

    /**
     * 开始时间
     */
    private Long start;

    /**
     * 结束时间
     */
    private Long end;

    /**
     * 时间间隔
     */
    private Long step;
}
