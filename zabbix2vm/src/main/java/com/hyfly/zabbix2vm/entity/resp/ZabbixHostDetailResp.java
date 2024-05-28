package com.hyfly.zabbix2vm.entity.resp;

import com.alibaba.fastjson2.annotation.JSONField;
import com.hyfly.zabbix2vm.entity.bo.ZabbixHostDetailBo;
import lombok.Data;

import java.util.List;

@Data
public class ZabbixHostDetailResp {

    @JSONField(name = "id")
    private String id;

    @JSONField(name = "jsonrpc")
    private String jsonrpc;

    @JSONField(name = "result")
    private List<ZabbixHostDetailBo> result;
}
