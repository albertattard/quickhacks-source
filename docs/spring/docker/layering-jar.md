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

   Gradle 7.1.1
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
