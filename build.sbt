name := "hello-world-scala-graalvm"

version := "0.1"

scalaVersion := "2.12.5"

mainClass in assembly := Some("Hello")

assemblyJarName in assembly := "hello-world.jar"
