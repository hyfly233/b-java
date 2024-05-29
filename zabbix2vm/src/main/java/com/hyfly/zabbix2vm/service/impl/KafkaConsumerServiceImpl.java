package com.hyfly.zabbix2vm.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.hyfly.zabbix2vm.entity.bo.OpentsdbMetric;
import com.hyfly.zabbix2vm.entity.bo.ZabbixHistory;
import com.hyfly.zabbix2vm.entity.po.ZabbixConfigPo;
import com.hyfly.zabbix2vm.entity.po.ZabbixItemInfo;
import com.hyfly.zabbix2vm.service.IKafkaConsumerService;
import com.hyfly.zabbix2vm.service.IVmClientService;
import com.hyfly.zabbix2vm.service.IZabbixApiService;
import com.hyfly.zabbix2vm.service.IZabbixConfigService;
import com.hyfly.zabbix2vm.util.DataTransform;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.listener.BatchAcknowledgingMessageListener;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class KafkaConsumerServiceImpl implements IKafkaConsumerService {

    @Resource
    private IVmClientService vmClientService;

    @Resource
    private IZabbixApiService zabbixApiService;

    @Resource
    private IZabbixConfigService zabbixConfigService;

    @Resource
    private ConcurrentKafkaListenerContainerFactory<String, String> batchFactory;

    @PostConstruct
    public void initialize() {
        // 获取主题列表
        List<ZabbixConfigPo> topics = zabbixConfigService.listAll();

        // 创建 Kafka 消费者
        for (ZabbixConfigPo po : topics) {
            // zabbix server 地址
            final String url = po.getUrl();
            // 对应的 SeaTunnel kafka 的 topic
            final String topic = po.getTopic();

            // 属性配置
            ContainerProperties containerProperties = new ContainerProperties(topic);
            containerProperties.setPollTimeout(1500);
            containerProperties.setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);

            containerProperties.setMessageListener((BatchAcknowledgingMessageListener<String, String>) (consumerRecords, ack)
                    -> consumerZabbixCdcData(consumerRecords, ack, url));

            KafkaMessageListenerContainer<String, String> container = new KafkaMessageListenerContainer<>(batchFactory.getConsumerFactory(),
                    containerProperties);
            container.setBeanName(topic);
            container.start();
        }
    }

    private void consumerZabbixCdcData(final List<ConsumerRecord<String, String>> consumerRecords, final Acknowledgment ack, final String url) {
        // 处理消息
        List<OpentsdbMetric> metricList = consumerRecords.stream()
                .map(ConsumerRecord::value)
                .map(value -> {
                    if (JSON.isValid(value)) {
                        ZabbixHistory zh = JSONObject.parseObject(value, ZabbixHistory.class);
                        OpentsdbMetric metric = DataTransform.history2Metric(zh);

                        ZabbixItemInfo zabbixItemInfo = zabbixApiService.getItemInfoByItemId(url, zh.getItemId());

                        if (zabbixItemInfo == null) {
                            return null;
                        }

                        // 对指标名称进行 URL 编码
                        String itemKey = zabbixItemInfo.getItemKey();
                        metric.setMetric(DataTransform.base64EncodeItemKey(itemKey));

                        Map<String, String> tags = DataTransform.getMetricTags(zabbixItemInfo);

                        metric.setTags(tags);

                        return metric;
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());


        if (vmClientService.opentsdbPutBatch(metricList)) {
            ack.acknowledge();
        }
    }
}
