terraform {
  backend "s3" {
    bucket = "quickhacks-terraform"
    key    = "aws/lambda-api-gateway/terraform.tfstate"
    region = "eu-central-1"
  }
}