package com.hyfly.zabbix2vm.entity.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnableItemBo {

    /**
     * 云主机监控IP
     */
    private String hostIp;

    /**
     * 监控项key
     */
    private String itemKey;

    /**
     * 监控项名称
     */
    private String itemName;
}
