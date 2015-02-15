name := "hasan simulator"

scalaVersion := "2.11.5"

updateOptions := updateOptions.value.withCachedResolution(true)

enablePlugins(ScalaJSPlugin)

skip in packageJSDependencies := false

persistLauncher in Compile := true

persistLauncher in Test := false

libraryDependencies ++= Seq(
  "be.doeraene" %%% "scalajs-jquery" % "0.8.0"
)

scalariformSettings
