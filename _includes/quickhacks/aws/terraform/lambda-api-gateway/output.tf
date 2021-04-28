output "invoke_url" {
  description = "The Lambda function invoke URL"
  value       = aws_api_gateway_deployment.quickhacks_deployment.invoke_url
}
