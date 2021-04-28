#!/bin/bash

export AWS_PROFILE="quickhacks"
terraform init
terraform fmt -recursive
terraform apply
