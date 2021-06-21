---
layout: default
title: Merge Branch Into Main
parent: Git
nav_order: 13
permalink: docs/git/merge-branch-into-main/
---

# Merge Branch Into Main

## References

- [git checkout -b](https://git-scm.com/docs/git-checkout#Documentation/git-checkout.txt--bltnewbranchgt)
- [git add -A](https://git-scm.com/docs/git-add#Documentation/git-add.txt--A)
- [git commit -m](https://git-scm.com/docs/git-branch#Documentation/git-branch.txt--a)
- [git checkout](https://git-scm.com/docs/git-checkout)
- [git merge](https://git-scm.com/docs/git-merge)

## Git Command

- Create a new branch

  ```console
  $ git checkout -b <branch-name>
  ```

- Add all files (except the ignored ones) and commit

  ```console
  $ git add -A
  $ git commit -m "<commit-message>"
  ```

- Go back to the `main` branch

  ```console
  $ git checkout main
  ```

- Merge branch into `main`

  ```console
  $ git merge <branch-name>
  ```

## Versions

1. Git

    ```console
    $ git --version
    git version 2.30.1 (Apple Git-130)
    ```
