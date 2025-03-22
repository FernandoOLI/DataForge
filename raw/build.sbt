ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.12.15"

lazy val root = (project in file("."))
  .settings(
    name := "raw",
    resolvers ++= Seq(
      "Maven Central" at "https://repo1.maven.org/maven2/",
      "Delta Lake IO" at "https://repo.delta.io/",
      "Confluent" at "https://packages.confluent.io/maven/"
    ),
    libraryDependencies ++= Seq(
      "org.apache.spark" %% "spark-core" % "3.5.0",
      "org.apache.spark" %% "spark-sql" % "3.5.0",
      "org.apache.spark" %% "spark-sql-kafka-0-10" % "3.5.0",
      "io.delta" %% "delta-spark" % "3.2.0"
    )
  )