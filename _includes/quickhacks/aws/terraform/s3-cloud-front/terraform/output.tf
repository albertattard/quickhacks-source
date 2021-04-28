output "domain_name" {
  description = "Domain name corresponding to the distribution"
  value       = try(aws_cloudfront_distribution.auickhacks_s3_distribution.domain_name, "")
}
