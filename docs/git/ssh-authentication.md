---
layout: default
title: SSH Authentication
parent: Git
nav_order: 19
permalink: docs/git/ssh-authentication/
---

# SSH Authentication

## Setup

1. Create SSH

   Pick the key type supported by your remote repository. Not all repositories support all types of keys. Put your email
   address as a comment to the key,
   as [recommended by GitHub](https://docs.github.com/en/github/authenticating-to-github/connecting-to-github-with-ssh/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent)
   .

   - Use the Edwards-curve Digital Signature Algorithm (Ed25519 is the EdDSA signature scheme using SHA-512 (SHA-2) and
     Curve25519). This is only supported by newer repositories.

     ```console
     $ ssh-keygen -t ed25519 -C "albertattard@gmail.com"
     ```

   - Use the older RSA Algorithm, supported by most repositories

     ```console
     $ ssh-keygen -t rsa -C "albertattard@gmail.com"
     ```

   **Do not use one SSH key for all repositories**.  Instead, create one for every environment, such as

   - Personal things
   - Profession/Work development environment
   - Profession/Work production environment

   You can do that by creating as many SSH key-pairs as needed.

   Secure your private key with a password.

   Following is an example

   ```console
   $ ssh-keygen -t rsa -C "albertattard@gmail.com"
   Generating public/private rsa key pair.
   Enter file in which to save the key (/Users/albertattard/.ssh/id_rsa): /Users/albertattard/.ssh/id_rsa_dev
   Enter passphrase (empty for no passphrase):
   Enter same passphrase again:
   Your identification has been saved in /Users/albertattard/.ssh/id_rsa_dev.
   Your public key has been saved in /Users/albertattard/.ssh/id_rsa_dev.pub.
   The key fingerprint is:
   SHA256:Fvh13Ra2aOJuehYpEu2sec7RzQzc5MckQMe2ZuL1nqw albertattard@gmail.com
   The key's randomart image is:
   +---[RSA 3072]----+
   |          .o.. o |
   |       .    o+o.o|
   |      . o ..o=ooo|
   |       o +oo*=+. |
   |        S .+*o.o |
   |       o +o+= .. |
   |        +..+.+o .|
   |       o..+o   + |
   |        o+o  E.  |
   +----[SHA256]-----+
   ```

1. Upload the public key to the remote repository.

   Copy Public Key to the clipboard

   ```console
   $ pbcopy < ~/.ssh/id_rsa_dev.pub
   ```

   Add the SSH Key to the repo

1. Configure SSH

   Edit the `~/.ssh/config` file and add an entry so that GIT can use SSH to authenticate

   ```console
   $ vi ~/.ssh/config
   ```

   Add the following

   ```console
   Host dev-project
     AddKeysToAgent yes
     UseKeychain yes
     User git
     Hostname github.com
     IdentityFile ~/.ssh/id_rsa_dev
     IdentitiesOnly yes
   ```

   Using the above configuration, the path `dev-project:albertattard/quickhacks-source.git` is equivalent to the 
   following: `git@github.com:albertattard/quickhacks-source.git`, and the private key file `~/.ssh/id_rsa_dev`.

1. Access the repository

   ```console
   $ git clone dev-project:albertattard/quickhacks-source.git
   ```

## Versions

1. Git

   ```console
   $ git --version
   git version 2.30.1 (Apple Git-130)
   ```

1. SSH

   ```console
   $ ssh -V
   OpenSSH_8.1p1, LibreSSL 2.7.3
   ```
