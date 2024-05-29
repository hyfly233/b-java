package com.hyfly.zabbix2vm.entity.bo;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

@Data
public class ZabbixItemBo {

    @JSONField(name = "itemid")
    private Long itemId;

    @JSONField(name = "name")
    private String name;

    @JSONField(name = "key_")
    private String key;

    @JSONField(name = "value_type")
    private Integer valueType;

    @JSONField(name = "units")
    private String units;

    @JSONField(name = "description")
    private String description;
}
