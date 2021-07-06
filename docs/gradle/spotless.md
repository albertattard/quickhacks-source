---
layout: default
title: Spotless
parent: Gradle
nav_order: 19
permalink: docs/gradle/spotless/
---

# Spotless

Spotless is a versatile and flexible code formatter that also provides a gradle plugin. This hooks to the build and
fails it if the formatting does not abide to the configured format.

## References

- [https://github.com/diffplug/spotless](https://github.com/diffplug/spotless){:target="_blank"}
- [https://github.com/google/google-java-format](https://github.com/google/google-java-format){:target="_blank"}

## File: `build.gradle`

{% highlight gradle %}
{% raw_include quickhacks/gradle/spotless/build.gradle %}
{% endhighlight %}

## File: `src/main/java/quickhacks/Main.java`

{% highlight java %}
{% raw_include quickhacks/gradle/spotless/src/main/java/quickhacks/Main.java %}
{% endhighlight %}

## Run Spotless formatter

```console
$ ./gradlew spotlessApply
```

## Run Spotless checks

The build will fail if one or more files do not adhere to the required format. Run `spotlessApply` to format the code
first.

```console
$ ./gradlew check
```

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
