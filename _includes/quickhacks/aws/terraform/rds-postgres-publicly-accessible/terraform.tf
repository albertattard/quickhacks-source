terraform {
  backend "s3" {
    bucket = "quickhacks-terraform"
    key    = "aws/rds-postgres-publicly-accessible/terraform.tfstate"
    region = "eu-central-1"
  }
}
