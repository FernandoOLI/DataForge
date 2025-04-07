package model

import org.apache.spark.sql.types._

object TransactionSchema {
  def schema: StructType = new StructType()
    .add("id", StringType)
    .add("created_at", StringType)
    .add("buyer", Buyer.schema)
    .add("card", Card.schema)
    .add("company", StringType)
    .add("item", Item.schema)
    .add("total_value", DoubleType)
}