import com.lihaoyi.workbench.Plugin._
import com.typesafe.sbt.packager.archetypes.JavaAppPackaging
import com.typesafe.sbt.web.SbtWeb
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import sbt.Keys._
import sbt._
import spray.revolver.RevolverPlugin._

/**
  * Application settings. Configure the build for your application here.
  * You normally don't have to touch the actual build definition after this.
  */
object Settings {

  val name = "website" /* The name of your application */
  val version = "0.1.0" /* The version of your application */

  /** Declare global dependency versions here to avoid mismatches in multi part dependencies */
  object versions {
    val scala = "2.11.8"
    val scalajsReact = "0.11.0"
    val scalaCSS = "0.4.1"
    val react = "15.4.2"
    val bootstrap = "3.3.2"
    val spray = "1.3.2"
    val akka = "2.3.9"
    val reactBootstrap = "0.28.3"
    val scalaTags = "0.5.4"
    val scalaDom = "0.9.1"
  }

  resolvers ++= Seq(
    "Apache repo" at "https://repository.apache.org/content/repositories/releases",
    "Local Repo" at Path.userHome.asFile.toURI.toURL + "/.m2/repository",
    "Local Ivy Repo" at Path.userHome.asFile.toURI.toURL + "/.iv2/local",
    Resolver.mavenLocal
  )

  /*
    * These dependencies are shared between JS and JVM projects
    * the special %%% function selects the correct version for each project
    */
  val sharedDependencies = Def.setting(Seq(
    "com.lihaoyi" %%% "scalatags" % versions.scalaTags,
    "io.spray" %% "spray-httpx" % versions.spray,
    "io.spray" %% "spray-json" % versions.spray
  ))

  /* Dependencies only used by the JVM project */
  val jvmDependencies = Def.setting(Seq(
    "io.spray" %% "spray-can" % versions.spray,
    "io.spray" %% "spray-routing" % versions.spray,
    "io.spray" %% "spray-client" % versions.spray,
    "com.typesafe.akka" %% "akka-actor" % versions.akka
  ))

  /* Dependencies only used by the JS project (note the use of %%% instead of %%) */
  val scalajsDependencies = Def.setting(Seq(
    "com.github.japgolly.scalajs-react" %%% "core" % versions.scalajsReact,
    "com.github.japgolly.scalajs-react" %%% "extra" % versions.scalajsReact,
    "com.github.japgolly.scalacss" %%% "ext-react" % versions.scalaCSS,
    "org.scala-js" %%% "scalajs-dom" % versions.scalaDom
  ))

  /* Dependencies for external JS libs that are bundled into a single .js file according to dependency order */
  val jsDependencies = Def.setting(Seq(
    "org.webjars.bower" % "react" % versions.react / "react-with-addons.js" commonJSName "React",
    "org.webjars.bower" % "react" % versions.react / "react-dom.js"
  ))

}

object ApplicationBuild extends Build {

  // root project aggregating the JS and JVM projects
  lazy val root = project.in(file(".")).
    aggregate(js, jvm).
    settings(
      name := "website",
      version := Settings.version
    )

  val sharedSrcDir = "shared"
  val productionBuild = settingKey[Boolean]("Build for production")

  // a special crossProject for configuring a JS/JVM/shared structure
  lazy val website = crossProject.in(file("."))
    .settings(
      name := Settings.name,
      version := Settings.version,
      scalaVersion := Settings.versions.scala,
      libraryDependencies ++= Settings.sharedDependencies.value
    )

    // set up settings specific to the JVM project
    .jvmSettings(Revolver.settings: _*)
    .jvmSettings(
      libraryDependencies ++= Settings.jvmDependencies.value
    )
    // set up settings specific to the JS project
    .jsSettings(workbenchSettings: _*)
    .jsSettings(
      libraryDependencies ++= Settings.scalajsDependencies.value,
      jsDependencies ++= Settings.jsDependencies.value,
      skip in packageJSDependencies := false,
      refreshBrowsers <<= refreshBrowsers.triggeredBy(fastOptJS in Compile),
      bootSnippet := "Main().main();"
    )

  // configure a specific directory for scalajs output
  val scalajsOutputDir = Def.settingKey[File]("directory for javascript files output by scalajs")

  // make all JS builds use the output dir defined later
  lazy val js2jvmSettings = Seq(fastOptJS, fullOptJS, packageJSDependencies) map { packageJSKey =>
    crossTarget in(js, Compile, packageJSKey) := scalajsOutputDir.value
  }

  // instantiate the JS project for SBT with some additional settings
  lazy val js: Project = website.js

  // instantiate the JVM project for SBT with some additional settings
  lazy val jvm: Project = website.jvm.settings(js2jvmSettings: _*).settings(
    scalajsOutputDir := (classDirectory in Compile).value / "web" / "js",
    Revolver.reStart <<= Revolver.reStart dependsOn (fastOptJS in(js, Compile))
  ).enablePlugins(SbtWeb).enablePlugins(JavaAppPackaging)
}