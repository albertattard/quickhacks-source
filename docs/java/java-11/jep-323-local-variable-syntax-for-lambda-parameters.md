---
layout: default
title: "JEP 323: Local-Variable Syntax for Lambda Parameters"
parent: Java 11
grand_parent: Java
nav_order: 10.323
permalink: docs/java/java-11/jep-323-local-variable-syntax-for-lambda-parameters/
---

# JEP 323: Local-Variable Syntax for Lambda Parameters

## File: `src/main/java/quickhack/Main.java`

{% highlight java %}
{% raw_include quickhacks/java/java-11/jep-323-local-variable-syntax-for-lambda-parameters/src/main/java/quickhack/Main.java %}
{% endhighlight %}

## Compile and run with Java 10

{% highlight gradle %}
{% raw_include quickhacks/java/java-11/jep-323-local-variable-syntax-for-lambda-parameters/build-java10.gradle %}
{% endhighlight %}

```console
$ ./gradlew clean build
```

The program fails to compile with the error `error: 'var' is not allowed here`.

```
> Task :compileJava FAILED
/quickhacks/java/java-11/jep-323-local-variable-syntax-for-lambda-parameters/src/main/java/quickhack/Main.java:13: error: 'var' is not allowed here
        compute((var a, var b) -> a - b); /* Supported from Java 11 onwards */
                 ^
/quickhacks/java/java-11/jep-323-local-variable-syntax-for-lambda-parameters/src/main/java/quickhack/Main.java:13: error: 'var' is not allowed here
        compute((var a, var b) -> a - b); /* Supported from Java 11 onwards */
                        ^
```

## Compile and run with Java 11

{% highlight gradle %}
{% raw_include quickhacks/java/java-11/jep-323-local-variable-syntax-for-lambda-parameters/build.gradle %}
{% endhighlight %}

```console
$ ./gradlew clean build
```

The same code compiles with Java 11

```console
BUILD SUCCESSFUL in 1s
6 actionable tasks: 6 executed
```

## Versions

- Java 11

  ```console
  $ java --version
  openjdk 11.0.10 2021-01-19
  OpenJDK Runtime Environment AdoptOpenJDK (build 11.0.10+9)
  OpenJDK 64-Bit Server VM AdoptOpenJDK (build 11.0.10+9, mixed mode)
  ```

- Gradle

  ```console
  $ ./gradlew --version

  Gradle 7.0

  Build time:   2021-04-09 22:27:31 UTC
  Revision:     d5661e3f0e07a8caff705f1badf79fb5df8022c4

  Kotlin:       1.4.31
  Groovy:       3.0.7
  Ant:          Apache Ant(TM) version 1.10.9 compiled on September 27 2020
  JVM:          11.0.10 (AdoptOpenJDK 11.0.10+9)
  OS:           Mac OS X 10.16 x86_64
  ```
