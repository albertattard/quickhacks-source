---
layout: default
title: Error Prone
parent: Gradle
nav_order: 5
permalink: docs/gradle/error-prone/
---

# Error Prone

## References

- [https://plugins.gradle.org/plugin/net.ltgt.errorprone](https://plugins.gradle.org/plugin/net.ltgt.errorprone){:target="_blank"}
- [https://github.com/tbroyer/gradle-errorprone-plugin](https://github.com/tbroyer/gradle-errorprone-plugin){:target="_blank"}
- [https://plugins.jetbrains.com/plugin/7349-error-prone-compiler](https://plugins.jetbrains.com/plugin/7349-error-prone-compiler){:target="_blank"}
- [https://github.com/uber/NullAway](https://github.com/uber/NullAway){:target="_blank"}

## File: `build.gradle`

{% highlight gradle %}
{% raw_include quickhacks/gradle/error-prone/build.gradle %}
{% endhighlight %}

## File: `src/main/java/quickhack/Examples.java`

{% include custom/dose_not_compile.html details="The following example will fail to compile due to the error prone checks" %}

{% highlight java %}
{% raw_include quickhacks/gradle/error-prone/src/main/java/quickhack/Examples.java %}
{% endhighlight %}

## Run Error Prone checks

The build will fail as one of the failed checks has error level.

```console
$ ./gradlew compileJava

> Task :compileJava
/quickhacks/gradle/error-prone/src/main/java/quickhack/Examples.java:8: warning: [UnusedVariable] The local variable 'a' is never read.
        int a = 7;
            ^
    (see https://errorprone.info/bugpattern/UnusedVariable)
  Did you mean to remove this line?
/quickhacks/gradle/error-prone/src/main/java/quickhack/Examples.java:3: Note: [RemoveUnusedImports] Unused imports: java.time.LocalDateTime
import java.time.LocalDateTime;
^
    (see https://errorprone.info/bugpattern/RemoveUnusedImports)
  Did you mean to remove this line?
/quickhacks/gradle/error-prone/src/main/java/quickhack/Examples.java:17: error: [NullAway] returning @Nullable expression from method with @NonNull return type
        return null;
        ^
    (see http://t.uber.com/nullaway )
/quickhacks/gradle/error-prone/src/main/java/quickhack/Examples.java:24: warning: [AnnotateFormatMethod] This method passes a pair of parameters through to String.format, but the enclosing method wasn't annotated @FormatMethod. Doing so gives compile-time rather than run-time protection against malformed format strings.
    private static void logf(final String pattern, final Object... params) {
                        ^
    (see https://errorprone.info/bugpattern/AnnotateFormatMethod)
1 error
2 warnings

> Task :compileJava FAILED

FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':compileJava'.
> Compilation failed; see the compiler error output for details.

* Try:
Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output. Run with --scan to get full insights.

* Get more help at https://help.gradle.org

BUILD FAILED in 3s
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

1. Java

   ```console
   $ java --version
   openjdk 16 2021-03-16
   OpenJDK Runtime Environment AdoptOpenJDK (build 16+36)
   OpenJDK 64-Bit Server VM AdoptOpenJDK (build 16+36, mixed mode, sharing)
   ```
