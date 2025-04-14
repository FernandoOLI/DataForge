package model

import org.apache.spark.sql.types._

object Item {
  val schema: StructType = new StructType()
    .add("name", StringType)
    .add("category", StringType)
    .add("price", DoubleType)
    .add("code", StringType)
    .add("sku", StringType)
    .add("brand", StringType)
    .add("stock", IntegerType)
    .add("manufacturing_at", StringType)
    .add("expiration_at", StringType)
}

case class Item(
                 name: String,
                 category: String,
                 price: Double,
                 code: String,
                 sku: String,
                 brand: String,
                 stock: Int,
                 manufacturingAt: String,
                 expirationAt: Option[String] = None
               )