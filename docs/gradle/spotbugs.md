---
layout: default
title: Spotbugs
parent: Gradle
nav_order: 19
permalink: docs/gradle/spotbugs/
---

# Spotbugs

{% include custom/pending.html details="I am still working on this one.  I am still trying to figure out how to get this working well" %}

I had better luck with [error prone]({{ "/docs/gradle/error-prone/" | absolute_url }}) so far, especially with
the `NullPointerException` detection.

## References

- [https://plugins.gradle.org/plugin/com.github.spotbugs](https://plugins.gradle.org/plugin/com.github.spotbugs){:target="_blank"}
- [https://spotbugs.readthedocs.io/en/latest/gradle.html](https://spotbugs.readthedocs.io/en/latest/gradle.html){:target="_blank"}
- [https://github.com/spotbugs/spotbugs/releases](https://github.com/spotbugs/spotbugs/releases){:target="_blank"}

## File: `build.gradle`

{% highlight gradle %}
{% raw_include quickhacks/gradle/spotbugs/build.gradle %}
{% endhighlight %}

## File: `src/main/java/quickhacks/Examples.java`

{% highlight java %}
{% raw_include quickhacks/gradle/spotbugs/src/main/java/quickhacks/Examples.java %}
{% endhighlight %}

## Run Spotbugs checks

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
