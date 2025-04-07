package model

import model.Buyer
import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class BuyerSpec extends AnyFlatSpec with Matchers {

  "Buyer" should "have correct schema" in {
    Buyer.schema shouldBe a [StructType]
    Buyer.schema.fields should contain allOf(
      StructField("name", StringType),
      StructField("cpf", StringType),
      StructField("phone", StringType),
      StructField("email", StringType),
      StructField("address", StringType),
      StructField("birth_date", StringType)
    )
  }

  "Buyer case class" should "store values correctly" in {
    val buyer = Buyer(
      name = "John Doe",
      cpf = "123.456.789-00",
      phone = "+1-555-123-4567",
      email = "john@example.com",
      address = "123 Main St",
      birthDate = "1980-01-01"
    )

    buyer.name shouldBe "John Doe"
    buyer.cpf shouldBe "123.456.789-00"
    buyer.email shouldBe "john@example.com"
    buyer.address shouldBe "123 Main St"
  }
}