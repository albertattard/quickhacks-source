terraform {
  backend "s3" {
    bucket = "quickhacks-terraform"
    key    = "aws/ec2-publicly-accessible/terraform.tfstate"
    region = "eu-central-1"
  }
}