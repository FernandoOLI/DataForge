ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.12.15"

lazy val root = (project in file("."))
  .settings(
    name := "deltaToMongo",
    resolvers ++= Seq(
      "Maven Central" at "https://repo1.maven.org/maven2/",
      "Delta Lake IO" at "https://repo.delta.io/",
      "Confluent" at "https://packages.confluent.io/maven/"
    ),
    libraryDependencies ++= Seq(
      "org.apache.spark" %% "spark-core" % "3.3.0",
      "org.apache.spark" %% "spark-sql" % "3.3.0",
      "org.mongodb.spark" %% "mongo-spark-connector" % "10.1.1",
      "io.delta" %% "delta-core" % "2.2.0"
    )
  )

import sbtassembly.AssemblyPlugin.autoImport._

assembly / mainClass := Some("Main") // substitua "Main" se for outro nome

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}