---
layout: default
title: Undo Commits both Locally and Remote
parent: Git
nav_order: 21
permalink: docs/git/undo-commits-both-locally-and-remote/
---

# Undo Commits both Locally and Remote

## References

- [https://git-scm.com/docs/git-revert#Documentation/git-revert.txt---no-commit](https://git-scm.com/docs/git-revert#Documentation/git-revert.txt---no-commit){:target="_blank"}
- [https://git-scm.com/docs/git-log#Documentation/git-log.txt---oneline](https://git-scm.com/docs/git-log#Documentation/git-log.txt---oneline){:target="_blank"}

## Git Commands

- Revert the last 2 commits

  There are two ways about this.

  - Using range

    ```console
    $ git revert --no-commit HEAD~2..
    ```

    The `..` represents a range, in this case between `HEAD~2` and `HEAD`, which is equivalent to `HEAD~2..HEAD`.
  
  - Specify each commit  
    
    Obtain the commit ids using the `log`.

    ```console
    $ git log --oneline
    ```

    Then revert these commit in order, starting from the last commit

    ```console
    $ git revert --no-commit <last-commit-id>
    $ git revert --no-commit <second-last-commit-id>
    ```

  The `-n`, or `--no-commit`, options will revert the changes without applying a commit.

- Commit reverts

  ```console
  $ git commit -S -m "The reason why the revert was applied"
  ```

- Push changes to the remote

  ```console
  $ git push
  ```

## Versions

- Git

  ```console
  $ git --version
  git version 2.30.1 (Apple Git-130)
  ```
