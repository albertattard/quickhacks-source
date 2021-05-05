---
layout: default
title: Setup environment (SDKMAN)
parent: Java
nav_order: 19
permalink: docs/java/sdkman/
---

# Setup environment (SDKMAN)

[SDKMAN](https://sdkman.io/){:target="_blank"} is a command line tool that allows us to install different versions of
Java, Gradle, Maven and more. SDKMAN also takes care of setting environment variables for you. Installing SDKMAN.

1. Install

   ```console
   $ curl -s "https://get.sdkman.io" | bash
   $ source "~/.sdkman/bin/sdkman-init.sh"
   ```

1. Check version after installation

   ```console
   $ sdk version
   SDKMAN SDKMAN 5.11.0+644
   ```

   Update SDKMAN if an older version is found

   ```console
   $ sdk update
   ```

1. List all available Java versions

   ```console
   $ sdk list java
   ```

   This will print all versions available to SDKMAN from different [vendors](https://sdkman.io/jdks){:target="_blank"}.

   ```console
   ================================================================================
   Available Java Versions
   ================================================================================
    Vendor        | Use | Version      | Dist    | Status     | Identifier
   --------------------------------------------------------------------------------
    AdoptOpenJDK  |     | 16.0.0.j9    | adpt    |            | 16.0.0.j9-adpt
                  |     | 16.0.0.hs    | adpt    |            | 16.0.0.hs-adpt
                  |     | 15.0.2.hs    | adpt    | local only | 15.0.2.hs-adpt
                  |     | 11.0.11.j9   | adpt    |            | 11.0.11.j9-adpt
                  |     | 11.0.11.hs   | adpt    |            | 11.0.11.hs-adpt
                  |     | 11.0.10.j9   | adpt    |            | 11.0.10.j9-adpt
                  | >>> | 11.0.10.hs   | adpt    | installed  | 11.0.10.hs-adpt
                  |     | 8.0.292.j9   | adpt    |            | 8.0.292.j9-adpt
                  |     | 8.0.292.hs   | adpt    |            | 8.0.292.hs-adpt
                  |     | 8.0.282.j9   | adpt    |            | 8.0.282.j9-adpt
                  |     | 8.0.282.hs   | adpt    |            | 8.0.282.hs-adpt
   ...
   ```

   The above shows two versions of Java installed

   1. `15.0.2.hs-adpt`
   1. `11.0.10.hs-adpt`

   The version `11.0.10.hs-adpt` is not available anymore as there is a newer version available, the
   version `11.0.11.hs-adpt`. That's why it has the status of `local only`.

   The version `11.0.10.hs-adpt` is the current default version of Java.

1. Install the latest Java 11 and Java 16

   ```console
   $ sdk install java 11.0.11.hs-adpt
   $ sdk install java 16.0.0.hs-adpt
   ```

   OpenJDK comes in two flavours:

   1. **HotSpot** (`...x.hs-adpt`) is the VM from the OpenJDK community. It is the most widely used VM today and is used
      in Oracle's JDK ([reference](https://openjdk.java.net/groups/hotspot/){:target="_blank"}).
   1. **Eclipse OpenJ9** (`...x.j9-adpt`) is the VM from the Eclipse community. It is an enterprise-grade VM designed
      for low memory footprint and fast start-up and is used in IBM's
      JDK ([reference](https://www.eclipse.org/openj9/){:target="_blank"}).

1. Change the default Java

   ```console
   $ sdk default java 11.0.11.hs-adpt
   Default java version set 11.0.11.hs-adpt
   ```

   Verify the version

   ```console
   $ java -version
   openjdk version "11.0.10" 2021-01-19
   OpenJDK Runtime Environment AdoptOpenJDK (build 11.0.10+9)
   OpenJDK 64-Bit Server VM AdoptOpenJDK (build 11.0.10+9, mixed mode)
   ```

   **Optionally**, add an alias to simplify switching from one version to another.

   ```console
   $ vi ~/.oh-my-zsh/custom/dev.zsh
   ```

   Add the aliases

   ```console
   source "$HOME/.sdkman/bin/sdkman-init.sh"
   alias java11='sdk default java 11.0.10.hs-adpt'
   alias java15='sdk default java 15.0.2.hs-adpt'
   ```

   Note that the aliases will need to be updated when different versions of Java are added or removed. I have Java 11
   and Java 15 installed.

   To switch between versions just use `java11` and `java15`.

   ```console
   $ java11
   Default java version set to 11.0.10.hs-adpt
   ```

1. Set the `JAVA_HOME` environment variable

   ```console
   $ echo $JAVA_HOME
   /Users/albertattard/.sdkman/candidates/java/current
   ```

   If the environment variable is missing, it will be blank. Edit the `~/.zshrc` to set the `JAVA_HOME` environment
   variable.

   ```console
   $ vi ~/.zshrc
   ```

   Add the `JAVA_HOME` environment variable

   ```console
   export JAVA_HOME="~/.sdkman/candidates/java/current"
   ```

   Open a new terminal and verify that this was properly set.

   ```console
   $ ${JAVA_HOME}/bin/java -version
   openjdk version "11.0.10" 2021-01-19
   OpenJDK Runtime Environment AdoptOpenJDK (build 11.0.10+9)
   OpenJDK 64-Bit Server VM AdoptOpenJDK (build 11.0.10+9, mixed mode)
   ```

For more details, please refer to: [https://sdkman.io/install](https://sdkman.io/install){:target="_blank"}
