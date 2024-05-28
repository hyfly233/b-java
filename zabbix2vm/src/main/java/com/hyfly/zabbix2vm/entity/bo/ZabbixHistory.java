package com.hyfly.zabbix2vm.entity.bo;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

@Data
public class ZabbixHistory {

    @JSONField(name = "itemid")
    private Long itemId;

    @JSONField(name = "clock")
    private Integer clock;

    @JSONField(name = "value")
    private Double value;

    @JSONField(name = "ns")
    private Integer ns;

    public long getTimestamp() {
        if (clock == null) {
            return 0;
        }

        if (ns == null) {
            return clock * 1000L;
        }

        return clock * 1000L + ns / 1000000L;
    }

}
