terraform {
  backend "s3" {
    bucket = "quickhacks-terraform"
    key    = "aws/redshift-cluster-publicly-accessible/terraform.tfstate"
    region = "eu-central-1"
  }
}
