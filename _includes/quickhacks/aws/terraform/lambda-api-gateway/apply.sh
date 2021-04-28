#!/bin/bash

zip lambda.zip exports.js

export AWS_PROFILE="quickhacks"
terraform init
terraform fmt -recursive
terraform apply
