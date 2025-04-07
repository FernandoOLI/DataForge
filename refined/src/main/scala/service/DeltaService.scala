package service

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.streaming.Trigger

object DeltaService {
  val outputPath = "./data/refined/"

  def writeToDelta(df: DataFrame): Unit = {
    val processedDF = df
      .withColumn("created_at", to_timestamp(col("created_at"), "yyyy-MM-dd HH:mm:ss"))
      .withColumn("year", year(col("created_at")))
      .withColumn("month", month(col("created_at")))
      .withColumn("day", dayofmonth(col("created_at")))

    processedDF.writeStream
      .format("delta")
      .outputMode("append")
      .trigger(Trigger.AvailableNow())
      .option("checkpointLocation", s"$outputPath/checkpoint")
      .partitionBy("year","month","day")
      .start(s"$outputPath/data")
      .awaitTermination()
  }

  def createDeltaTable(spark: SparkSession): Unit = {
    spark.sql(s"CREATE TABLE IF NOT EXISTS delta_table USING DELTA LOCATION '$outputPath'")
  }
}