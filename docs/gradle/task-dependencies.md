---
layout: default
title: Task Dependencies
parent: Gradle
nav_order: 20
permalink: docs/gradle/task-dependencies/
---

# Task Dependencies

## References

- [https://plugins.gradle.org/plugin/org.barfuin.gradle.taskinfo](https://plugins.gradle.org/plugin/org.barfuin.gradle.taskinfo)
- [https://youtu.be/Vxfk7OsEekA?t=105](https://youtu.be/Vxfk7OsEekA?t=105)

## File: `build.gradle`

{% highlight gradle %}
{% raw_include quickhacks/gradle/task-dependencies/build.gradle %}
{% endhighlight %}

## Display the Task Dependencies

```console
$ ./gradlew tiTree build

> Task :tiTree
:build                                          (org.gradle.api.DefaultTask)
+--- :assemble                                  (org.gradle.api.DefaultTask)
|    `--- :jar                                  (org.gradle.api.tasks.bundling.Jar)
|         `--- :classes                         (org.gradle.api.DefaultTask)
|              +--- :compileJava                (org.gradle.api.tasks.compile.JavaCompile)
|              `--- :processResources           (org.gradle.language.jvm.tasks.ProcessResources)
`--- :check                                     (org.gradle.api.DefaultTask)
     `--- :test                                 (org.gradle.api.tasks.testing.Test)
          +--- :classes                         (org.gradle.api.DefaultTask)
          |    +--- :compileJava                (org.gradle.api.tasks.compile.JavaCompile)
          |    `--- :processResources           (org.gradle.language.jvm.tasks.ProcessResources)
          `--- :testClasses                     (org.gradle.api.DefaultTask)
               +--- :compileTestJava            (org.gradle.api.tasks.compile.JavaCompile)
               |    `--- :classes               (org.gradle.api.DefaultTask)
               |         +--- :compileJava      (org.gradle.api.tasks.compile.JavaCompile)
               |         `--- :processResources (org.gradle.language.jvm.tasks.ProcessResources)
               `--- :processTestResources       (org.gradle.language.jvm.tasks.ProcessResources)


BUILD SUCCESSFUL in 768ms
1 actionable task: 1 executed
```

## Versions

1. Gradle

   ```console
   $ ./gradlew --version

   Gradle 7.0

   Build time:   2021-04-09 22:27:31 UTC
   Revision:     d5661e3f0e07a8caff705f1badf79fb5df8022c4

   Kotlin:       1.4.31
   Groovy:       3.0.7
   Ant:          Apache Ant(TM) version 1.10.9 compiled on September 27 2020
   JVM:          16 (AdoptOpenJDK 16+36)
   OS:           Mac OS X 10.16 x86_64
   ```
