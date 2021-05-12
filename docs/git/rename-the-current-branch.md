---
layout: default
title: Rename the Current Branch
parent: Git
nav_order: 18
permalink: docs/git/rename-the-current-branch/
---

# Rename the Current Branch

## Reference

- [https://git-scm.com/docs/git-branch#Documentation/git-branch.txt--m](https://git-scm.com/docs/git-branch#Documentation/git-branch.txt--m)
- [https://git-scm.com/docs/git-checkout#Documentation/git-checkout.txt--bltnewbranchgt](https://git-scm.com/docs/git-checkout#Documentation/git-checkout.txt--bltnewbranchgt)

## Git Command

Following are examples of how to rename a branch to `<new-branch-name>`.

- Commits already exist

  ```console
  $ git branch -M <new-branch-name>
  ```

  `-M` is a shortcut for `-m -f` (or the longer form `--move --force`).

  Rename the branch to `main`

  ```console
  $ git branch -M main
  ```

- No commits exist

  ```console
  $ git checkout -b <new-branch-name>
  ```

  Rename the branch to `main`

  ```console
  $ git checkout -b main
  Switched to a new branch 'main'
  ```

In both cases, the previous branch is renamed to `<new-branch-name>`.

## Versions

1. Git

    ```console
    $ git --version
    git version 2.30.1 (Apple Git-130)
    ```
