---
layout: default
title: Key Pair (SSH Key)
parent: CLI (AWS)
nav_order: 1
permalink: docs/aws/cli/key-pair/
---

# Key Pair (SSH Key)

---

## Create Key Pair

The following make use of [jq](https://stedolan.github.io/jq/), which can be installed using 
[brew](https://formulae.brew.sh/formula/jq).

```console
$ AWS_PROFILE="quickhacks" aws ec2 create-key-pair --key-name "Quickhacks SSH Key" --region eu-central-1 | jq -r ".KeyMaterial" > ./quickhacks.pem
```

Restrict the permissions of the key as otherwise it cannot be used.

```console
$ chmod 400 quickhacks.pem
```

## Delete Key Pair

```console
$ AWS_PROFILE="quickhacks" aws ec2 delete-key-pair --key-name "Quickhacks SSH Key"
```

## Versions

1. AWS CLI

   ```console
   $ aws --version
   aws-cli/2.1.30 Python/3.9.2 Darwin/20.3.0 source/x86_64 prompt/off
   ```
