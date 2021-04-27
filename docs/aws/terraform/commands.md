---
layout: default
title: Terraform Commands
parent: Terraform
grand_parent: AWS
nav_order: 1
permalink: docs/aws/terraform/commands/
---

# Terraform Commands

1. Initialise terraform

   ```bash
   $ AWS_PROFILE="quickhacks" terraform init
   ```

   Or, use the `-chdir` argument to run the command from a different directory

   ```bash
   $ AWS_PROFILE="quickhacks" terraform -chdir=terraform init
   ```

1. Format code (recursive)

   ```bash
   $ terraform fmt -recursive
   ```

   Or, use the `-chdir` argument to run the command from a different directory

   ```bash
   $ terraform -chdir=terraform fmt -recursive
   ```

1. Plan the changes

   Without saving the plan.

   ```bash
   $ AWS_PROFILE="quickhacks" terraform plan
   ```

   Saving the plan so that it can be executed later on.

   ```bash
   $ AWS_PROFILE="quickhacks" terraform plan -out quickhacks.plan
   ```

1. Apply the changes

   ```bash
   $ AWS_PROFILE="quickhacks" terraform apply
   ```

   Or, use the `-chdir` argument to run the command from a different directory

   ```bash
   $ AWS_PROFILE="quickhacks" terraform -chdir=terraform apply
   ```

1. Destroy the resources

   ```bash
   $ AWS_PROFILE="quickhacks" terraform destroy
   ```

   Or, use the `-chdir` argument to run the command from a different directory

   ```bash
   $ AWS_PROFILE="quickhacks" terraform -chdir=terraform destroy
   ```
