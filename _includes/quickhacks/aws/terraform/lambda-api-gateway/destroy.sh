#!/bin/bash

export AWS_PROFILE="quickhacks"
terraform destroy

rm -rf lambda.zip