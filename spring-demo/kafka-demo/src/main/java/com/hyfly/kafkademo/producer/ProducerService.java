package com.hyfly.kafkademo.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class ProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public ProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMsg(String topic, String msg) {
        kafkaTemplate.send(topic, msg);
    }

    public void sendMsgCallback(String topic, String msg) {
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, msg);

        future.whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("send message failed", ex);
            } else {
                log.info("send message success, result: {}", result);
            }
        });
    }
}
