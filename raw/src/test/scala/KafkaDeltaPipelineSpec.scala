import org.scalatest.matchers.should.Matchers
import org.mockito.MockitoSugar


class KafkaDeltaPipelineSpec extends SparkTestBase with Matchers with MockitoSugar {

  test("should create SparkSession with Delta configurations") {
    val pipeline = new KafkaDeltaPipeline("test:9092", "test-topic", "/tmp/output")
    val spark = pipeline.createSparkSession()

    spark.conf.get("spark.sql.extensions") shouldEqual "io.delta.sql.DeltaSparkSessionExtension"
    spark.conf.get("spark.sql.catalog.spark_catalog") shouldEqual "org.apache.spark.sql.delta.catalog.DeltaCatalog"
  }

  test("should add metadata columns correctly") {
    import spark.implicits._
    val pipeline = new KafkaDeltaPipeline("test:9092", "test-topic", "/tmp/output")
    val testDF = Seq(("key1", "value1")).toDF("key", "value")

    val resultDF = pipeline.addMetadataColumns(testDF)

    resultDF.columns should contain allOf("received_at", "year", "month", "day")
  }
}