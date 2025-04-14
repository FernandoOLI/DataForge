package model

import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CardSpec extends AnyFlatSpec with Matchers {

  "Card" should "have correct schema" in {
    Card.schema shouldBe a [StructType]
    Card.schema.fields should contain allOf(
      StructField("number", StringType),
      StructField("bank", StringType),
      StructField("agency", StringType),
      StructField("account", StringType)
    )
  }

  "Card case class" should "store values correctly" in {
    val card = Card(
      number = "4111-1111-1111-1111",
      bank = "National Bank",
      agency = "001",
      account = "123456-7"
    )

    card.number shouldBe "4111-1111-1111-1111"
    card.bank shouldBe "National Bank"
    card.agency shouldBe "001"
    card.account shouldBe "123456-7"
  }
}