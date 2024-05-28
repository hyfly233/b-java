package com.hyfly.zabbix2vm.entity.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZabbixConfigPo {

    /**
     * zabbix源名称
     */
    private String name;

    /**
     * zabbix源地址
     */
    private String url;

    private String account;

    private String password;

    private String topic;
}
