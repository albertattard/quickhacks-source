#!/bin/bash

export AWS_PROFILE="quickhacks"
terraform -chdir=terraform init
terraform -chdir=terraform fmt -recursive
terraform -chdir=terraform apply
