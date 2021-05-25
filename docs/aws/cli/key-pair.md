---
layout: default
title: Key Pair (SSH Key)
parent: CLI (AWS)
grand_parent: AWS
nav_order: 11
permalink: docs/aws/cli/key-pair/
---

# Key Pair (SSH Key)

## Create Key Pair

The following make use of [jq](https://stedolan.github.io/jq/){:target="_blank"}, which can be installed using 
[brew](https://formulae.brew.sh/formula/jq){:target="_blank"}.

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

Delete the private key file.

```console
$ rm quickhacks.pem
```

## Versions

1. AWS CLI

   ```console
   $ aws --version
   aws-cli/2.1.30 Python/3.9.2 Darwin/20.3.0 source/x86_64 prompt/off
   ```

1. jq

   ```console
   $ jq --version
   jq-1.6
   ```
