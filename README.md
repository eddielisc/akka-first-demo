# sc-akka-first-demo

#### Description
first try for akka

a small application to find all files in predefined directory, then count the line number of them


#### Environment & Plugin Required

1. JDK 1.7
2. Maven
3. Akka Actor

#### Seq diagram
![alt tag](https://github.com/eddielisc/sc-akka-first-demo/blob/master/sc-akka-first-demo/doc/diagram.png)

#### Build & Run

###### Build

> mvn package

###### Run

> java -jar akkaDemo.jar [filePath]


#### Enhancement

1. file logging with SLF4J
2. Use any other method to stop all actors & system instead of calling system shutdown
3. use JAVA 8
4. recursively check if there is any subfolders and check all files's line num under it




