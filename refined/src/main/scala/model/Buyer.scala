package model

import org.apache.spark.sql.types.{StringType, StructType};

object Buyer {
  val schema: StructType = new StructType()
    .add("name", StringType)
    .add("cpf", StringType)
    .add("phone", StringType)
    .add("email", StringType)
    .add("address", StringType)
    .add("birth_date", StringType)
}

case class Buyer(
                  name: String,
                  cpf: String,
                  phone: String,
                  email: String,
                  address: String,
                  birthDate: String
                )