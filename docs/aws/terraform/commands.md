---
layout: default
title: Terraform Commands
parent: Terraform (AWS)
grand_parent: AWS
nav_order: 3
permalink: docs/aws/terraform/commands/
---

# Terraform Commands

1. Initialise terraform

   ```console
   $ AWS_PROFILE="quickhacks" terraform init
   ```

   Or, use the `-chdir` argument to run the command from a different directory

   ```console
   $ AWS_PROFILE="quickhacks" terraform -chdir=terraform init
   ```

1. Format code (recursive)

   ```console
   $ terraform fmt -recursive
   ```

   Or, use the `-chdir` argument to run the command from a different directory

   ```console
   $ terraform -chdir=terraform fmt -recursive
   ```

1. Plan the changes

   Without saving the plan.

   ```console
   $ AWS_PROFILE="quickhacks" terraform plan
   ```

   Saving the plan so that it can be executed later on.

   ```console
   $ AWS_PROFILE="quickhacks" terraform plan -out quickhacks.plan
   ```

1. Apply the changes

   ```console
   $ AWS_PROFILE="quickhacks" terraform apply
   ```

   Or, use the `-chdir` argument to run the command from a different directory

   ```console
   $ AWS_PROFILE="quickhacks" terraform -chdir=terraform apply
   ```

1. Output values

   ```console
   $ AWS_PROFILE="quickhacks" terraform output
   ```

   Or, use the `-chdir` argument to run the command from a different directory

   ```console
   $ AWS_PROFILE="quickhacks" terraform -chdir=terraform output
   ```

   Change the format to JSON

   ```console
   $ AWS_PROFILE="quickhacks" terraform output -json
   ```

1. Destroy the resources

   ```console
   $ AWS_PROFILE="quickhacks" terraform destroy
   ```

   Or, use the `-chdir` argument to run the command from a different directory

   ```console
   $ AWS_PROFILE="quickhacks" terraform -chdir=terraform destroy
   ```

## Versions

1. Terraform

    ```console
    $ terraform --version
    Terraform v0.15.0
    ```
