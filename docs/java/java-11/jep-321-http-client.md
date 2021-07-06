---
layout: default
title: "JEP 321: HTTP Client"
parent: Java 11
grand_parent: Java
nav_order: 10.321
permalink: docs/java/java-11/jep-321-http-client/
---

# JEP 321: HTTP Client

Java 11 introduced HTTP Client ([JEP-321](https://openjdk.java.net/jeps/321){:target="_blank"}), which Standardize
the [incubated](https://openjdk.java.net/jeps/11) HTTP Client API introduced
in [JDK 9, via JEP 110](https://openjdk.java.net/jeps/110), and updated in JDK 10.

## File: `src/main/java/quickhack/Main.java`

{% highlight java %}
{% raw_include quickhacks/java/java-11/jep-321-http-client/src/main/java/quickhack/Main.java %}
{% endhighlight %}

## Run

```console
$ ./gradlew run

> Task :run
Found 49 links in page https://albertattard.github.io/quickhacks/docs/java/java-11/
 > https://albertattard.github.io/quickhacks/
 > https://albertattard.github.io/quickhacks/docs/aws/
 > https://albertattard.github.io/quickhacks/docs/aws/cli/
 ...
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

   Gradle 7.1.1
  ```
