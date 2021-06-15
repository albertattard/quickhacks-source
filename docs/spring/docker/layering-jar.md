---
layout: default
title: Layering Jar
parent: Docker (Spring)
grand_parent: Spring
nav_order: 11
permalink: docs/spring/docker/layering-jar/
---

# Layering Jar

## File: `Dockerfile`

{% highlight docker %}
{% raw_include quickhacks/spring/docker/layering-jar/Dockerfile %}
{% endhighlight %}

## Build and Run

1. Build the docker image

   ```console
   $ docker build . -t layering-jar:local
   ```

1. Run the docker image

   ```console
   $ docker run --rm --name layering-jar -p 8080:8080 layering-jar:local
   ```

1. Access the Health endpoint

   ```console
   $ curl "http://localhost:8080/actuator/health/" | jq
   {
     "status": "UP"
   }
   ```

## Versions

- Java

  ```console
  $ java --version
  openjdk 11.0.10 2021-01-19
  OpenJDK Runtime Environment AdoptOpenJDK (build 11.0.10+9)
  OpenJDK 64-Bit Server VM AdoptOpenJDK (build 11.0.10+9, mixed mode)
  ```

- Gradle

  ```console
  $ ./gradlew --version

  Gradle 7.0.2

  Build time:   2021-05-14 12:02:31 UTC
  Revision:     1ef1b260d39daacbf9357f9d8594a8a743e2152e

  Kotlin:       1.4.31
  Groovy:       3.0.7
  Ant:          Apache Ant(TM) version 1.10.9 compiled on September 27 2020
  JVM:          11.0.10 (AdoptOpenJDK 11.0.10+9)
  OS:           Mac OS X 10.16 x86_64
  ```

- Docker

  ```console
  $ docker --version
  Docker version 20.10.6, build 370c289
  ```
  
- jq

  ```console
  $ jq --version
  jq-1.6
  ```
