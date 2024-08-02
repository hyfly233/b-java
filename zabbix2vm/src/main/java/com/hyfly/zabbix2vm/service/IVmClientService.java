package com.hyfly.zabbix2vm.service;

import com.hyfly.zabbix2vm.entity.bo.OpentsdbMetric;
import com.hyfly.zabbix2vm.entity.bo.VmQueryData;
import com.hyfly.zabbix2vm.entity.req.MonQueryReq;
import com.hyfly.zabbix2vm.entity.req.SumQueryReq;
import com.hyfly.zabbix2vm.entity.vo.SumQueryVo;

import java.util.List;

public interface IVmClientService {

    boolean opentsdbPut(OpentsdbMetric metric);

    boolean opentsdbPutBatch(List<OpentsdbMetric> metrics);

    VmQueryData queryRange(MonQueryReq req);

    SumQueryVo sumQueryRange(SumQueryReq req);

    List<SumQueryVo> sumQueryRangeGroupBy(SumQueryReq req, String... groupBy);
}
