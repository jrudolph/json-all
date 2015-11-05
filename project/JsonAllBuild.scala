import sbt._
import Keys._

object JsonAllBuild extends Build {
  val root =
    Project("root", file("."))
      .aggregate(json4sAst, sprayJson, jawn, rojoma, argonaut)

  lazy val json4sAst = ProjectRef(file("json4s-ast").getAbsoluteFile, "json4sASTJVM")
  lazy val sprayJson = ProjectRef(file("spray-json"), "spray-json")
  lazy val jawn = ProjectRef(file("jawn"), "ast")
  lazy val rojoma = ProjectRef(file("rojoma-json"), "rojoma-json")
  lazy val argonaut = ProjectRef(file("argonaut"), "argonaut")

  override def buildLoaders =
    BuildLoader.transform(demoTransformer) ::
      Nil

  def demoTransformer: BuildLoader.TransformInfo => BuildUnit = { ti =>
    //println(s"transformer called with ${ti.base}: $ti")
    if (!ti.base.getName.contains("json4s-ast"))
        new BuildUnit(ti.unit.uri, ti.unit.localBase,
          {
            val defs = ti.unit.definitions
            import defs._
            new LoadedDefinitions(base, target, loader, builds, defs.projects.map(_ dependsOn json4sAst % "compile -> compile"), buildNames, dslDefinitions)
          },ti.unit.plugins)
    else ti.unit
  }
}