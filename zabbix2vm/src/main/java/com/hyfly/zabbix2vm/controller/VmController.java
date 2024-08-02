package com.hyfly.zabbix2vm.controller;

import com.hyfly.zabbix2vm.entity.req.SumQueryReq;
import com.hyfly.zabbix2vm.entity.vo.SumQueryVo;
import com.hyfly.zabbix2vm.service.IVmClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vm")
public class VmController {

    @Autowired
    private IVmClientService vmClientService;

    @PostMapping("/sumQuery")
    public SumQueryVo sumQuery(@RequestBody SumQueryReq req) {
        return vmClientService.sumQueryRange(req);
    }

    @PostMapping("/sumQueryBy")
    public List<SumQueryVo> sumQueryBy(@RequestBody SumQueryReq req) {
        return vmClientService.sumQueryRangeGroupBy(req, "Protocol");
    }
}
