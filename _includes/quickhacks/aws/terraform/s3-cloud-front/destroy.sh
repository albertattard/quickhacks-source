#!/bin/bash

export AWS_PROFILE="quickhacks"
terraform -chdir=terraform destroy
