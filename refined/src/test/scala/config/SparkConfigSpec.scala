package config

import config.SparkConfig
import org.scalatest.BeforeAndAfterAll
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class SparkConfigSpec extends AnyFlatSpec with Matchers with BeforeAndAfterAll {

  "SparkConfig" should "create a SparkSession with Delta extensions" in {
    val spark = SparkConfig.createSession()

    try {
      spark should not be null
      spark.conf.get("spark.sql.extensions") should include("DeltaSparkSessionExtension")
      spark.conf.get("spark.sql.catalog.spark_catalog") should include("DeltaCatalog")
    } finally {
      spark.stop()
    }
  }

  it should "have local master configuration" in {
    val spark = SparkConfig.createSession()

    try {
      spark.conf.get("spark.master") shouldBe "local[*]"
    } finally {
      spark.stop()
    }
  }
}