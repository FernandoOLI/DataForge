package org.example.kafka;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.util.Properties;

public class KafkaProducerConfig {
    public static KafkaProducer<String, String> createProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.15.6:9092");// Replace with the actual IP
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return new KafkaProducer<>(props);
    }
}
