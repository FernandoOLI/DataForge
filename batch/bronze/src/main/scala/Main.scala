import org.apache.spark.sql.functions._
import org.apache.spark.sql.streaming.{StreamingQuery, Trigger}
import org.apache.spark.sql.{DataFrame, SparkSession}

class KafkaDeltaPipeline(
                          kafkaBootstrapServers: String = "0.0.0.0:9092",
                          kafkaTopic: String = "topic-test",
                          deltaOutputPath: String = "./data/bronze/"
                        ) {

  def createSparkSession(): SparkSession = {
    SparkSession.builder()
      .appName("Kafka to Bronze")
      .master("local[*]")
      .config("spark.sql.extensions", "io.delta.sql.DeltaSparkSessionExtension")
      .config("spark.sql.catalog.spark_catalog", "org.apache.spark.sql.delta.catalog.DeltaCatalog")
      .getOrCreate()
  }

  def readFromKafka(spark: SparkSession): DataFrame = {
    spark.readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", kafkaBootstrapServers)
      .option("subscribe", kafkaTopic)
      .option("startingOffsets", "earliest")
      .option("failOnDataLoss", "true")
      .load()
  }

  def addMetadataColumns(df: DataFrame): DataFrame = {
    df.withColumn("received_at", current_timestamp())
      .withColumn("year", year(col("received_at")))
      .withColumn("month", month(col("received_at")))
      .withColumn("day", dayofmonth(col("received_at")))
  }

  def writeToDelta(df: DataFrame): StreamingQuery = {
    df.writeStream
      .format("delta")
      .outputMode("append")
      .trigger(Trigger.AvailableNow())
      .option("checkpointLocation", s"$deltaOutputPath/checkpoint")
      .partitionBy("year", "month", "day")
      .start(s"$deltaOutputPath/data")
  }

  def execute(): Unit = {
    val spark = createSparkSession()
    try {
      val kafkaData = readFromKafka(spark)
      val processedData = addMetadataColumns(kafkaData)
      val query = writeToDelta(processedData)

      query.awaitTermination()
    } finally {
      spark.stop()
    }
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    val pipeline = new KafkaDeltaPipeline()

    pipeline.execute()
  }
}