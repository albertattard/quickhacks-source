---
layout: default
title: Ignore Uncommitted Changes to File
parent: Git
nav_order: 9
permalink: docs/git/ignore-uncommitted-changes-to-file/
---

# Ignore Uncommitted Changes to File

## Git Commands

- Save all changes (tracked or not) for later use and sets your working area to your last commit

  ```console
  $ git stash
  ```

- Replace a given file with the last committed copy

  ```console
  $ git checkout <file-path>
  ```

- Permanently discard all local changes and move back to the last commit

  ```console
  $ git reset --hard HEAD
  ```

  Note that untracked files are not effected by this command, and you need to delete them manually

## Versions

1. Git

   ```console
   $ git --version
   git version 2.30.1 (Apple Git-130)
   ```
