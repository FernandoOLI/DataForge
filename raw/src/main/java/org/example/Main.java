package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;
import org.example.kafka.KafkaProducerConfig;

public class Main {
    public static void main(String[] args) {
        // Initialize Spark Session
        SparkSession spark = SparkSession.builder()
                .appName("Send DataFrame to Kafka")
                .master("local[*]") // For local testing
                .getOrCreate();

        StructType schema = new StructType()
                .add("id", DataTypes.StringType)
                .add("created_at", DataTypes.StringType)
                .add("buyer", new StructType()
                        .add("name", DataTypes.StringType)
                        .add("cpf", DataTypes.StringType)
                        .add("phone", DataTypes.StringType)
                        .add("email", DataTypes.StringType)
                        .add("address", DataTypes.StringType)
                        .add("birth_date", DataTypes.StringType))
                .add("card", new StructType()
                        .add("number", DataTypes.StringType)
                        .add("bank", DataTypes.StringType)
                        .add("agency", DataTypes.StringType)
                        .add("account", DataTypes.StringType))
                .add("company", DataTypes.StringType)
                .add("item", new StructType()
                        .add("name", DataTypes.StringType)
                        .add("category", DataTypes.StringType)
                        .add("price", DataTypes.DoubleType)
                        .add("code", DataTypes.StringType)
                        .add("sku", DataTypes.StringType)
                        .add("brand", DataTypes.StringType)
                        .add("stock", DataTypes.IntegerType)
                        .add("manufacturing_at", DataTypes.StringType)
                        .add("expiration_at", DataTypes.StringType))
                .add("total_value", DataTypes.DoubleType);

        Dataset<Row> df = spark.read()
                .schema(schema).json("./data/source/transactions.json");

        df.printSchema();
        df.show(false);

        KafkaProducer<String, String> producer = KafkaProducerConfig.createProducer();
        String topic = "topic-test";

        df.toJSON().foreach(rowJson -> {
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, null, rowJson);
            producer.send(record);
        });

        producer.close();

        spark.stop();
    }
}
