resource "aws_vpc" "quickhacks_vpc" {
  cidr_block           = "172.16.0.0/16"
  enable_dns_support   = true
  enable_dns_hostnames = true

  tags = {
    Name      = "Quickhacks - VPC"
    Quickhack = "RDS Postgres - Publicly Accessible"
    ManagedBy = "Terraform"
  }
}

resource "aws_internet_gateway" "quickhacks_internet_gateway" {
  vpc_id = aws_vpc.quickhacks_vpc.id

  tags = {
    Name      = "Quickhacks - Internet Gateway"
    Quickhack = "RDS Postgres - Publicly Accessible"
    ManagedBy = "Terraform"
  }
}

resource "aws_route_table" "quickhacks_routing_table" {
  vpc_id = aws_vpc.quickhacks_vpc.id

  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.quickhacks_internet_gateway.id
  }

  tags = {
    Name      = "Quickhacks - Routing Table"
    Quickhack = "RDS Postgres - Publicly Accessible"
    ManagedBy = "Terraform"
  }
}

resource "aws_security_group" "quickhacks_sg_allow_postgresql" {
  name        = "quickhacks-sg-allow-postgres-for-everyone"
  description = "Enable Postgres connection (5432) from anywhere on the internet"
  vpc_id      = aws_vpc.quickhacks_vpc.id

  ingress {
    description = "Enable Postgres connection (5432) from anywhere on the internet"
    cidr_blocks = ["0.0.0.0/0"]
    from_port   = 5432
    to_port     = 5432
    protocol    = "tcp"
  }

  /* Terraform removes the default rule, this we need to add it */
  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name      = "Quickhacks - Security Group"
    Quickhack = "RDS Postgres - Publicly Accessible"
    ManagedBy = "Terraform"
  }
}

resource "aws_subnet" "quickhacks_subnet_a" {
  vpc_id            = aws_vpc.quickhacks_vpc.id
  cidr_block        = "172.16.10.0/24"
  availability_zone = "eu-central-1a"

  tags = {
    Name      = "Quickhacks - Public Subnet 10/A"
    Quickhack = "RDS Postgres - Publicly Accessible"
    ManagedBy = "Terraform"
  }
}

resource "aws_subnet" "quickhacks_subnet_b" {
  vpc_id            = aws_vpc.quickhacks_vpc.id
  cidr_block        = "172.16.11.0/24"
  availability_zone = "eu-central-1b"

  tags = {
    Name      = "Quickhacks - Public Subnet 11/B"
    Quickhack = "RDS Postgres - Publicly Accessible"
    ManagedBy = "Terraform"
  }
}

resource "aws_subnet" "quickhacks_subnet_c" {
  vpc_id            = aws_vpc.quickhacks_vpc.id
  cidr_block        = "172.16.12.0/24"
  availability_zone = "eu-central-1c"

  tags = {
    Name      = "Quickhacks - Public Subnet 12/C"
    Quickhack = "RDS Postgres - Publicly Accessible"
    ManagedBy = "Terraform"
  }
}

resource "aws_db_subnet_group" "quickhacks_db_subnet" {
  name       = "quickhacks_db_subnet"
  subnet_ids = [aws_subnet.quickhacks_subnet_a.id, aws_subnet.quickhacks_subnet_b.id, aws_subnet.quickhacks_subnet_c.id]

  tags = {
    Name      = "Quickhacks - DB Subnet"
    Quickhack = "RDS Postgres - Publicly Accessible"
    ManagedBy = "Terraform"
  }
}

resource "aws_route_table_association" "quickhacks_route_table_association" {
  subnet_id      = aws_db_subnet_group.quickhacks_db_subnet.id
  route_table_id = aws_route_table.quickhacks_routing_table.id
}

resource "random_password" "quickhacks_db_password" {
  length           = 16
  special          = true
  override_special = "_%@"
}

resource "aws_db_instance" "quickhacks_db" {
  allocated_storage    = 20
  storage_type         = "gp2"
  engine               = "postgres"
  engine_version       = "12.4"
  instance_class       = "db.t2.micro"
  name                 = "quickhacks_db"
  username             = "quickhacks"
  password             = random_password.quickhacks_db_password.result
  apply_immediately    = true
  identifier           = "quickhacks-db"
  skip_final_snapshot  = true
  publicly_accessible  = true
  db_subnet_group_name = aws_db_subnet_group.quickhacks_db_subnet.name

  tags = {
    Name      = "Quickhacks - DB"
    Quickhack = "RDS Postgres - Publicly Accessible"
    ManagedBy = "Terraform"
  }
}
