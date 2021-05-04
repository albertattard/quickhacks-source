---
layout: default
title: Gradle Wrapper
parent: Gradle
nav_order: 7
permalink: docs/gradle/gradlew/
---

# Gradle Wrapper

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

You can update, or downgrade, Gradle Wrapper version used

```console
$ ./gradlew wrapper --gradle-version 7.0
```

It is recommended to set the SHA256 signature if the Gradle Wrapper to mitigate against man in the middle attacks, and
verify that the correct version of Gradle Wrapper is used.

```console
$ ./gradlew wrapper --gradle-distribution-sha256-sum=eb8b89184261025b0430f5b2233701ff1377f96da1ef5e278af6ae8bac5cc305
```

A full list of SHA256 signatures is found [here](https://gradle.org/release-checksums/).

Kindly note that the checksum verification is only performed when the Gradle Wrapper distribution hasn't been downloaded
yet. The Gradle Wrapper distribution is not verified if this was already downloaded. The wrapper can be deleted as shown
next.

```console
$ rm -rf ~/.gradle/wrapper/dists/gradle-7.0-bin
```

It is also good to verify the Gradle Wrapper JAR file as this is an important part of the build. GitHub provides
an [action](https://github.com/marketplace/actions/gradle-wrapper-validation) for that already.
