---
layout: default
title: "JEP 181: Nest-Based Access Control"
parent: Java 11
grand_parent: Java
nav_order: 10.181
permalink: docs/java/java-11/jep-181-nest-based-access-control/
---

# JEP 181: Nest-Based Access Control

Java 11 introduced Nest-Based Access Control ([JEP-181](https://openjdk.java.net/jeps/181){:target="_blank"}), an
access-control context that aligns with the existing notion of nested types in the Java programming language. Nests
allow classes that are logically part of the same code entity (source file), but which are compiled to distinct class
files, to access each other's private members without the need for compilers to insert accessibility-broadening bridge
methods.

---

## File: `src/main/java/quickhack/Main.java`

{% highlight java %}
{% raw_include quickhacks/java/java-11/jep-181-nest-based-access-control/src/main/java/quickhack/Main.java %}
{% endhighlight %}

## Compile and run with Java 8

Java 9 and 10 were not available on sdkman at the time of writing and had to use Java 8. Please refer to
the [Setup environment (SDKMAN)]({{ "/docs/java/sdkman/" | absolute_url }}) for more information about how to set up
multiple versions of java and set aliases to quickly switch between different versions.

```console
$ java8 && ./gradlew clean run
```

The program fails with an `IllegalAccessException`.

```console
Java version is: 1.8.0_292
The field value is: Accessing the field directly
Exception in thread "main" java.lang.IllegalAccessException: Class quickhack.Main can not access a member of class quickhack.Main$NestedClass with modifiers "private"
        at sun.reflect.Reflection.ensureMemberAccess(Reflection.java:102)
        at java.lang.reflect.AccessibleObject.slowCheckMemberAccess(AccessibleObject.java:296)
        at java.lang.reflect.AccessibleObject.checkAccess(AccessibleObject.java:288)
        at java.lang.reflect.Field.set(Field.java:761)
        at quickhack.Main.main(Main.java:19)
```

## Compile and run with Java 11

```console
$ java11 && ./gradlew clean run
```

The same code works with Java 11

```console
Java version is: 11.0.10
The field value is: Accessing the field directly
The field value is: Accessing the field using reflection
```

## How does it work when accessed directly?

The Java compilers (before Java 11) create an inner anonymous class, `Main$1.class`, that enables access to the nested
class's private fields.

```console
$ java8 && ./gradlew clean compileJava
$ ls -la build/classes/java/main/quickhack
total 24
drwxr-xr-x  5 albertattard  staff   160 May  6 12:34 .
drwxr-xr-x  3 albertattard  staff    96 May  6 12:34 ..
-rw-r--r--  1 albertattard  staff   180 May  6 12:34 Main$1.class
-rw-r--r--  1 albertattard  staff   841 May  6 12:34 Main$NestedClass.class
-rw-r--r--  1 albertattard  staff  1826 May  6 12:34 Main.class
```

This is not required since Java 11

```console
$ java11 && ./gradlew clean compileJava
$ ls -la build/classes/java/main/quickhack
total 24
drwxr-xr-x  5 albertattard  staff   160 May  6 12:34 .
drwxr-xr-x  3 albertattard  staff    96 May  6 12:34 ..
-rw-r--r--  1 albertattard  staff   841 May  6 12:34 Main$NestedClass.class
-rw-r--r--  1 albertattard  staff  1826 May  6 12:34 Main.class
```

Following is the bytecode of the respective builds.

- Java 8 Bytecode

  ```console
  $ javap -c build/classes/java/main/quickhack/Main.class
  
  Compiled from "Main.java"
  public class quickhack.Main {
  public quickhack.Main();
  Code:
  0: aload_0
  1: invokespecial #1                  // Method java/lang/Object."<init>":()V
  4: return
  
  public static void main(java.lang.String[]) throws java.lang.NoSuchFieldException, java.lang.IllegalAccessException;
  Code:
  0: invokestatic  #2                  // Method printJavaVersion:()V
  3: new           #3                  // class quickhack/Main$NestedClass
  6: dup
  7: aconst_null
  8: invokespecial #4                  // Method quickhack/Main$NestedClass."<init>":(Lquickhack/Main$1;)V
  11: astore_1
  12: aload_1
  13: ldc           #5                  // String Accessing the field directly
  15: invokestatic  #6                  // Method quickhack/Main$NestedClass.access$102:(Lquickhack/Main$NestedClass;Ljava/lang/String;)Ljava/lang/String;
  18: pop
  19: aload_1
  20: invokestatic  #7                  // Method printFieldValue:(Lquickhack/Main$NestedClass;)V
  23: ldc           #3                  // class quickhack/Main$NestedClass
  25: ldc           #8                  // String privateField
  27: invokevirtual #9                  // Method java/lang/Class.getDeclaredField:(Ljava/lang/String;)Ljava/lang/reflect/Field;
  30: astore_2
  31: aload_2
  32: aload_1
  33: ldc           #10                 // String Accessing the field using reflection
  35: invokevirtual #11                 // Method java/lang/reflect/Field.set:(Ljava/lang/Object;Ljava/lang/Object;)V
  38: aload_1
  39: invokestatic  #7                  // Method printFieldValue:(Lquickhack/Main$NestedClass;)V
  42: return
  }
  ```

- Java 11 Bytecode

  ```console
  $ javap -c build/classes/java/main/quickhack/Main.class
  
  Compiled from "Main.java"
  public class quickhack.Main {
    public quickhack.Main();
      Code:
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
  
    public static void main(java.lang.String[]) throws java.lang.NoSuchFieldException, java.lang.IllegalAccessException;
      Code:
         0: invokestatic  #2                  // Method printJavaVersion:()V
         3: new           #3                  // class quickhack/Main$NestedClass
         6: dup
         7: invokespecial #4                  // Method quickhack/Main$NestedClass."<init>":()V
        10: astore_1
        11: aload_1
        12: ldc           #5                  // String Accessing the field directly
        14: putfield      #6                  // Field quickhack/Main$NestedClass.privateField:Ljava/lang/String;
        17: aload_1
        18: invokestatic  #7                  // Method printFieldValue:(Lquickhack/Main$NestedClass;)V
        21: ldc           #3                  // class quickhack/Main$NestedClass
        23: ldc           #8                  // String privateField
        25: invokevirtual #9                  // Method java/lang/Class.getDeclaredField:(Ljava/lang/String;)Ljava/lang/reflect/Field;
        28: astore_2
        29: aload_2
        30: aload_1
        31: ldc           #10                 // String Accessing the field using reflection
        33: invokevirtual #11                 // Method java/lang/reflect/Field.set:(Ljava/lang/Object;Ljava/lang/Object;)V
        36: aload_1
        37: invokestatic  #7                  // Method printFieldValue:(Lquickhack/Main$NestedClass;)V
        40: return
  }
  ```

## Versions

- Java 8

  ```console
  $ java -version
  openjdk version "1.8.0_292"
  OpenJDK Runtime Environment (AdoptOpenJDK)(build 1.8.0_292-b10)
  OpenJDK 64-Bit Server VM (AdoptOpenJDK)(build 25.292-b10, mixed mode)
  ```

- Java 11

  ```console
  $ java --version
  openjdk 11.0.10 2021-01-19
  OpenJDK Runtime Environment AdoptOpenJDK (build 11.0.10+9)
  OpenJDK 64-Bit Server VM AdoptOpenJDK (build 11.0.10+9, mixed mode)
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
