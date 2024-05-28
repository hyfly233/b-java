package com.hyfly.zabbix2vm.entity.bo;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

@Data
public class ZabbixInterfaceBo {

    @JSONField(name = "interfaceid")
    private Integer interfaceId;

    @JSONField(name = "main")
    private Boolean main;

    @JSONField(name = "type")
    private Integer type;

    @JSONField(name = "useip")
    private Boolean useIp;

    @JSONField(name = "ip")
    private String ip;

    @JSONField(name = "dns")
    private String dns;

    @JSONField(name = "port")
    private String port;
}
