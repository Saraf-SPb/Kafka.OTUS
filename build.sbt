ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.10"

lazy val root = (project in file("."))
  .settings(
    name := "Kafka.OTUS"
  )

libraryDependencies ++= Seq(
  "org.apache.kafka" % "kafka-clients" % "3.1.0",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.13.3",
  "ch.qos.logback" % "logback-classic" % "1.2.11",
  "org.json4s" %% "json4s-jackson" % "4.0.5",
)