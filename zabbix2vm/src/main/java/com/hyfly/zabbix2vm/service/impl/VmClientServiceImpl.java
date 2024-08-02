package com.hyfly.zabbix2vm.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.hyfly.zabbix2vm.entity.bo.MetricResult;
import com.hyfly.zabbix2vm.entity.bo.OpentsdbMetric;
import com.hyfly.zabbix2vm.entity.bo.VmQueryData;
import com.hyfly.zabbix2vm.entity.req.MonQueryReq;
import com.hyfly.zabbix2vm.entity.req.SumQueryReq;
import com.hyfly.zabbix2vm.entity.resp.VmQueryRangeResp;
import com.hyfly.zabbix2vm.entity.vo.SumQueryVo;
import com.hyfly.zabbix2vm.service.IVmClientService;
import com.hyfly.zabbix2vm.util.DataTransform;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service("vmClientService")
public class VmClientServiceImpl implements IVmClientService {

    public static final int MAX_POINTS = 30000;

    @Value("${victoriaMetrics.ip}")
    private String VM_IP;

    @Value("${victoriaMetrics.port.base}")
    private String VM_PORT_BASE;

    @Value("${victoriaMetrics.port.opentsdb}")
    private String VM_PORT_OPENTSDB;

    @Autowired
    private RestTemplate rt;

    @Override
    public boolean opentsdbPut(OpentsdbMetric metric) {
        String json = JSONObject.toJSONString(metric);
        return this.opentsdbPut(json);
    }

    @Override
    public boolean opentsdbPutBatch(List<OpentsdbMetric> metrics) {
        boolean success = true;

        if (metrics != null && !metrics.isEmpty()) {
            int size = metrics.size();
            int batchSize = 10000;
            int times = size / batchSize + (size % batchSize == 0 ? 0 : 1);

            for (int i = 0; i < times; i++) {
                int fromIndex = i * batchSize;
                int toIndex = Math.min((i + 1) * batchSize, size);

                List<OpentsdbMetric> subList = metrics.subList(fromIndex, toIndex);

                String json = JSONObject.toJSONString(subList);

                success = this.opentsdbPut(json);
            }
        }

        return success;
    }

    @Override
    public VmQueryData queryRange(MonQueryReq req) {

        this.validateMaxPointsPerSeries(req.getStart(), req.getEnd(), req.getStep());

        String metricName = req.getMetricName();
        Map<String, String> tags = req.getTags();

        if ((StringUtils.isBlank(req.getMetricName()) && req.getTags() == null) ||
                (StringUtils.isBlank(req.getMetricName()) && req.getTags().isEmpty())) {
            String errMsg = "监控数据查询参数错误";

            log.error(errMsg);
            throw new RuntimeException(errMsg);
        }

        String query = StringUtils.isNoneBlank(metricName) ? DataTransform.base64EncodeItemKey(metricName) : "";

        if (tags != null && !tags.isEmpty()) {
            String collect = tags.entrySet()
                    .stream()
                    .map(entry -> entry.getKey() + "=\"" + entry.getValue() + "\"")
                    .collect(Collectors.joining(",", "{", "}"));

            query += collect;
        }

        if (StringUtils.isBlank(query)) {
            String errMsg = "查询数据指标名称不能为空";

            log.error(errMsg);
            throw new RuntimeException(errMsg);
        }

        return this.baseQueryRange(query, req.getStart(), req.getEnd(), req.getStep());
    }

    @Override
    public SumQueryVo sumQueryRange(SumQueryReq req) {

        String metricName = req.getMetricName();

        String query = StringUtils.isNoneBlank(metricName) ? metricName : "";

        Map<String, String> tags = req.getTags();

        if (tags != null && !tags.isEmpty()) {
            String collect = tags.entrySet()
                    .stream()
                    .map(entry -> entry.getKey() + "=\"" + entry.getValue() + "\"")
                    .collect(Collectors.joining(",", "{", "}"));

            query += collect;
        }

        if (StringUtils.isBlank(query)) {
            String errMsg = "查询数据指标名称不能为空";

            log.error(errMsg);
            throw new RuntimeException(errMsg);
        }

        query = "sum(" + query + ")";

        VmQueryData data = this.baseQueryRange(query, req.getStart(), req.getEnd(), req.getStep());

        SumQueryVo vo = new SumQueryVo();

        if (data != null) {
            List<MetricResult> result = data.getResult();

            Double sum = 0.0;

            if (result != null && !result.isEmpty()) {
                MetricResult metricResult = result.get(0);
                List<List<String>> values = metricResult.getValues();

                if (values != null && !values.isEmpty()) {
                    for (List<String> value : values) {
                        String s = value.get(1);

                        if (NumberUtils.isCreatable(s)) {
                            sum += Double.parseDouble(s);
                        }
                    }
                }

                vo.setSum(String.valueOf(sum))
                        .setValues(values);
            }
        }

        return vo;
    }

    @Override
    public List<SumQueryVo> sumQueryRangeGroupBy(SumQueryReq req, String... groupBy) {
        String metricName = req.getMetricName();

        String query = StringUtils.isNoneBlank(metricName) ? metricName : "";

        Map<String, String> tags = req.getTags();

        if (tags != null && !tags.isEmpty()) {
            String collect = tags.entrySet()
                    .stream()
                    .map(entry -> entry.getKey() + "=\"" + entry.getValue() + "\"")
                    .collect(Collectors.joining(",", "{", "}"));

            query += collect;
        }

        if (StringUtils.isBlank(query)) {
            String errMsg = "查询数据指标名称不能为空";

            log.error(errMsg);
            throw new RuntimeException(errMsg);
        }

        query = "sum(" + query + ")";

        if (groupBy != null && groupBy.length > 0) {
            query += " by (" + String.join(",", groupBy) + ")";
        }

        VmQueryData data = this.baseQueryRange(query, req.getStart(), req.getEnd(), req.getStep());

        if (data != null) {
            List<SumQueryVo> vo = new ArrayList<>();

            List<MetricResult> result = data.getResult();

            for (MetricResult metric : result) {
                Map<String, String> metricMap = metric.getMetric();

                List<List<String>> values = metric.getValues();
            }
        }
        return Collections.emptyList();
    }

    private VmQueryData baseQueryRange(String query, Long start, Long end, Long step) {
        String url = "http://" + VM_IP + ":" + VM_PORT_BASE + "/api/v1/query_range";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("query", query);

        if (start != null) {
            param.add("start", start);
        }

        if (end != null) {
            param.add("end", end);
        }

        if (step != null) {
            param.add("step", step);
        }

        ResponseEntity<String> exchange = rt.exchange(url, HttpMethod.POST, new HttpEntity<>(param, headers), String.class);

        if (exchange.getStatusCode().is2xxSuccessful()) {
            String body = exchange.getBody();

            VmQueryRangeResp resp = JSONObject.parseObject(body, VmQueryRangeResp.class);

            if (resp.isSuccess()) {
                return resp.getData();
            }

            log.error("从VM查询监控数据失败 {}", resp.getStatus());
            return null;
        } else {
            String errMsg = "从VM查询监控数据失败: " + exchange.getBody();

            log.error(errMsg);
            throw new RuntimeException(errMsg);
        }
    }

    private boolean opentsdbPut(String json) {
        String url = "http://" + VM_IP + ":" + VM_PORT_OPENTSDB + "/api/put";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<String> exchange = rt.exchange(url, HttpMethod.POST, new HttpEntity<>(json, headers), String.class);
        HttpStatusCode statusCode = exchange.getStatusCode();
        return statusCode.is2xxSuccessful();
    }

    /**
     * <a href="https://github.com/VictoriaMetrics/VictoriaMetrics/blob/master/app/vmselect/promql/eval.go">...</a>
     * func ValidateMaxPointsPerSeries(start, end, step int64, maxPoints int) error
     *
     * @param start 开始时间
     * @param end   结束时间
     * @param step  时间间隔
     */
    private void validateMaxPointsPerSeries(Long start, Long end, Long step) {
        if (start != null && end != null && step != null) {
            if (step == 0L) {
                throw new RuntimeException("时间间隔不能为0");
            }

            long maxPoints = (end - start) / step + 1;
            if (maxPoints > MAX_POINTS) {
                throw new RuntimeException("数据点数超过最大限制 " + MAX_POINTS);
            }
        }
    }
}
