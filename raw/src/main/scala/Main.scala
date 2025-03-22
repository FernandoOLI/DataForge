import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.streaming.Trigger
import org.apache.spark.sql.types._

object Main {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("Kafka to Delta Table")
      .master("local[*]")
      .config("spark.sql.extensions", "io.delta.sql.DeltaSparkSessionExtension")
      .config("spark.sql.catalog.spark_catalog", "org.apache.spark.sql.delta.catalog.DeltaCatalog")
      .getOrCreate()

    import spark.implicits._

    // Read data from Kafka
    val kafkaData = spark.readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "192.168.15.6:9092")
      .option("subscribe", "topic-test")
      .option("startingOffsets", "earliest")
      .option("failOnDataLoss", true)
      .load()


    // Define the path to save the Delta table
    val pathToSave = "./data/raw/"

    // Write to Delta table
    kafkaData
      .withColumn("received_at", current_timestamp())
      .withColumn("year", year(col("received_at")))
      .withColumn("month", month(col("received_at")))
      .withColumn("day", dayofmonth(col("received_at")))
      .writeStream
      .format("delta")
      .outputMode("append")
      .trigger(Trigger.AvailableNow())
      .option("checkpointLocation", s"$pathToSave/checkpoint")
      .partitionBy("year","month","day")
      .start(s"$pathToSave/data")
      .awaitTermination()

    spark.stop()
  }
}