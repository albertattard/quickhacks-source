terraform {
  backend "s3" {
    bucket = "quickhacks-terraform"
    key    = "aws/dynamodb/terraform.tfstate"
    region = "eu-central-1"
  }
}
