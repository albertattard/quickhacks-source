terraform {
  backend "s3" {
    bucket = "quickhacks-terraform"
    key    = "aws/s3-cloud-front/terraform.tfstate"
    region = "eu-central-1"
  }
}
