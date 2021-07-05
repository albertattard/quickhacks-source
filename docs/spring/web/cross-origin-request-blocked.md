---
layout: default
title: Cross-Origin Request Blocked
parent: Web
grand_parent: Spring
nav_order: 3
permalink: docs/spring/web/cross-origin-request-blocked/
---

# Cross-Origin Request Blocked

The browser will block the return path if the CORS header `Access-Control-Allow-Origin` is missing. The web application
still carries out its task and make the changes requested. Only the response is blocked by the browser.

![Cross-Origin Request Blocked]({{ "/assets/images/spring/web/cross-origin-request-blocked.png" | absolute_url }})

Spring Security can be used to also block request that are not coming from trusted origins. Refer
to [spring-security-cors]({{ "/docs/spring/web/spring-security-cors/" | absolute_url }}) for an
example.

## File: `src/main/java/quickhacks/CorsConfiguration.java`

{% highlight java %}
{% raw_include quickhacks/spring/web/cross-origin-request-blocked/src/main/java/quickhacks/CorsConfiguration.java %}
{% endhighlight %}

## File: `src/test/java/quickhacks/CorsHeadersTest.java`

{% include custom/note.html details="RestTemplate makes use of the HttpUrlConnection class, which has the
   <code>Origin</code> header as one of the restricted headers. Set the <code>sun.net.http.allowRestrictedHeaders</code>
   property to <code>true</code> so that the <code>Origin</code> header is also sent." %}

{% highlight java %}
{% raw_include quickhacks/spring/web/cross-origin-request-blocked/src/test/java/quickhacks/CorsHeadersTest.java %}
{% endhighlight %}

## File: `build.gradle`

{% highlight gradle %}
{% raw_include quickhacks/spring/web/cross-origin-request-blocked/build.gradle %}
{% endhighlight %}

## Commands

- Run tests

  ```console
  $ ./gradlew check
  ```

- Run application

  ```console
  $ ./gradlew bootRun
  ```

- Make CURl request

  ```console
  $ curl -v \
     -X "POST" "http://localhost:8080/quickhack/name?name=Quickhacks" \
     -H "Origin: http://localhost:3000"
  ```

## Versions

- Java

  ```console
  $ java -version
  openjdk 16 2021-03-16
  OpenJDK Runtime Environment AdoptOpenJDK (build 16+36)
  OpenJDK 64-Bit Server VM AdoptOpenJDK (build 16+36, mixed mode, sharing)
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
