package com.example.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author hnn
 * @date 2021/2/22
 */
@Component
public class TestConsumer {
    private static final Logger log = LoggerFactory.getLogger(TestConsumer.class);

    @KafkaListener(topics = {"test"})
    public void onMessageTest(ConsumerRecord<?, ?> record) {
        log.info("++++消费到kafka数据：topic={};partition={};offset={};value={} ", record.topic(), record.partition(), record.offset(), record.value());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("----消费到kafka数据：topic={};partition={};offset={};value={} ", record.topic(), record.partition(), record.offset(), record.value());
    }

    @KafkaListener(topics = {"test2"}, containerFactory = "batchFactoryData")
    public void onMessageTest2(List<ConsumerRecord<?, ?>> records, Acknowledgment ack) {
        log.info("----->records size={}", records.size());
        for (ConsumerRecord<?, ?> record : records) {
            log.info("++++消费到kafka数据：topic={};partition={};offset={};value={} ", record.topic(), record.partition(), record.offset(), record.value());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("----消费到kafka数据：topic={};partition={};offset={};value={} ", record.topic(), record.partition(), record.offset(), record.value());
        }
        ack.acknowledge();
    }
}
