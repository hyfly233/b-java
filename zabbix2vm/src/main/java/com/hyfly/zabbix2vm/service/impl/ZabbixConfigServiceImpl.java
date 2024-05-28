package com.hyfly.zabbix2vm.service.impl;

import com.hyfly.zabbix2vm.entity.po.ZabbixConfigPo;
import com.hyfly.zabbix2vm.service.IZabbixConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ZabbixConfigServiceImpl implements IZabbixConfigService {

    @Override
    public List<ZabbixConfigPo> listAll() {
        return IZabbixConfigService.super.listAll();
    }
}
