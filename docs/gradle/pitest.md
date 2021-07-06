---
layout: default
title: PIT Mutation Testing (pitest)
parent: Gradle
nav_order: 19
permalink: docs/gradle/pitest/
---

# PIT Mutation Testing (pitest)

PIT is a state of the art mutation testing system, providing gold standard test coverage for Java and the jvm. It's
fast, scalable and integrates with modern test and build tooling.

## References

- [https://pitest.org/](https://pitest.org/){:target="_blank"}
- [https://gradle-pitest-plugin.solidsoft.info/](https://gradle-pitest-plugin.solidsoft.info/){:target="_blank"}
- [https://github.com/hcoles/pitest/releases](https://github.com/hcoles/pitest/releases){:target="_blank"}

## File: `build.gradle`

{% highlight gradle %}
{% raw_include quickhacks/gradle/pitest/build.gradle %}
{% endhighlight %}

## File: `src/main/java/quickhacks/Calculator.java`

{% highlight java %}
{% raw_include quickhacks/gradle/pitest/src/main/java/quickhacks/Calculator.java %}
{% endhighlight %}

## File: `src/test/java/quickhacks/CalculatorTest.java`

{% highlight java %}
{% raw_include quickhacks/gradle/pitest/src/test/java/quickhacks/CalculatorTest.java %}
{% endhighlight %}

## Run Mutation testing

```console
$ ./gradlew pitest
```

Both our mutation score and line coverage are below the threshold and thus the `pitest` will fail.

![Pitest Report]({{ "/assets/images/gradle/pitest-report.png" | absolute_url }})

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
