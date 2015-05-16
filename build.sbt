name := "Scrabble"

version := "1.0"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.1.2",
  "org.scalaz" %% "scalaz-effect" % "7.1.2",
  "org.scalaz" %% "scalaz-typelevel" % "7.1.2",
  "org.scalaz" %% "scalaz-scalacheck-binding" % "7.1.2" % "test",
  "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"
)

resolvers ++= Seq(
  "Sonatype OSS Releases"  at "http://oss.sonatype.org/content/repositories/releases/",
  "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"
)

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

initialCommands in console := "import scalaz._, Scalaz._"

initialCommands in console in Test := "import scalaz._, Scalaz._, scalacheck.ScalazProperties._, scalacheck.ScalazArbitrary._,scalacheck.ScalaCheckBinding._"
