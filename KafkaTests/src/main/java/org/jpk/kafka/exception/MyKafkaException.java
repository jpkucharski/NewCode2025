package org.jpk.kafka.exception;

public class MyKafkaException extends RuntimeException {

    public MyKafkaException(String message) {
        super(message);
    }

    public MyKafkaException(String message, Throwable cause){
        super(message, cause);
    }
}