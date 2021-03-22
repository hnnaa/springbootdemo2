package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hnn
 * @date 2021/01/04
 */
@Configuration
public class KafkaConsumerConfig {
    @Value("${spring.kafka.consumer.error-file-dir:consumer_error}")
    private String errorFileDir;


    public String getErrorFileDir() {
        return errorFileDir;
    }

    @Resource
    private ConsumerFactory<Object, Object> consumerFactory;

    @Bean("batchFactoryData")
    public KafkaListenerContainerFactory<?> batchFactoryData() {
        ConcurrentKafkaListenerContainerFactory<?, ?> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
//        factory.setConcurrency(concurrency);
        factory.setConcurrency(3);
        //设置为批量消费，每个批次数量在Kafka配置参数中设置ConsumerConfig.MAX_POLL_RECORDS_CONFIG
        factory.setBatchListener(true);
        //设置提交偏移量的方式
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        return factory;
    }
}

