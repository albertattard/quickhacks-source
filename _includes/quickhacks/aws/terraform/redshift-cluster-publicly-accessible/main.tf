data "aws_vpc" "default" {
  default = true
}

data "aws_subnet_ids" "redshift" {
  vpc_id = data.aws_vpc.default.id
}

resource "aws_iam_role" "redshift" {
  name        = "redshift_airflow_role"
  description = "Allows Redshift clusters to call AWS services"

  assume_role_policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Action = "sts:AssumeRole"
        Effect = "Allow"
        Principal = {
          Service = "redshift.amazonaws.com"
        }
      },
    ]
  })

  tags = {
    Name      = "Redshift Airflow Role"
    Quickhack = "Redshift Cluster - Publicly Accessible"
    ManagedBy = "Terraform"
  }
}

resource "aws_iam_role_policy_attachment" "redshift" {
  role       = aws_iam_role.redshift.name
  policy_arn = "arn:aws:iam::aws:policy/AmazonS3ReadOnlyAccess"
}

resource "aws_security_group" "redshift" {
  name        = "redshift_security_group"
  description = "Authorise redshift cluster access"
  vpc_id      = data.aws_vpc.default.id

  ingress {
    description = "Allow access to Redshift from anywhere in the world!!"
    from_port   = 5439
    to_port     = 5439
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name      = "Redshift Security Group"
    Quickhack = "Redshift Cluster - Publicly Accessible"
    ManagedBy = "Terraform"
  }
}

resource "random_password" "redshift" {
  length           = 16
  special          = true
  override_special = "!#$&-_+."
  min_lower        = 1
  min_upper        = 1
  min_numeric      = 1
  min_special      = 1
}

resource "aws_redshift_subnet_group" "redshift" {
  name       = "redshift-subnet-group"
  subnet_ids = data.aws_subnet_ids.redshift.ids

  tags = {
    Name      = "Redshift Subnet Group"
    Quickhack = "Redshift Cluster - Publicly Accessible"
    ManagedBy = "Terraform"
  }
}

resource "aws_redshift_cluster" "redshift" {
  cluster_identifier        = "quickhack-redshift-cluster"
  database_name             = "quickhack_db"
  port                      = 5439
  master_username           = "quickhack"
  master_password           = random_password.redshift.result
  node_type                 = "dc2.large"
  cluster_type              = "single-node"
  number_of_nodes           = 1
  publicly_accessible       = true
  cluster_subnet_group_name = aws_redshift_subnet_group.redshift.name
  vpc_security_group_ids    = [aws_security_group.redshift.id]
  iam_roles                 = [aws_iam_role.redshift.arn]
  enhanced_vpc_routing      = false
  skip_final_snapshot       = true

  tags = {
    Name      = "Redshift Cluster"
    Quickhack = "Redshift Cluster - Publicly Accessible"
    ManagedBy = "Terraform"
  }
}