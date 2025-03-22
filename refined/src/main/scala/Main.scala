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

    val kafkaData = spark.readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "192.168.15.6:9092")
      .option("subscribe", "topic-test")
      .option("startingOffsets", "earliest")
      .option("failOnDataLoss", true)
      .load()

    val schema = new StructType()
      .add("id", StringType)
      .add("created_at", StringType)
      .add("buyer", new StructType()
        .add("name", StringType)
        .add("cpf", StringType)
        .add("phone", StringType)
        .add("email", StringType)
        .add("address", StringType)
        .add("birth_date", StringType)
      )
      .add("card", new StructType()
        .add("number", StringType)
        .add("bank", StringType)
        .add("agency", StringType)
        .add("account", StringType)
      )
      .add("company", StringType)
      .add("item", new StructType()
        .add("name", StringType)
        .add("category", StringType)
        .add("price", DoubleType)
        .add("code", StringType)
        .add("sku", StringType)
        .add("brand", StringType)
        .add("stock", IntegerType)
        .add("manufacturing_at", StringType)
        .add("expiration_at", StringType)
      )
      .add("total_value", DoubleType)

    val parsedMessages = kafkaData.selectExpr("CAST(value AS STRING)").as[String]
      .select(from_json(col("value"), schema).alias("data"))
      .select("data.*")
      .withColumn("created_at", to_timestamp(col("created_at"), "yyyy-MM-dd HH:mm:ss"))
      .withColumn("year", year(col("created_at")))
      .withColumn("month", month(col("created_at")))
      .withColumn("day", dayofmonth(col("created_at")))

    val pathToSave = "./data/refined/"

    parsedMessages.writeStream
      .format("delta")
      .outputMode("append")
      .trigger(Trigger.AvailableNow())
      .option("checkpointLocation", s"$pathToSave/checkpoint")
      .partitionBy("year","month","day")
      .start(s"$pathToSave/data")
      .awaitTermination()

    spark.sql(s"CREATE TABLE IF NOT EXISTS delta_table USING DELTA LOCATION '$pathToSave'")

    spark.stop()
  }
}