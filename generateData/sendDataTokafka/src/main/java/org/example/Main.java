package org.example;

import org.example.config.SparkConfig;
import org.example.service.DataReader;
import org.example.service.KafkaProducerService;

public class Main {
    public static void main(String[] args) {
        SparkConfig sparkConfig = new SparkConfig();
        DataReader dataReader = new DataReader(sparkConfig.getSparkSession());
        KafkaProducerService producerService = new KafkaProducerService();
        // if limit = 0, will send all data in file
        dataReader.readAndShowData("./data/source/transactions.json", 20);
        producerService.sendToKafka(dataReader.getData(), "topic-test");

        sparkConfig.stopSpark();
    }
}