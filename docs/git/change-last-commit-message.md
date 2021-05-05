---
layout: default
title: Change Last Commit Message
parent: Git
nav_order: 3
permalink: docs/git/change-last-commit-message/
---

# Change Last Commit Message

## References

- [https://git-scm.com/docs/git-commit#Documentation/git-commit.txt--mltmsggt](https://git-scm.com/docs/git-commit#Documentation/git-commit.txt--mltmsggt){:target="_blank"}
- [https://linuxize.com/post/change-git-commit-message/](https://linuxize.com/post/change-git-commit-message/){:target="_blank"}

## Git Command

The `-m <msg>`, or `--message=<msg>`, options allow to replace the existing message.

- `-m <msg>` option

  ```console
  $ git commit --amend -m "the new commit message"
  ```

- `--message=<msg>` option

  ```console
  $ git commit --amend --message="the new commit message"
  ```

## Versions

1. Git

    ```console
    $ git --version
    git version 2.30.1 (Apple Git-130)
    ```
