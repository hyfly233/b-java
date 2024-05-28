package com.hyfly.zabbix2vm.entity.bo;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.util.List;

@Data
public class ZabbixHostDetailBo {

    @JSONField(name = "hostid")
    private Integer hostId;

    @JSONField(name = "host")
    private String host;

    @JSONField(name = "available")
    private Integer available;

    @JSONField(name = "name")
    private String name;

    @JSONField(name = "description")
    private String description;

    @JSONField(name = "items")
    private List<ZabbixItemBo> items;

    @JSONField(name = "interfaces")
    private List<ZabbixInterfaceBo> interfaces;
}
