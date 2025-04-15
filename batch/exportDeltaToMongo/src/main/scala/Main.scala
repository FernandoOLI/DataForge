import org.apache.spark.sql.SparkSession

object Main {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("Delta to MongoDB")
      .master("local[*]")
      .config("spark.mongodb.output.uri", "mongodb://mongodb:27017/refined?authSource=refined")
      .config("spark.sql.extensions", "io.delta.sql.DeltaSparkSessionExtension")
      .config("spark.sql.catalog.spark_catalog", "org.apache.spark.sql.delta.catalog.DeltaCatalog")
      .config("spark.jars.packages", "org.mongodb.spark:mongo-spark-connector_2.12:10.1.1")
      .getOrCreate()

    val deltaPath = "./data/silver/data"

    val df = spark.read.format("delta").load(deltaPath)

    df.show()
    // todo - in another project create clean data routine

    import org.apache.spark.sql.functions._
    import java.time.LocalDate

    val thirtyDaysAgo = LocalDate.now().minusDays(30).toString
    val filteredDF = df.filter(col("created_at") >= lit(thirtyDaysAgo))
    filteredDF.write
      .format("mongodb")
      .mode("append")
      .option("database", "refined")
      .option("collection", "teste")
      .option("replaceDocument", "false")
      .option("idFieldList", "id")
      .save()

    spark.stop()
  }
}
