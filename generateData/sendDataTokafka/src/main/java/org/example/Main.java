package org.example;

import org.example.config.SparkConfig;
import org.example.service.DataReader;
import org.example.service.KafkaProducerService;

public class Main {
    public static void main(String[] args) {
        String path = args.length > 0 ? args[0] : "data/source/transactions.json";
        SparkConfig sparkConfig = new SparkConfig();
        DataReader dataReader = new DataReader(sparkConfig.getSparkSession());
        KafkaProducerService producerService = new KafkaProducerService();
        // if limit = 0, will send all data in file
        dataReader.readAndShowData(path, 20);
        producerService.sendToKafka(dataReader.getData(), "topic-test");

        sparkConfig.stopSpark();
    }
}