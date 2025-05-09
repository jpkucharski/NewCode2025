package org.jpk.kafka.service;

import org.jpk.kafka.exception.MyKafkaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * Kafka Consumer class configured with {@link org.jpk.kafka.config.KafkaConfiguration}
 */
@Component
public class KafkaConsumer {

    @Value("${spring.kafka.dlq-topic}")
    private String dlqTopic;

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    private String payload;

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    /**
     * Kafka listener for receiving messages. Configured in {@link org.jpk.kafka.config.KafkaConfiguration}
     *
     * @param message, payload value of kafka message received from topic.
     */
    @KafkaListener(topics = "${spring.kafka.topic}", containerFactory = "customKafkaListenerContainerFactory")
    public void receive(String message) {
        LOGGER.info("received message='{}'", message);
        if (message.contains("fail")) {
            throw new MyKafkaException("Simulated processing error");
        }
        payload = message;
        countDownLatch.countDown();
    }

    public String getPayload() {
        return payload;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void resetLatch() {
        this.countDownLatch = new CountDownLatch(1);
    }
}
