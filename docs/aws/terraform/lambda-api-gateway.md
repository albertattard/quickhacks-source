---
layout: default
title: Lambda (API Gateway)
parent: Terraform
grand_parent: AWS
nav_order: 4
permalink: docs/aws/terraform/lambda-api-gateway/
---

# Lambda (API Gateway)

---

## Terraform

Terraform files: [lambda-api-gateway.zip]({{ "/assets/quickhacks/aws/terraform/lambda-api-gateway.zip" | absolute_url }})

### File: `terraform.tf`

The bucket `quickhacks-terraform` needs to be created beforehand.

```terraform
terraform {
  backend "s3" {
    bucket = "quickhacks-terraform"
    key    = "aws/lambda-api-gateway/terraform.tfstate"
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

The Lambda distribution file `lambda.zip` is saved in the same folder as the `main.tf` file. Also, change the `runtime`
and `handler` as required.

```terraform
locals {
   lambda_zip_file = "${path.module}/lambda.zip"
   lambda_function_name = "quickhacks-lambda-function"
}

resource "aws_iam_role" "quickhacks_iam_for_lambda" {
   name        = "quickhacks_iam_for_lambda"
   description = "Allow the lambda function to assume roles"

   assume_role_policy = <<EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Sid": "LabLambdaIamRole",
      "Action": "sts:AssumeRole",
      "Principal": {
        "Service": "lambda.amazonaws.com"
      },
      "Effect": "Allow"
    }
  ]
}
EOF

   tags = {
      Name      = "Quickhacks - Lambda IAM Role"
      ManagedBy = "Terraform"
   }
}

resource "aws_lambda_function" "quickhacks_lambda_function" {
   function_name    = local.lambda_function_name
   description      = "Lab Lambda Function"
   filename         = local.lambda_zip_file
   source_code_hash = filebase64sha256(local.lambda_zip_file)
   handler          = "exports.handler"
   runtime          = "nodejs12.x"
   role             = aws_iam_role.quickhacks_iam_for_lambda.arn

   environment {
      variables = {
         name      = "Quickhacks - Lambda"
         ManagedBy = "Terraform"
      }
   }

   tags = {
      Name      = "Quickhacks - Lambda Function"
      ManagedBy = "Terraform"
   }
}

resource "aws_api_gateway_rest_api" "quickhacks_gateway" {
   name        = "quickhacks_api_gateway_rest_api"
   description = "Quickhacks - API Gateway Rest API"

   tags = {
      Name      = "Quickhacks - API Gateway"
      ManagedBy = "Terraform"
   }
}

resource "aws_api_gateway_resource" "quickhacks_proxy" {
   rest_api_id = aws_api_gateway_rest_api.quickhacks_gateway.id
   parent_id   = aws_api_gateway_rest_api.quickhacks_gateway.root_resource_id
   path_part   = "{proxy+}"
}

resource "aws_api_gateway_method" "quickhacks_proxy" {
   rest_api_id   = aws_api_gateway_rest_api.quickhacks_gateway.id
   resource_id   = aws_api_gateway_resource.quickhacks_proxy.id
   http_method   = "ANY"
   authorization = "NONE"
}

resource "aws_api_gateway_integration" "quickhacks_lambda" {
   rest_api_id = aws_api_gateway_rest_api.quickhacks_gateway.id
   resource_id = aws_api_gateway_method.quickhacks_proxy.resource_id
   http_method = aws_api_gateway_method.quickhacks_proxy.http_method

   integration_http_method = "POST"
   type                    = "AWS_PROXY"
   uri                     = aws_lambda_function.quickhacks_lambda_function.invoke_arn
}

resource "aws_api_gateway_method" "quickhacks_proxy_root" {
   rest_api_id   = aws_api_gateway_rest_api.quickhacks_gateway.id
   resource_id   = aws_api_gateway_rest_api.quickhacks_gateway.root_resource_id
   http_method   = "ANY"
   authorization = "NONE"
}

resource "aws_api_gateway_integration" "quickhacks_lambda_root" {
   rest_api_id = aws_api_gateway_rest_api.quickhacks_gateway.id
   resource_id = aws_api_gateway_method.quickhacks_proxy_root.resource_id
   http_method = aws_api_gateway_method.quickhacks_proxy_root.http_method

   integration_http_method = "POST"
   type                    = "AWS_PROXY"
   uri                     = aws_lambda_function.quickhacks_lambda_function.invoke_arn
}

resource "aws_api_gateway_deployment" "quickhacks_deployment" {
   description = "Lab Deployment"

   depends_on = [
      aws_api_gateway_integration.quickhacks_lambda,
      aws_api_gateway_integration.quickhacks_lambda_root,
   ]

   rest_api_id = aws_api_gateway_rest_api.quickhacks_gateway.id
   stage_name  = "test"
}

resource "aws_lambda_permission" "quickhacks_permissions" {
   statement_id  = "AllowAPIGatewayInvoke"
   action        = "lambda:InvokeFunction"
   function_name = aws_lambda_function.quickhacks_lambda_function.function_name
   principal     = "apigateway.amazonaws.com"

   # The "/*/*" portion grants access from any method on any resource
   # within the API Gateway REST API.
   source_arn = "${aws_api_gateway_rest_api.quickhacks_gateway.execution_arn}/*/*"
}

resource "aws_cloudwatch_log_group" "quickhacks_cloudwatch_log_group" {
   name              = "/aws/lambda/${local.lambda_function_name}"
   retention_in_days = 1

   tags = {
      Name      = "Quickhacks - Cloudwatch Log Group"
      ManagedBy = "Terraform"
   }
}

resource "aws_iam_policy" "quickhacks_lambda_logging" {
   name        = "quickhacks_lambda_logging"
   path        = "/"
   description = "Enable the lambda function to write logs in CloudWatch"

   policy = <<EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Action": [
        "logs:CreateLogGroup",
        "logs:CreateLogStream",
        "logs:PutLogEvents"
      ],
      "Resource": "arn:aws:logs:*:*:*",
      "Effect": "Allow"
    }
  ]
}
EOF
}

resource "aws_iam_role_policy_attachment" "quickhacks_lambda_logging_policy_attachment" {
   role       = aws_iam_role.quickhacks_iam_for_lambda.name
   policy_arn = aws_iam_policy.quickhacks_lambda_logging.arn
}
```

### File: `output.tf`

```terraform
output "invoke_url" {
  value = aws_api_gateway_deployment.quickhacks_deployment.invoke_url
}
```

## Invoke Lambda

Make an HTTP request to the lambda function

```console
$ curl $(AWS_PROFILE="quickhacks" terraform output -json invoke_url | jq -r ".") | jq
{
  "quickhacks": "Lambda (API Gateway)"
}
```

## Versions

1. Terraform

    ```console
    $ terraform --version
    Terraform v0.15.0
    ```

1. CURl

   ```console
   $ curl --version
   curl 7.64.1 (x86_64-apple-darwin20.0) libcurl/7.64.1 (SecureTransport) LibreSSL/2.8.3 zlib/1.2.11 nghttp2/1.41.0
   Release-Date: 2019-03-27
   Protocols: dict file ftp ftps gopher http https imap imaps ldap ldaps pop3 pop3s rtsp smb smbs smtp smtps telnet tftp
   Features: AsynchDNS GSS-API HTTP2 HTTPS-proxy IPv6 Kerberos Largefile libz MultiSSL NTLM NTLM_WB SPNEGO SSL UnixSockets
   ```

1. jq

   ```console
   $ jq --version
   jq-1.6
   ```
