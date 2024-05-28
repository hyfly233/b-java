package com.hyfly.zabbix2vm.service;

import com.hyfly.zabbix2vm.entity.po.ZabbixConfigPo;

import java.util.ArrayList;
import java.util.List;

public interface IZabbixConfigService {

    default List<ZabbixConfigPo> listAll() {
        return new ArrayList<>() {{
            add(new ZabbixConfigPo("zabbix_name", "http://127.0.0.1:18080/api_jsonrpc.php", "Admin", "zabbix", "zabbix_topic"));
        }};
    }
}
