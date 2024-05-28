package com.hyfly.zabbix2vm.controller;

import com.hyfly.zabbix2vm.entity.bo.ZabbixHostDetailBo;
import com.hyfly.zabbix2vm.service.IZabbixApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/zabbix")
public class ZabbixController {

    @Autowired
    private IZabbixApiService zabbixService;

    @GetMapping("/hostInfos")
    public List<ZabbixHostDetailBo> getHostInfos() {
        return zabbixService.getHostInfos();
    }
}
