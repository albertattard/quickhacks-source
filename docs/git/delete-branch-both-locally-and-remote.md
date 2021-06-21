---
layout: default
title: Delete Branch both Locally and Remote
parent: Git
nav_order: 4
permalink: docs/git/delete-branch-both-locally-and-remote/
---

# Delete Branch both Locally and Remote

## References

- [git fetch -p](https://git-scm.com/docs/git-fetch#Documentation/git-fetch.txt--p)
- [git branch -a](https://git-scm.com/docs/git-branch#Documentation/git-branch.txt--a)
- [git branch -d](https://git-scm.com/docs/git-branch#Documentation/git-branch.txt--d)
- [git push origin -d](https://git-scm.com/docs/git-push#Documentation/git-push.txt--d)

## Git Command

- Before fetching, remove any remote-tracking references that no longer exist on the remote

  ```console
  $ git fetch -p
  ```

- List both remote-tracking branches and local branches

  ```console
  $ git branch -a
  ```

- Delete a branch that is fully merged in its upstream branch, or in HEAD if no upstream was set

  ```console
  $ git branch -d <branch-name>
  ```

  Alternatively, force the deletion

  ```console
  $ git branch -D <branch-name>
  ```

- Delete a branch from the remote repository

  ```console
  $ git push origin -d <branch-name>
  ```

## Versions

1. Git

    ```console
    $ git --version
    git version 2.30.1 (Apple Git-130)
    ```
