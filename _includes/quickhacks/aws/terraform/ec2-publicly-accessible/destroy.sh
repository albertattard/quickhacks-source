#!/bin/bash

export AWS_PROFILE="quickhacks"

terraform destroy

aws ec2 delete-key-pair --key-name "Quickhacks SSH Key"
rm -rf quickhacks.pem
