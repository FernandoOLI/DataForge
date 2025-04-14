package config

import org.apache.spark.sql.SparkSession

object SparkConfig {
  def createSession(): SparkSession = {
    SparkSession.builder()
      .appName("Kafka to Delta Table")
      .master("local[*]")
      .config("spark.sql.extensions", "io.delta.sql.DeltaSparkSessionExtension")
      .config("spark.sql.catalog.spark_catalog", "org.apache.spark.sql.delta.catalog.DeltaCatalog")
      .getOrCreate()
  }
}