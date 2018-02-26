//Tracethret-aws-core

name := "tracethreat-awscore"
 
version := "0.0.1"
 
scalaVersion := "2.11.2" 

organization := "systems.tracethreat.awscore"

libraryDependencies += "org.scala-lang" % "scala-library" % "2.11.2"

libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.0.9"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test"

libraryDependencies += "org.specs2" %% "specs2" % "2.3.12" % "test"

libraryDependencies += "com.typesafe.slick" %% "slick" % "3.0.0"

libraryDependencies ++= Seq(
   "junit" % "junit" % "4.8.1" % "test"
)

resolvers += "Typesafe repository releases" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies += "com.github.nscala-time" %% "nscala-time" % "2.0.0"

resolvers ++= Seq(
  "Sonatype Snapshots Repository"  at "http://oss.sonatype.org/content/repositories/snapshots",
  "Sonatype Releases Repository"   at "http://oss.sonatype.org/content/repositories/releases",
  "Spray Repository" at "http://repo.spray.io",
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/",
  "Akka Releases Repository" at "http://repo.akka.io/releases/"
)
  

libraryDependencies ++= {
  val akkaV = "2.5.10"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaV,
	"com.typesafe.akka" %% "akka-remote" %  akkaV ,
	"com.typesafe.akka" %% "akka-slf4j" %  akkaV ,
	"com.typesafe.akka" %% "akka-stream" %  akkaV ,
	"com.typesafe.akka" %% "akka-stream-testkit" %  akkaV ,
	"com.typesafe.akka" %% "akka-testkit" %  akkaV
  )
}

//SQS Streams
libraryDependencies += "com.lightbend.akka" %% "akka-stream-alpakka-sqs" % "0.17"

libraryDependencies += "com.github.seratch" %% "awscala" % "0.6.+"

// Scala 2.10, 2.11, Query with MySQL 
libraryDependencies ++= Seq( 
  "com.typesafe.slick" %% "slick" % "3.1.1",
  "com.typesafe.slick" %% "slick-testkit" % "3.1.1" % "test",
  "com.github.tototoshi" %% "slick-joda-mapper" % "2.1.0",
  "com.typesafe.slick" %% "slick-codegen" % "3.1.1", 
  "com.typesafe.slick" %% "slick-hikaricp" % "3.1.1",
  "mysql" % "mysql-connector-java" % "5.1.38",
  "joda-time" % "joda-time" % "2.7",
  "org.joda" % "joda-convert" % "1.7",
  "com.h2database"  %  "h2"                % "1.4.190",
  "ch.qos.logback"  %  "logback-classic"   % "1.1.3"
)

//Dependency injection
libraryDependencies ++= Seq("org.scaldi" %% "scaldi-akka" % "0.5.7")

//Messsage  
libraryDependencies ++= Seq(
  "org.apache.thrift" % "libthrift" % "0.8.0",
  "com.twitter" %% "scrooge-core" % "4.6.0",
  "com.twitter" %% "finagle-thrift" % "6.33.0",
  "com.twitter" % "bijection-core_2.11" % "0.9.2",
  "com.twitter" % "bijection-scrooge_2.11" % "0.9.2"
)
 

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-Xlint",
  "-Ywarn-dead-code",
  "-language:_",
  "-target:jvm-1.7",
  "-encoding", "UTF-8"
)
 
javaOptions := Seq("-Xdebug", "-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8000")
