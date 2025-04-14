package model

import org.apache.spark.sql.types._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ItemSpec extends AnyFlatSpec with Matchers {

  "Item" should "have correct schema" in {
    Item.schema shouldBe a [StructType]
    Item.schema.fields should contain allOf(
      StructField("name", StringType),
      StructField("category", StringType),
      StructField("price", DoubleType),
      StructField("code", StringType),
      StructField("sku", StringType),
      StructField("brand", StringType),
      StructField("stock", IntegerType),
      StructField("manufacturing_at", StringType),
      StructField("expiration_at", StringType)
    )
  }

  "Item case class" should "store values correctly" in {
    val item = Item(
      name = "Smartphone",
      category = "Electronics",
      price = 999.99,
      code = "SP-123",
      sku = "SKU12345",
      brand = "TechBrand",
      stock = 10,
      manufacturingAt = "2023-01-01",
      expirationAt = None
    )

    item.name shouldBe "Smartphone"
    item.price shouldBe 999.99
    item.stock shouldBe 10
    item.expirationAt shouldBe None
  }

  "Item with expiration" should "handle optional field" in {
    val perishableItem = Item(
      name = "Milk",
      category = "Food",
      price = 3.99,
      code = "ML-456",
      sku = "SKU67890",
      brand = "DairyCo",
      stock = 50,
      manufacturingAt = "2023-05-01",
      expirationAt = Some("2023-06-01")
    )

    perishableItem.expirationAt shouldBe Some("2023-06-01")
  }
}