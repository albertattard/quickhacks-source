---
layout: default
title: EC2 (Publicly Accessible)
parent: Terraform (AWS)
grand_parent: AWS
nav_order: 5
permalink: docs/aws/terraform/ec2-publicly-accessible/
---

# EC2 (Publicly Accessible)

---

## Acknowledgements

_Punit Kumar_, for reviewing the diagram and providing feedback.

## AWS Resources

![EC2 (Publicly Accessible)]({{ "/assets/images/aws/terraform/ec2-publicly-accessible.png" | absolute_url }})

## Terraform

### File: `terraform.tf`

The bucket `quickhacks-terraform` needs to be created beforehand. Refer to
[create S3 bucket]({{ "/docs/aws/cli/s3" | absolute_url }}#create-bucket) for more information about that.

{% highlight terraform %}
{% raw_include quickhacks/aws/terraform/ec2-publicly-accessible/terraform.tf %}
{% endhighlight %}

### File: `providers.tf`

{% highlight terraform %}
{% raw_include quickhacks/aws/terraform/ec2-publicly-accessible/providers.tf %}
{% endhighlight %}

### File: `main.tf`

The key pair (SSH key) `Quickhacks SSH Key` needs to be created beforehand.  Refer to
[create key pair]({{ "/docs/aws/cli/key-pair" | absolute_url }}#create-key-pair) for more information about that.

{% highlight terraform %}
{% raw_include quickhacks/aws/terraform/ec2-publicly-accessible/main.tf %}
{% endhighlight %}

### File: `output.tf`

{% highlight terraform %}
{% raw_include quickhacks/aws/terraform/ec2-publicly-accessible/output.tf %}
{% endhighlight %}

## SSH to EC2

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

1. SSH

   ```console
   $ ssh -V
   OpenSSH_8.1p1, LibreSSL 2.7.3
   ```

1. jq

   ```console
   $ jq --version
   jq-1.6
   ```
