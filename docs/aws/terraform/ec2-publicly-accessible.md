---
layout: default
title: EC2 (Publicly Accessible)
parent: Terraform
grand_parent: AWS
nav_order: 2
permalink: docs/aws/terraform/ec2-publicly-accessible/
---

# EC2 (Publicly Accessible)

---

## AWS Resources

![EC2 (Publicly Accessible)]({% link assets/images/aws/terraform/ec2-publicly-accessible.png %})

## Terraform

### File: `terraform.tf`

The bucket `quickhacks-terraform` needs to be created beforehand. Refer to 
[create S3 bucket]({% link docs/aws/cli/s3.md %}#create-bucket) for more information about that.

```terraform
terraform {
  backend "s3" {
    bucket = "quickhacks-terraform"
    key    = "aws/ec2-publicly-accessible/terraform.tfstate"
    region = "eu-central-1"
  }
}
```

### File: `providers.tf`

```terraform
provider "aws" {
  region = "eu-central-1"
}
```

### File: `main.tf`

The key pair (SSH key) `Quickhacks SSH Key` needs to be created beforehand.  Refer to
[create key pair]({% link docs/aws/cli/ssh-key.md %}#create-key-pair) for more information about that.

```terraform
resource "aws_vpc" "quickhacks_vpc" {
  cidr_block           = "172.16.0.0/16"
  enable_dns_support   = true
  enable_dns_hostnames = true

  tags = {
    Name      = "Quickhacks - VPC"
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
    ManagedBy = "Terraform"
  }
}

resource "aws_subnet" "quickhacks_subnet" {
  vpc_id            = aws_vpc.quickhacks_vpc.id
  cidr_block        = "172.16.10.0/24"
  availability_zone = "eu-central-1a"

  tags = {
    Name      = "Quickhacks - Subnet"
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
    ManagedBy = "Terraform"
  }
}

resource "aws_instance" "quickhacks_ec2" {
  ami           = "ami-00a205cb8e06c3c4e"
  instance_type = "t2.micro"
  key_name      = "Quickhacks Key"

  network_interface {
    network_interface_id = aws_network_interface.quickhacks_nic.id
    device_index         = 0
  }

  volume_tags = {
    Name      = "Quickhacks - EBS Root Volume"
    ManagedBy = "Terraform"
  }

  tags = {
    Name      = "Quickhacks - EC2 Instance"
    ManagedBy = "Terraform"
  }
}

resource "aws_ebs_volume" "quickhacks_volume" {
  availability_zone = "eu-central-1a"
  size              = 10

  tags = {
    Name      = "Quickhacks - EBS Volume"
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
    ManagedBy = "Terraform"
  }
}

resource "aws_internet_gateway" "quickhacks_gateway" {
  vpc_id = aws_vpc.quickhacks_vpc.id

  tags = {
    Name      = "Quickhacks - Internet Gateway"
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
    ManagedBy = "Terraform"
  }
}

resource "aws_route_table_association" "quickhacks_route_table_association" {
  subnet_id      = aws_subnet.quickhacks_subnet.id
  route_table_id = aws_route_table.quickhacks_route_table.id
}
```

### File: `output.tf`

```terraform
output "instance_ip" {
  description = "Elastic (public) IP, associated to the network interface connected to the EC2 instance"
  value       = coalesce(aws_eip.quickhacks_eip.*.public_ip)
}
```

## Connect

Connect to the EC2 instance using the private key

```console
$ ssh -i "quickhacks.pem" ec2-user@$(AWS_PROFILE="quickhacks" terraform output -json instance_ip | jq -r ".[0]")
```

## Versions

1. Terraform

    ```console
    $ terraform --version
    Terraform v0.15.0
    ```
