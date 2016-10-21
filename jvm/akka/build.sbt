val echoHttp = project
  .in(file("."))

organization := "com.ericsson.research"
name         := "akka-http-echo-message"
licenses     += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))

scalaVersion   := "2.11.8"
scalacOptions ++= List(
  "-unchecked",
  "-deprecation",
  "-language:_",
  "-target:jvm-1.8",
  "-encoding", "UTF-8"
)

unmanagedSourceDirectories.in(Compile) := List(scalaSource.in(Compile).value)
unmanagedSourceDirectories.in(Test)    := List(scalaSource.in(Test).value)

libraryDependencies ++= List(
  "com.typesafe.akka" %% "akka-http-experimental" % "2.4.8"
)

initialCommands := """|import com.ericsson.research.perftest.echo._""".stripMargin
