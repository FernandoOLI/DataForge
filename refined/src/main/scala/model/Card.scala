package model

import org.apache.spark.sql.types._

object Card {
  val schema: StructType = new StructType()
    .add("number", StringType)
    .add("bank", StringType)
    .add("agency", StringType)
    .add("account", StringType)
}

case class Card(
                 number: String,
                 bank: String,
                 agency: String,
                 account: String
               )