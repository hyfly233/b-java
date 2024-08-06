package com.hyfly.kafkademo.stream;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.ValueMapper;
import org.apache.kafka.streams.processor.WallclockTimestampExtractor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@EnableKafka
@Configuration
@EnableKafkaStreams
public class KafkaStreamsConfig {

    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public KafkaStreamsConfiguration kStreamsConfigs(KafkaProperties kafkaProperties,
                                                     @Value("${spring.application.name}") String appName) {
        Map<String, Object> props = new HashMap<>(kafkaProperties.getProperties());

        props.put(StreamsConfig.APPLICATION_ID_CONFIG, appName);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, WallclockTimestampExtractor.class.getName());

        return new KafkaStreamsConfiguration(props);
    }


    @Bean(name = "kStream01")
    public KStream<String, String> kStream01(final StreamsBuilder streamsBuilder,
                                             @Value("${streams.topic.test01.input}") String inputTopic,
                                             @Value("${streams.topic.test01.output}") String outputTopic) {
        KStream<String, String> stream = streamsBuilder.stream(inputTopic);

        stream.mapValues((ValueMapper<String, String>) String::toUpperCase)
                .to(outputTopic, Produced.with(Serdes.String(), Serdes.String()));
        return stream;
    }

    @Bean(name = "kStream02")
    public KStream<String, String> kStream02(final StreamsBuilder streamsBuilder,
                                             @Value("${streams.topic.test02.input}") String inputTopic,
                                             @Value("${streams.topic.test02.output}") String outputTopic) {
        KStream<String, String> stream = streamsBuilder.stream(inputTopic);

        stream.mapValues((ValueMapper<String, String>) String::toUpperCase)
                .to(outputTopic);
        return stream;
    }
}
