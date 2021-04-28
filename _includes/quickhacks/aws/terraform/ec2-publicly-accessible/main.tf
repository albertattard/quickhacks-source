resource "aws_vpc" "quickhacks_vpc" {
  cidr_block           = "172.16.0.0/16"
  enable_dns_support   = true
  enable_dns_hostnames = true

  tags = {
    Name      = "Quickhacks - VPC"
    Quickhack = "EC2 - Publicly Accessible"
    ManagedBy = "Terraform"
  }
}

resource "aws_security_group" "quickhacks_sg_allow_ssh" {
  name        = "quickhacks-sg-allow-ssh-for-everyone"
  description = "Enable SSH (22) from anywhere on the internet"
  vpc_id      = aws_vpc.quickhacks_vpc.id

  ingress {
    cidr_blocks = ["0.0.0.0/0"]
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    description = "Enable SSH (22) from anywhere on the internet"
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
    Quickhack = "EC2 - Publicly Accessible"
    ManagedBy = "Terraform"
  }
}

resource "aws_subnet" "quickhacks_subnet" {
  vpc_id            = aws_vpc.quickhacks_vpc.id
  cidr_block        = "172.16.10.0/24"
  availability_zone = "eu-central-1a"

  tags = {
    Name      = "Quickhacks - Subnet"
    Quickhack = "EC2 - Publicly Accessible"
    ManagedBy = "Terraform"
  }
}

resource "aws_network_interface" "quickhacks_nic" {
  description     = "Network interface to be used by the EC2 instance"
  subnet_id       = aws_subnet.quickhacks_subnet.id
  private_ips     = ["172.16.10.100"]
  security_groups = [aws_security_group.quickhacks_sg_allow_ssh.id]

  tags = {
    Name      = "Quickhacks - NIC"
    Quickhack = "EC2 - Publicly Accessible"
    ManagedBy = "Terraform"
  }
}

resource "aws_instance" "quickhacks_ec2" {
  ami           = "ami-00a205cb8e06c3c4e"
  instance_type = "t2.micro"
  key_name      = "Quickhacks SSH Key"

  network_interface {
    network_interface_id = aws_network_interface.quickhacks_nic.id
    device_index         = 0
  }

  volume_tags = {
    Name      = "Quickhacks - EBS Root Volume"
    Quickhack = "EC2 - Publicly Accessible"
    ManagedBy = "Terraform"
  }

  tags = {
    Name      = "Quickhacks - EC2 Instance"
    Quickhack = "EC2 - Publicly Accessible"
    ManagedBy = "Terraform"
  }
}

resource "aws_ebs_volume" "quickhacks_volume" {
  availability_zone = "eu-central-1a"
  size              = 10

  tags = {
    Name      = "Quickhacks - EBS Volume"
    Quickhack = "EC2 - Publicly Accessible"
    ManagedBy = "Terraform"
  }
}

resource "aws_volume_attachment" "quickhacks_volume_attachment" {
  device_name = "/dev/sdh"
  volume_id   = aws_ebs_volume.quickhacks_volume.id
  instance_id = aws_instance.quickhacks_ec2.id
}

resource "aws_eip" "quickhacks_eip" {
  associate_with_private_ip = aws_network_interface.quickhacks_nic.private_ip
  vpc                       = true

  tags = {
    Name      = "Quickhacks - Elastic IP"
    Quickhack = "EC2 - Publicly Accessible"
    ManagedBy = "Terraform"
  }
}

resource "aws_internet_gateway" "quickhacks_gateway" {
  vpc_id = aws_vpc.quickhacks_vpc.id

  tags = {
    Name      = "Quickhacks - Internet Gateway"
    Quickhack = "EC2 - Publicly Accessible"
    ManagedBy = "Terraform"
  }
}

resource "aws_route_table" "quickhacks_route_table" {
  vpc_id = aws_vpc.quickhacks_vpc.id

  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.quickhacks_gateway.id
  }

  tags = {
    Name      = "Quickhacks - Route Table"
    Quickhack = "EC2 - Publicly Accessible"
    ManagedBy = "Terraform"
  }
}

resource "aws_route_table_association" "quickhacks_route_table_association" {
  subnet_id      = aws_subnet.quickhacks_subnet.id
  route_table_id = aws_route_table.quickhacks_route_table.id
}
