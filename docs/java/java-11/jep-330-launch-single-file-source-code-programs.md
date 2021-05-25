---
layout: default
title: "JEP 330: Launch Single-File Source-Code Programs"
parent: Java 11
grand_parent: Java
nav_order: 10.330
permalink: docs/java/java-11/jep-330-launch-single-file-source-code-programs/
---

# JEP 330: Launch Single-File Source-Code Programs

Java 11 introduced Java Single File Execution ([JEP-330](https://openjdk.java.net/jeps/330){:target="_blank"}) which
enhanced the java launcher to run a program supplied as a single file of Java source code, including usage from within a
script by means of ["shebang" files](https://openjdk.java.net/jeps/330#Shebang_files){:target="_blank"} and related
techniques.

## File: `hello`

{% highlight java %} 
{% raw_include quickhacks/java/java-11/jep-330-launch-single-file-source-code-programs/hello %}
{% endhighlight %}

## Run the script

```console
$ ./hello

Hello World!!
```

The script needs to be executable, otherwise you will get an error such as

```console
zsh: permission denied: ./hello
```

Make the script executable

```console
$ chmod +x hello
```

## Versions

1. Java

   ```console
   $ java --version
   openjdk 11.0.10 2021-01-19
   OpenJDK Runtime Environment AdoptOpenJDK (build 11.0.10+9)
   OpenJDK 64-Bit Server VM AdoptOpenJDK (build 11.0.10+9, mixed mode)
   ```
