import config.SparkConfig
import service.{DeltaService, KafkaService}

object Main {

  def main(args: Array[String]): Unit = {
    val spark = SparkConfig.createSession()
    try {
      val kafkaDF = KafkaService.readFromKafka(spark)
      DeltaService.writeToDelta(kafkaDF)
      DeltaService.createDeltaTable(spark)
    } finally {
      spark.stop()
    }
  }

}