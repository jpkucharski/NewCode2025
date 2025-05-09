package org.jpk.kafka.service;

import org.jpk.kafka.config.KafkaConfiguration;
import org.jpk.kafka.exception.MyKafkaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

/**
 * JUnit Test of {@link KafkaConsumer} class
 */

@SpringBootTest(properties = "spring.profiles.active=test")
@EmbeddedKafka(partitions = 1, topics = {"test-topic","test-dlq-topic" })
@Import(KafkaConfiguration.class)
class KafkaConsumerTest {

    private final static String MESSAGE = "test success message!";
    private final static String ERROR_MESSAGE = "test fail message!";

    @Value("${spring.kafka.topic}")
    private String topic;

    @Value("${spring.kafka.dlq-topic}")
    private String dlqTopic;

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @MockitoSpyBean
    private KafkaConsumer spyConsumer;

    @BeforeEach
    public void reset() {
        spyConsumer.resetLatch();
    }

    @Test
    public void testListener_ShouldReceiveCorrectMessage() throws InterruptedException {
        kafkaTemplate.send(topic, MESSAGE);
        boolean messageConsumed = spyConsumer.getCountDownLatch().await(5, TimeUnit.SECONDS);
        if (!messageConsumed) {
            fail("Message not consumed by kafkaConsumer in time!");
        }
        Mockito.verify(spyConsumer, times(1)).receive(MESSAGE);
        assertTrue(spyConsumer.getPayload().contains(MESSAGE));
    }

    @Test
    public void testListener_ShouldBeCalled3TimesAndThrowRuntimeException() throws InterruptedException {
        kafkaTemplate.send(topic, ERROR_MESSAGE);
        spyConsumer.getCountDownLatch().await(10, TimeUnit.SECONDS);
        Mockito.verify(spyConsumer, times(3)).receive(ERROR_MESSAGE);
        assertThrows(MyKafkaException.class, () -> {
            spyConsumer.receive(ERROR_MESSAGE);
        });
    }
}

