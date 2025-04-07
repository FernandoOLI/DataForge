package config

import config.KafkaConfig
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class KafkaConfigSpec extends AnyFlatSpec with Matchers {

  "KafkaConfig" should "have correct bootstrap servers" in {
    KafkaConfig.bootstrapServers shouldBe "192.168.15.6:9092"
  }

  it should "have correct topic name" in {
    KafkaConfig.topic shouldBe "topic-test"
  }
}