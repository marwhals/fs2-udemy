ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "FS2-Udemy"
  )

addCompilerPlugin("org.typelevel" % "kind-projector" % "0.13.2" cross CrossVersion.full)

libraryDependencies += "co.fs2" %% "fs2-core" % "3.2.7"
libraryDependencies += "co.fs2" %% "fs2-io" % "3.2.7"