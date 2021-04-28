locals {
  s3_origin_id   = "quickhacks_bucket"
  s3_bucket_name = "albertattard.quickhacks.cloud-front.bucket"
}

resource "aws_s3_bucket" "auickhacks_bucket" {
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
    Quickhack = "S3 and Cloud Front"
    ManagedBy = "Terraform"
  }
}

resource "aws_s3_bucket_object" "auickhacks_bucket_object" {
  for_each     = module.template_files.files
  bucket       = aws_s3_bucket.auickhacks_bucket.id
  key          = each.key
  content_type = each.value.content_type
  source       = each.value.source_path
  content      = each.value.content
  etag         = each.value.digests.md5

  tags = {
    Name      = "Quickhacks - Bucket Object ${each.value.source_path}"
    Quickhack = "S3 and Cloud Front"
    ManagedBy = "Terraform"
  }
}

resource "aws_cloudfront_distribution" "auickhacks_s3_distribution" {
  origin {
    domain_name = aws_s3_bucket.auickhacks_bucket.bucket_regional_domain_name
    origin_id   = local.s3_origin_id
  }

  enabled             = true
  default_root_object = "index.html"

  default_cache_behavior {
    allowed_methods  = ["GET", "HEAD"]
    cached_methods   = ["GET", "HEAD"]
    target_origin_id = local.s3_origin_id

    forwarded_values {
      query_string = false

      cookies {
        forward = "none"
      }
    }

    viewer_protocol_policy = "allow-all"
    min_ttl                = 0
    default_ttl            = 3600
    max_ttl                = 86400
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
    Quickhack = "S3 and Cloud Front"
    ManagedBy = "Terraform"
  }
}
