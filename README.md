# Hello World native-image GraalVM example in Scala

## Install GraalVM
1. Three possibilities here:

   * Install GraalVM Enterprise Edition for your operating system by downloading a pre-built version: http://www.oracle.com/technetwork/oracle-labs/program-languages/downloads/index.html
   * Install GraalVM Community Edition from the official Github repository: https://github.com/oracle/graal/releases (only a Linux version is for the moment available)
   * Cloning and building your version of GraalVM from the official Github repository: https://github.com/oracle/graal
 
2. Extract the archive

3. Finally simply export the `bin` folder to your `PATH`
  * Linux: `export PATH="/path/to/graalvm/bin:$PATH"`
  * Mac OS: `export PATH="/path/to/graalvm/Contents/Home/bin:$PATH"`

**Do not forget to add the command line above to your `~/.profile` for future usage**

Check that everything is correct with `echo $PATH`

## Create a fat JAR of the project

We are using `sbt-assembly` to create a fat JAR for us.
Description taken from the repository (https://github.com/sbt/sbt-assembly)
> sbt-assembly is a sbt plugin originally ported from codahale's assembly-sbt, which I'm guessing was inspired by Maven's assembly plugin. The goal is simple: Create a fat JAR of your project with all of its dependencies.

In order to create the fat JAR, since `sbt-assembly` is already present in our project, we just have to do `sbt assembly` at the root of our project

You should see the following output:

```
[info] Loading settings from assembly.sbt ...
[info] Loading project definition from /path/to/project/hello-world-scala-graalvm/project
[info] Loading settings from build.sbt ...
[info] Set current project to hello-world-scala-graalvm (in build file:/path/to/project/hello-world-scala-graalvm/)
[info] Compiling 1 Scala source to /path/to/project/hello-world-scala-graalvm/target/scala-2.12/classes ...
[info] Done compiling.
[info] Including from cache: scala-library-2.12.5.jar
[info] Checking every *.class/*.jar file's SHA-1.
[info] Merging files...
[warn] Merging 'META-INF/MANIFEST.MF' with strategy 'discard'
[warn] Strategy 'discard' was applied to a file
[info] SHA-1: b8098009dc6ad381e43c481d0fb23ebc68e66cda
[info] Packaging /path/to/project/hello-world-scala-graalvm/target/scala-2.12/hello-world.jar ...
[info] Done packaging.
[success] Total time: 6 s, completed Apr 21, 2018 9:26:49 PM
```

We then simply use `native-image` binary from the GraalVM to create our native image `native-image -jar target/scala-2.12/hello-world.jar`

You should see the following output:

```
Build on Server(pid: 45153, port: 26681)
   classlist:   2,357.02 ms
       (cap):   1,576.42 ms
       setup:   2,278.55 ms
  (typeflow):   3,084.05 ms
   (objects):   2,302.57 ms
  (features):      34.19 ms
    analysis:   5,507.09 ms
    universe:     271.12 ms
     (parse):     787.74 ms
    (inline):     562.75 ms
   (compile):   5,289.08 ms
     compile:   7,166.70 ms
       image:   2,391.88 ms
       write:   1,043.90 ms
     [total]:  21,068.57 ms
```

## Executing the native image

A simple `./hello-world` will do the job :smile:

Check the time it takes for the program to execute!
`time ./hello-world`
