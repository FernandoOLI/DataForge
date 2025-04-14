package org.example.config;

import org.apache.spark.sql.SparkSession;

public class SparkConfig {
    private final SparkSession sparkSession;

    public SparkConfig() {
        this.sparkSession = SparkSession.builder()
                .appName("Send Json to Kafka")
                .master("local[*]")
                .getOrCreate();
    }

    public SparkSession getSparkSession() {
        return sparkSession;
    }

    public void stopSpark() {
        if (sparkSession != null) {
            sparkSession.stop();
        }
    }
}