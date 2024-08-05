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

//    @KafkaListener(topics = "inputTopic")
//    public void streamInputTopic(List<ConsumerRecord<String, String>> records, Acknowledgment ack) {
//
//        if (records != null) {
//            for (ConsumerRecord<String, String> record : records) {
//                log.info("inputTopic record: {}", record);
//            }
//
//            ack.acknowledge();
//        }
//    }

    @KafkaListener(topics = "outputTopic")
    public void streamOutputTopic(List<ConsumerRecord<String, String>> records, Acknowledgment ack) {

        if (records != null) {
            for (ConsumerRecord<String, String> record : records) {
                log.info("outputTopic record: {}", record);
            }

            ack.acknowledge();
        }
    }
}
