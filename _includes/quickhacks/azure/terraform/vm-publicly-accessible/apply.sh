#!/bin/bash

terraform init
terraform fmt -recursive
terraform apply
