KafkaTests:

This project is an example of Kafka Consumer that includes:
 - Retry policy,
 - DLQ Publisher,
 - Custom Exception,
 - JUnit,
 - Integration Test

@DirtiesContext - reload application context after each test finish. 
                    Used with EmbeddedKafka.

Retry policy:
    After Consumer will be not able to process message (Database Connection issue, 
    JSON paring problem, or any Exception) It will apply retry policy defined in 
    DefaultErrorHandler [KafkaConfiguration](src/main/java/org/jpk/kafka/config/KafkaConfiguration.java)

DLQ publisher:
    The DefaultErrorHandler also contains definition of DeadLetterPublishingRecoverer which will forward 
    unconsumed message to DLQ topic. 
   

App will listen on: http://localhost:29092
Message in straight String form. No DTO implemented.
Topic: 'prod-topic'
If message will contain string "fail" after 3 attempts (1st + 2x retry = 3) it will be forwarded to 'kafka-dlq-topic'

Have fun.