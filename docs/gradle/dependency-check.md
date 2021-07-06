---
layout: default 
title: Dependency-Check (OWASP)
parent: Gradle 
nav_order: 4 
permalink: docs/gradle/dependency-check/
---

# Dependency-Check (OWASP)

The Open Web Application Security Project (OWASP) dependency check is an open source solution that verifies whether the
project is using any dependencies marked as vulnerable and fails the build depending on the threshold level. The Common
Vulnerability Scoring System (CVSS) is used where threats are scored between 0 and 10. The default threshold used is 11,
which means that by default the plugin will not fail. Lower score means that the plugins will fail for even less
threatening issues.

The property `failBuildOnCVSS` defines the limit at which the build should fail. If you like the build to fail for high
security threats or above, then set this to `7`.

- At `0.0` - No threats
- Between `0.1` and `3.9` - Low threats
- Between `4.0` and `9.9` - Medium threats
- Between `7.0` and `8.9` - High threats
- Between `9.0` and `10.0` - Critical threats

## References

- [https://jeremylong.github.io/DependencyCheck/dependency-check-gradle/index.html](https://jeremylong.github.io/DependencyCheck/dependency-check-gradle/index.html){:target="_blank"}
- [https://plugins.gradle.org/plugin/org.owasp.dependencycheck](https://plugins.gradle.org/plugin/org.owasp.dependencycheck){:target="_blank"}
- [https://en.wikipedia.org/wiki/Common_Vulnerability_Scoring_System](https://en.wikipedia.org/wiki/Common_Vulnerability_Scoring_System){:target="_blank"}
- [https://www.first.org/cvss/specification-document](https://www.first.org/cvss/specification-document**){:target="_blank"}
- [https://owasp.org/www-project-top-ten/](https://owasp.org/www-project-top-ten/){:target="_blank"}

## File: `build.gradle`

{% highlight gradle %} {% raw_include quickhacks/gradle/dependency-check/build.gradle %} {% endhighlight %}

## Run the dependency check

```console
$ ./gradlew dependencyCheckAnalyze
```

Given that we have an old dependency that has a critical security vulnerability, the build will fail.

```console
FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':dependencyCheckAnalyze'.
>

  Dependency-Analyze Failure:
  One or more dependencies were identified with vulnerabilities that have a CVSS score greater than '7,0': CVE-2017-5929
  See the dependency-check report for more details.
```

## Versions

- Gradle

  ```console
  $ ./gradlew --version

  Gradle 7.1.1
  ```

- Java

  ```console
  $ java --version
  openjdk 16 2021-03-16
  OpenJDK Runtime Environment AdoptOpenJDK (build 16+36)
  OpenJDK 64-Bit Server VM AdoptOpenJDK (build 16+36, mixed mode, sharing)
  ```
