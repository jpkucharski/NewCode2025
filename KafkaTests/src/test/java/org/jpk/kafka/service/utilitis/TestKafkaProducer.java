package org.jpk.kafka.service.utilitis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Utility class:
 * KafkaProducer for IT test purpose only.
 */
@Component
public class TestKafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestKafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String message){
        LOGGER.info("Sending message='{}' to topic='{}'", message, topic);
        kafkaTemplate.send(topic, message);
    }
}