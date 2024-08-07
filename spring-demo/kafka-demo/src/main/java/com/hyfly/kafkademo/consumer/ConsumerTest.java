package com.hyfly.kafkademo.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ConsumerTest {

//    @KafkaListener(topics = "inputTopic01")
//    public void streamInputTopic(List<ConsumerRecord<String, String>> records, Acknowledgment ack) {
//
//        if (records != null) {
//            for (ConsumerRecord<String, String> record : records) {
//                log.info("inputTopic01 record: {}", record.value());
//            }
//
//            ack.acknowledge();
//        }
//    }

    @KafkaListener(topics = "outputTopic01")
    public void streamOutputTopic01(List<ConsumerRecord<String, String>> records, Acknowledgment ack) {

        if (records != null) {
            for (ConsumerRecord<String, String> record : records) {
                log.info("outputTopic01 record: {}", record.value());
            }

            ack.acknowledge();
        }
    }

    @KafkaListener(topics = "outputTopic02")
    public void streamOutputTopic02(List<ConsumerRecord<String, String>> records, Acknowledgment ack) {

        if (records != null) {
            for (ConsumerRecord<String, String> record : records) {
                log.info("outputTopic02 record: {}",record.value());
            }

            ack.acknowledge();
        }
    }
}
