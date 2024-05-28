package com.hyfly.zabbix2vm.service;

import com.hyfly.zabbix2vm.entity.bo.OpentsdbMetric;
import com.hyfly.zabbix2vm.entity.bo.VmQueryRangeResponse;
import com.hyfly.zabbix2vm.entity.resp.MonQueryRequest;

import java.util.List;

public interface IVmClientService {

    boolean opentsdbPut(OpentsdbMetric metric);

    boolean opentsdbPutBatch(List<OpentsdbMetric> metrics);

    VmQueryRangeResponse queryRange(MonQueryRequest request);
}
