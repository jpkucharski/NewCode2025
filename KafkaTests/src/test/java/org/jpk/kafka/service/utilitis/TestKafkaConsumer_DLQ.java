package org.jpk.kafka.service.utilitis;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;


/**
 * Utility Class for DLQ Listener used in {@link org.jpk.kafka.service.KafkaConsumer_IT}
 */
@Component
public class TestKafkaConsumer_DLQ {

    private String payload;

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    @KafkaListener(topics = "test-dlq-topic", groupId = "test-group-id-1")
    public void listenToDLQ(String message){
        payload = message;
        countDownLatch.countDown();
    }

    public String getPayload(){
        return payload;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void resetCountDownLatch(){
        countDownLatch = new CountDownLatch(1);
    }
}
