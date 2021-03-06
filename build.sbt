organization := "lt.dvim.sssio"
name := "sssio"
description := "Discover and interface with sbt server using standard input and output"

libraryDependencies ++= Seq(
  "io.circe"           %% "circe-parser"                           % "0.9.3",
  "io.circe"           %% "circe-generic"                          % "0.9.3",
  "com.lightbend.akka" %% "akka-stream-alpakka-unix-domain-socket" % "0.18",
  "com.typesafe.akka"  %% "akka-slf4j"                             % "2.5.11",
  "io.rbricks"         %% "scalog-backend"                         % "0.2.1",
)

licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))
homepage := Some(url("https://github.com/2m/sssio"))
scmInfo := Some(ScmInfo(url("https://github.com/2m/sssio"), "git@github.com:2m/sssio.git"))
developers += Developer("contributors",
                        "Contributors",
                        "https://gitter.im/2m/sssio",
                        url("https://github.com/2m/sssio/graphs/contributors"))
bintrayOrganization := Some("2m")
bintrayRepository := (if (isSnapshot.value) "snapshots" else "maven")
organizationName := "https://github.com/2m/sssio/graphs/contributors"
startYear := Some(2018)

scalafmtOnCompile := true
enablePlugins(AutomateHeaderPlugin)
