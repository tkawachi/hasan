name := "hasan simulator"

scalaVersion := "2.11.5"

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0"
)

updateOptions := updateOptions.value.withCachedResolution(true)

scalariformSettings
