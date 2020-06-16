enablePlugins(ScalaJSPlugin)

name := "SimableXIV"

version := "0.1"

scalaVersion := "2.13.2"

val circeVersion = "0.12.3"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)


scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) }
scalaJSLinkerConfig ~= { _.withESFeatures(_.withUseECMAScript2015(true)) }
