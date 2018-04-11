name := "getting-dressed"
version := "0.1"
scalaVersion := "2.12.5"
enablePlugins(PackPlugin)

libraryDependencies ++= Dependencies.test

packMain := Map("get-dressed" -> "com.rewardsnet.getdressed.app.GetDressed")