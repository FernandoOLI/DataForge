import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import org.apache.spark.sql.types._

class StreamingTestHelper(spark: SparkSession) {
  private implicit val sqlContext = spark.sqlContext

  def createTestKafkaData(values: Seq[String]): DataFrame = {
    val schema = StructType(Seq(
      StructField("key", BinaryType),
      StructField("value", BinaryType),
      StructField("topic", StringType),
      StructField("partition", IntegerType),
      StructField("offset", LongType),
      StructField("timestamp", TimestampType),
      StructField("timestampType", IntegerType)
    ))

    val rows = values.map { v =>
      Row(v.getBytes, v.getBytes, "test-topic", 0, 0L, null, 0)
    }

    spark.createDataFrame(spark.sparkContext.parallelize(rows), schema)
  }
}