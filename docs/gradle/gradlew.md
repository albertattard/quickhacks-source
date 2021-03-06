---
layout: default
title: Gradle Wrapper
parent: Gradle
nav_order: 7
permalink: docs/gradle/gradlew/
---

# Gradle Wrapper

## References

- [https://www.youtube.com/watch?v=zAR3Ahr8VnA](https://www.youtube.com/watch?v=zAR3Ahr8VnA)

## Blank Projects

A [blank project containing just the gradle wrapper](https://github.com/albertattard/blank-gradle-project).

- SSH

  ```console
  $ git clone git@github.com:albertattard/blank-gradle-project.git
  ```

- HTTPS

  ```console
  $ git clone https://github.com/albertattard/blank-gradle-project.git
  ```

## Version

```console
$ ./gradlew --version
```

## Set Version

You can update, or downgrade, Gradle Wrapper version used.

```console
$ ./gradlew wrapper --gradle-version 7.1.1
```

A list of Gradle releases can be found [here](https://gradle.org/releases/).

It is recommended to set the SHA256 signature if the Gradle Wrapper to mitigate against man in the middle attacks, and
verify that the correct version of Gradle Wrapper is used.

```console
$ ./gradlew wrapper --gradle-distribution-sha256-sum=bf8b869948901d422e9bb7d1fa61da6a6e19411baa7ad6ee929073df85d6365d
```

A full list of SHA256 signatures is found [here](https://gradle.org/release-checksums/).

Kindly note that the checksum verification is only performed when the Gradle Wrapper distribution hasn't been downloaded
yet. The Gradle Wrapper distribution is not verified if this was already downloaded. The wrapper can be deleted as shown
next.

```console
$ rm -rf ~/.gradle/wrapper/dists/gradle-7.1.1-bin
```

It is also good to verify the Gradle Wrapper JAR file as this is an important part of the build. GitHub provides
an [action](https://github.com/marketplace/actions/gradle-wrapper-validation) for that already.
