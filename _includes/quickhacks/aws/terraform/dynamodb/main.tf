resource "aws_dynamodb_table" "quickhacks_table" {
  name           = "Contacts"
  hash_key       = "Name"
  read_capacity  = 20
  write_capacity = 20

  attribute {
    name = "Name"
    type = "S"
  }

  tags = {
    Name      = "Quickhacks - DynamoDB Table"
    Quickhack = "DynamoDB"
    ManagedBy = "Terraform"
  }
}

resource "aws_dynamodb_table_item" "quickhacks_data" {
  table_name = aws_dynamodb_table.quickhacks_table.name
  hash_key   = aws_dynamodb_table.quickhacks_table.hash_key
  item       = <<EOF
{
  "Name": {"S": "Albert"},
  "Surname": {"S": "Attard"},
  "Location": {"S": "Köln"}
}
EOF
}