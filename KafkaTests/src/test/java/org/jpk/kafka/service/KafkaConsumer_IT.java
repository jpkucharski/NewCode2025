package org.jpk.kafka.service;


import org.jpk.kafka.service.utilitis.TestKafkaConsumer_DLQ;
import org.jpk.kafka.service.utilitis.TestKafkaProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * TestClass for Integration test of {@link KafkaConsumer} and DLQ.
 */
@SpringBootTest(properties = "spring.profiles.active=test")
@DirtiesContext
@EmbeddedKafka(partitions = 1, topics = "test-dlq-topic", brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
public class KafkaConsumer_IT {

    private final static String MESSAGE_IT = "integration test message!";
    private final static String FAIL_MESSAGE_IT = "integration test message containing fail";

    @Autowired
    private KafkaConsumer kafkaConsumer;

    @Autowired
    private TestKafkaProducer testKafkaProducer;

    @Autowired
    private TestKafkaConsumer_DLQ dlqConsumer;

    @Value("${spring.kafka.topic}")
    private String topic;

    @BeforeEach
    public void reset(){
        kafkaConsumer.resetLatch();
        dlqConsumer.resetCountDownLatch();
    }

    @Test
    public void whenSendingWithProducer_thenMessageReceived() throws InterruptedException {
        testKafkaProducer.sendMessage(topic, MESSAGE_IT);
        boolean messageConsumed = kafkaConsumer.getCountDownLatch().await(10, TimeUnit.SECONDS);
        assertTrue(messageConsumed);
        assertThat(kafkaConsumer.getPayload(), containsString(MESSAGE_IT));
    }

    @Test
    public void whenConsumptionFail_thenMessageWillBeSendToDLQ() throws InterruptedException {
        testKafkaProducer.sendMessage(topic, FAIL_MESSAGE_IT);
        kafkaConsumer.getCountDownLatch().await(10, TimeUnit.SECONDS);
        boolean messageConsumed = dlqConsumer.getCountDownLatch().await(10, TimeUnit.SECONDS);
        assertTrue(messageConsumed);
        assertThat(dlqConsumer.getPayload(), containsString(FAIL_MESSAGE_IT));
    }
}
