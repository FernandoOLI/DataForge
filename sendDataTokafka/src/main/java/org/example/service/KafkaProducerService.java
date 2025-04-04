package org.example.service;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.example.config.KafkaProducerConfig;


import java.util.UUID;
import java.util.Iterator;

public class KafkaProducerService {
    public void sendToKafka(Dataset<Row> data, String topic) {
        data.toJSON().foreachPartition((Iterator<String> rows) -> {
            KafkaProducer<String, String> producer = KafkaProducerConfig.createProducer();

            while (rows.hasNext()) {
                String rowJson = rows.next();
                ProducerRecord<String, String> record =
                        new ProducerRecord<>(topic, UUID.randomUUID().toString(), rowJson);
                producer.send(record);
            }

            producer.close();
        });
    }
}