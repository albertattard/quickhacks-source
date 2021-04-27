---
layout: default
title: DynamoDB
parent: Terraform
grand_parent: AWS
nav_order: 2
permalink: docs/aws/terraform/dynamodb.md/
---

# DynamoDB

---

## Terraform

Terraform files: [dynamodb.md]({{ "/assets/quickhacks/aws/terraform/dynamodb.md" | absolute_url }}).

### File: `terraform.tf`

The bucket `quickhacks-terraform` needs to be created beforehand. Refer to
[create S3 bucket]({{ "/docs/aws/cli/s3" | absolute_url }}#create-bucket) for more information about that.

```terraform
terraform {
  backend "s3" {
    bucket = "quickhacks-terraform"
    key    = "aws/dynamodb/terraform.tfstate"
    region = "eu-central-1"
  }
}
```

### File: `providers.tf`

```terraform
provider "aws" {
  region = "eu-central-1"
}
```

### File: `main.tf`

```terraform
resource "aws_dynamodb_table" "quickhacks_table" {
   name           = "Contacts"
   hash_key       = "Name"
   read_capacity  = 20
   write_capacity = 20

   attribute {
      name = "Name"
      type = "S"
   }

   tags = {
      Name      = "Quickhacks - DynamoDB Table"
      ManagedBy = "Terraform"
   }
}

resource "aws_dynamodb_table_item" "quickhacks_data" {
   table_name = aws_dynamodb_table.quickhacks_table.name
   hash_key   = aws_dynamodb_table.quickhacks_table.hash_key
   item       = <<EOF
{
  "Name": {"S": "Albert"}
}
EOF
}
```

## Fetch item by key

```console
$ AWS_PROFILE="quickhacks" aws dynamodb get-item --table-name Contacts --key "{\"Name\": {\"S\": \"Albert\"}}"
{
    "Item": {
        "Name": {
            "S": "Albert"
        }
    }
}
```

## Versions

1. Terraform

    ```console
    $ terraform --version
    Terraform v0.15.0
    ```

1. AWS CLI

   ```console
   $ aws --version
   aws-cli/2.1.30 Python/3.9.2 Darwin/20.4.0 source/x86_64 prompt/off
   ```
