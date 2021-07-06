---
layout: default
title: Java Compiler Warnings
parent: Gradle
nav_order: 9
permalink: docs/gradle/java-compiler-warnings/
---

# Java Compiler Warnings

Enabled warnings

- `Xlint:unchecked`
- `Xlint:deprecation`

## File: `build.gradle`

{% highlight gradle %}
{% raw_include quickhacks/gradle/java-compiler-warnings/build.gradle %}
{% endhighlight %}

# File: `src/main/java/quickhacks/Sample.java`

{% highlight java %}
{% raw_include quickhacks/gradle/java-compiler-warnings/src/main/java/quickhacks/Sample.java %}
{% endhighlight %}

# See the Compiler Warnings

```console
$ ./gradlew compileJava
```

The compilation warnings will be displayed

```console
./gradlew build
Starting a Gradle Daemon, 1 incompatible and 2 stopped Daemons could not be reused, use --status for details

> Task :compileJava 
quickhacks/gradle/java-compiler-warnings/src/main/java/quickhacks/Sample.java:10: warning: [unchecked] unchecked call to add(E) as a member of the raw type List
        strings.add("Quickhack");
                   ^
  where E is a type-variable:
    E extends Object declared in interface List
1 warning

BUILD SUCCESSFUL in 8s
2 actionable tasks: 2 executed
➜  java-compiler-warnings git:(main) ✗ 
```

This applies to all Java compilation tasks, including tests (task `compileTestJava`).

## Versions

1. Gradle

   ```console
   $ ./gradlew --version

   Gradle 7.1.1
   ```

1. Java

   ```console
   $ java --version
   openjdk 16 2021-03-16
   OpenJDK Runtime Environment AdoptOpenJDK (build 16+36)
   OpenJDK 64-Bit Server VM AdoptOpenJDK (build 16+36, mixed mode, sharing)
   ```
