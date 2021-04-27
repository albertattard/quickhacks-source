---
layout: default
title: S3 & Cloud Front
parent: Terraform
grand_parent: AWS
nav_order: 5
permalink: docs/aws/terraform/s3-cloud-front/
---

# S3 & Cloud Front

---

## Terraform

Terraform files: [s3-cloud-front.md]({{ "/assets/quickhacks/aws/terraform/s3-cloud-front.md" | absolute_url }}).

{% include custom/note.html details="Deployment may take several minute to create all required AWS resources. Don't be
alarmed if a simple deployment of few files take several minutes to complete." %}

### File: `terraform.tf`

The bucket `quickhacks-terraform` needs to be created beforehand. Refer to
[create S3 bucket]({{ "/docs/aws/cli/s3" | absolute_url }}#create-bucket) for more information about that.

```terraform
terraform {
  backend "s3" {
    bucket = "quickhacks-terraform"
    key    = "aws/s3-cloud-front/terraform.tfstate"
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

### File: `modules.tf`

Used this module so that I can easily set the content type of the files. The files to be deployed are found in the
folder `../web-app` with respect to this file.

```terraform
module "template_files" {
  source   = "hashicorp/dir/template"
  base_dir = "${path.module}/../web-app"
}
```

### File: `main.tf`

```terraform
locals {
  s3_origin_id   = "quickhacks_bucket"
  s3_bucket_name = "albertattard.quickhacks.cloud-front.bucket"
}

resource "aws_s3_bucket" "quickhacks_bucket" {
  bucket = local.s3_bucket_name
  acl    = "public-read"

  website {
    index_document = "index.html"
    error_document = "error.html"
  }

  policy = <<EOF
{
  "Version": "2008-10-17",
  "Statement": [
    {
      "Sid": "PublicReadForGetBucketObjects",
      "Effect": "Allow",
      "Principal": {
        "AWS": "*"
      },
      "Action": "s3:GetObject",
      "Resource": "arn:aws:s3:::${local.s3_bucket_name}/*"
    }
  ]
}
EOF

  tags = {
    Name      = "Quickhacks - Bucket"
    ManagedBy = "Terraform"
  }
}

resource "aws_s3_bucket_object" "quickhacks_bucket_object" {
  for_each     = module.template_files.files
  bucket       = aws_s3_bucket.quickhacks_bucket.id
  key          = each.key
  content_type = each.value.content_type
  source       = each.value.source_path
  content      = each.value.content
  etag         = each.value.digests.md5

  tags = {
    Name      = "Quickhacks - Bucket Object"
    ManagedBy = "Terraform"
  }
}

resource "aws_cloudfront_distribution" "quickhacks_s3_distribution" {
  enabled             = true
  default_root_object = "index.html"

  origin {
    domain_name = aws_s3_bucket.quickhacks_bucket.bucket_regional_domain_name
    origin_id   = local.s3_origin_id
  }

  default_cache_behavior {
    allowed_methods        = ["GET", "HEAD"]
    cached_methods         = ["GET", "HEAD"]
    target_origin_id       = local.s3_origin_id
    viewer_protocol_policy = "allow-all"
    min_ttl                = 0
    default_ttl            = 3600
    max_ttl                = 86400

    forwarded_values {
      query_string = false

      cookies {
        forward = "none"
      }
    }
  }

  restrictions {
    geo_restriction {
      restriction_type = "none"
    }
  }

  viewer_certificate {
    cloudfront_default_certificate = true
  }

  tags = {
    Name      = "Quickhacks - S3 Distribution"
    ManagedBy = "Terraform"
  }
}
```

### File: `output.tf`

```terraform
output "domain_name" {
   description = "Domain name corresponding to the distribution"
   value       = try(aws_cloudfront_distribution.quickhacks_s3_distribution.domain_name, "")
}
```

## Access the default root object

Access the default root object, which is set to be the `index.html` page.

```console
$ curl -v "https://$(AWS_PROFILE="quickhacks" terraform -chdir=terraform output -json domain_name | jq -r ".")"
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
