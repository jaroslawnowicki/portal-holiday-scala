name := "play"

version := "1.0"

lazy val `play` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(javaCore, javaJdbc,  javaJpa, jdbc , cache , ws   , specs2 % Test )

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"  


//libraryDependencies ++= Seq(
//  javaCore,
//      javaJdbc,
//     	  javaJpa,
//      jdbc,
//      anorm,
//      cache,
//       "postgresql" % "postgresql" % "8.4-701.jdbc4",
//       "org.apache.directory.api" % "api-all" % "1.0.0-M14",
//  	"org.hibernate" % "hibernate-core" % "4.0.2.Final",
//  "org.hibernate" % "hibernate-entitymanager" % "4.0.2.Final",
//  "org.hibernate.javax.persistence" % "hibernate-jpa-2.1-api" % "1.0.0.Draft-16"
//)


//play.Project.playScalaSettings
