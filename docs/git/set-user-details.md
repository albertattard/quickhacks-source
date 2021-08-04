---
layout: default
title: Set User Details
parent: Git
nav_order: 19.1
permalink: docs/git/set-user-details/
---

# Set User Details

## References

- [https://git-scm.com/docs/git-config#Documentation/git-config.txt-username](https://git-scm.com/docs/git-config#Documentation/git-config.txt-username){:target="_blank"}

## Change user details

- Change the user details for repository

  ```console
  $ git config user.name "Albert Attard"
  $ git config user.email albertattard@gmail.com
  ```

- List repository configuration

  ```console
  $ git config --local --list
  ```

## Versions

1. Git

    ```console
    $ git --version
    git version 2.30.1 (Apple Git-130)
    ```
