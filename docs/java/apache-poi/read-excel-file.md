---
layout: default
title: Read Excel File
parent: Apache POI
grand_parent: Java
nav_order: 18
permalink: docs/java/apache-poi/read-excel-file/
---

# Read Excel File

## File: `src/main/java/quickhack/Main.java`

{% highlight java %}
{% raw_include quickhacks/java/apache-poi/read-excel-file/src/main/java/quickhack/Main.java %}
{% endhighlight %}

## File: `build.gradle`

{% highlight java %}
{% raw_include quickhacks/java/apache-poi/read-excel-file/build.gradle %}
{% endhighlight %}

## Run the Example

```console
$ ./gradlew run

> Task :run
Sheet: Sheet1
1       Russia                  17098242
2       Canada                  9984670
3       United States           9826675
4       China                   9596961
5       Brazil                  8514877
6       Australia               7741220
7       India                   3287263
8       Argentina               2780400
9       Kazakhstan              2724900
10      Algeria                 2381741

BUILD SUCCESSFUL in 1s
3 actionable tasks: 1 executed, 2 up-to-date
```

## Versions

1. Java

   ```console
   $ java --version
   openjdk 16 2021-03-16
   OpenJDK Runtime Environment AdoptOpenJDK (build 16+36)
   OpenJDK 64-Bit Server VM AdoptOpenJDK (build 16+36, mixed mode, sharing)
   ```

1. Gradle

   ```console
   $ ./gradlew --version

   Gradle 7.1.1
   ```
