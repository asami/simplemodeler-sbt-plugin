sbtPlugin := true

name:= "simplemodelerplugin"

organization := "org.simplemodeling"

version := "0.3.3-SNAPSHOT"

scalaVersion := "2.9.1"

scalacOptions += "-deprecation"

scalacOptions += "-unchecked"

resolvers += "Asami Maven Repository" at "http://www.asamioffice.com/maven"

libraryDependencies += "org.simplemodeling" %% "simplemodeler" % "0.3.3-SNAPSHOT"
