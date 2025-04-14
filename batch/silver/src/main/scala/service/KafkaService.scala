package service

import config.KafkaConfig
import model.TransactionSchema
import org.apache.spark.sql.functions.from_json
import org.apache.spark.sql.{DataFrame, SparkSession}

object KafkaService {
  def readFromKafka(spark: SparkSession): DataFrame = {
    import spark.implicits._

    spark.readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", KafkaConfig.bootstrapServers)
      .option("subscribe", KafkaConfig.topic)
      .option("startingOffsets", "earliest")
      .option("failOnDataLoss", true)
      .load()
      .selectExpr("CAST(value AS STRING)")
      .as[String]
      .select(from_json($"value", TransactionSchema.schema).alias("data"))
      .select("data.*")
  }
}