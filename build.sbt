organization  := "com.agiledon"

version       := "0.1"

scalaVersion  := "2.10.3"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaVersion = "2.1.4"
  val sprayVersion = "1.1.1"
  val scalikeVersion = "2.0.4"
  val sparkVersion = "1.0.1"
  Seq(
    "io.spray"            %   "spray-can"         % sprayVersion,
    "io.spray"            %   "spray-routing"     % sprayVersion,
    "io.spray"            %   "spray-testkit"     % sprayVersion    % "test",
    "io.spray"            %%  "spray-json"        % "1.2.5",
    "org.json4s"          %%  "json4s-native"     % "3.2.4",
    "com.typesafe.akka"   %%  "akka-actor"        % akkaVersion,
    "com.typesafe.akka"   %%  "akka-testkit"      % akkaVersion     % "test",
    "org.scalikejdbc"     %%  "scalikejdbc"       % scalikeVersion,
    "org.apache.spark"    %%  "spark-core"        % sparkVersion,
    "org.apache.spark"    %%  "spark-streaming"   % sparkVersion,
    "org.specs2"          %%  "specs2"            % "2.2.3"         % "test",
    "org.scalatest"       %%  "scalatest"         % "2.1.0"         % "test",
    "com.novocode"        %   "junit-interface"   % "0.7"           % "test->default",
    "org.apache.hbase" % "hbase" % "0.95.2-hadoop2",
    "org.apache.hbase" % "hbase-client" % "0.95.2-hadoop2",
    "org.apache.hbase" % "hbase-common" % "0.95.2-hadoop2"
  )
}

Revolver.settings
