scalaVersion := "2.13.6"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := "aggregator",
    Compile / scalaSource := baseDirectory.value / "src/main/scala",
    Test / scalaSource := baseDirectory.value / "src/test/scala",
    libraryDependencies ++= Seq(
      guice,
      ws,
      "com.typesafe.play"             %% "play-server"                      % "2.8.8",
      "com.softwaremill.sttp.client3" %% "core"                             % "3.3.9",
      "com.softwaremill.sttp.client3" %% "async-http-client-backend-future" % "3.3.9",
      "org.scalactic"                 %% "scalactic"                        % "3.2.9",
      
      // Test
      "org.scalatest"          %% "scalatest"          % "3.2.9" % "test",
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % "test"
    )
  )