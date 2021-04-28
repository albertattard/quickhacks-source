#!/bin/bash

export AWS_PROFILE="quickhacks"

aws ec2 create-key-pair --key-name "Quickhacks SSH Key" --region "eu-central-1" | jq -r ".KeyMaterial" > "./quickhacks.pem"
chmod 400 quickhacks.pem

terraform init
terraform fmt -recursive
terraform apply
