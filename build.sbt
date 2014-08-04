organization  := "com.agiledon"

version       := "0.1"

scalaVersion  := "2.10.3"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaV = "2.1.4"
  val sprayV = "1.1.1"
  val scalikeV = "2.0.4"
  Seq(
    "io.spray"            %   "spray-can"     % sprayV,
    "io.spray"            %   "spray-routing" % sprayV,
    "io.spray"            %   "spray-testkit" % sprayV  % "test",
    "io.spray"            %  "spray-json_2.10"    % "1.2.5",
    "org.json4s"          %% "json4s-native"      % "3.2.4",
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "org.scalikejdbc"     %% "scalikejdbc"    % scalikeV,
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaV   % "test",
    "org.specs2"          %%  "specs2"        % "2.2.3" % "test",
    "org.scalatest"      %% "scalatest"           % "2.1.0"        % "test",
    "com.novocode"        % "junit-interface"     % "0.7"          % "test->default"
  )
}

Revolver.settings
