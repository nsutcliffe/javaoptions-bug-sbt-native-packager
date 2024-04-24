import scala.collection.Seq

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.13"

ThisBuild / javaOptions += "-Dsome-property=true"

lazy val root = Project("root-project", file("."))
  .aggregate(javaOptionsNotDefinedProperly, javaOptionsDefinedProperly1, javaOptionsDefinedProperly2)

val javaOptionsDefinedProperly1 = module("module1")

val javaOptionsNotDefinedProperly = moduleWithPlugin("module2-nojavaoptions")

val javaOptionsDefinedProperly2 = moduleWithPluginAndAppendEmptyJavaOptions("module3")

def module(name:String) = Project(name, file(name))
def moduleWithPlugin(name: String) =
  module(name).enablePlugins(JavaServerAppPackaging)

def moduleWithPluginAndAppendEmptyJavaOptions(name: String): Project =
  moduleWithPlugin(name)
    .settings(javaOptions ++= Seq())
