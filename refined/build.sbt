ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.12.15"

lazy val root = (project in file("."))
  .settings(
    name := "refined"
  )

resolvers ++= Seq(
  "Maven Central" at "https://repo1.maven.org/maven2/",
  "Delta Lake IO" at "https://repo.delta.io/",
  "Confluent" at "https://packages.confluent.io/maven/"
)

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "3.3.1",
  "org.apache.spark" %% "spark-sql" % "3.3.1",
  "org.apache.spark" %% "spark-sql-kafka-0-10" % "3.3.1", // Nome correto para Kafka
  "io.delta" %% "delta-core" % "2.4.0" // Versão mais recente do Delta Lake
)