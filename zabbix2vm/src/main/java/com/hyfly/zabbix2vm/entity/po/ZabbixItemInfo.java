package com.hyfly.zabbix2vm.entity.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZabbixItemInfo {

    private String serverIp;

    private Long hostId;

    private String hostName;

    private String ip;

    private String dns;

    private Long itemId;

    private String itemName;

    private String itemKey;

    private String itemUnits;
}
