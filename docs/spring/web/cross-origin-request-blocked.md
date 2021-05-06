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

---

## File: `src/main/java/quickhacks/QuickhackController.java`

{% highlight java %}
{% raw_include quickhacks/spring/web/cross-origin-request-blocked/src/main/java/quickhacks/QuickhackController.java %}
{% endhighlight %}

## File: `build.gradle`

{% highlight gradle %}
{% raw_include quickhacks/spring/web/cross-origin-request-blocked/build.gradle %}
{% endhighlight %}

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
