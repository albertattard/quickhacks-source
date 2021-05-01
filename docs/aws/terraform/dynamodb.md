---
layout: default
title: DynamoDB
parent: Terraform
grand_parent: AWS
nav_order: 4
permalink: docs/aws/terraform/dynamodb/
---

# DynamoDB

---

## Terraform

### File: `terraform.tf`

The bucket `quickhacks-terraform` needs to be created beforehand. Refer to
[create S3 bucket]({{ "/docs/aws/cli/s3" | absolute_url }}#create-bucket) for more information about that.

{% highlight terraform %}
{% raw_include quickhacks/aws/terraform/dynamodb/terraform.tf %}
{% endhighlight %}

### File: `providers.tf`

{% highlight terraform %}
{% raw_include quickhacks/aws/terraform/dynamodb/providers.tf %}
{% endhighlight %}

### File: `main.tf`

{% highlight terraform %}
{% raw_include quickhacks/aws/terraform/dynamodb/main.tf %}
{% endhighlight %}

## Fetch item by key

```console
$ AWS_PROFILE="quickhacks" aws dynamodb get-item --table-name Contacts --key "{\"Name\": {\"S\": \"Albert\"}}"
{
    "Item": {
        "Location": {
            "S": "Köln"
        },
        "Surname": {
            "S": "Attard"
        },
        "Name": {
            "S": "Albert"
        }
    }
}
```

## Versions

1. Terraform

    ```console
    $ terraform --version
    Terraform v0.15.0
    ```

1. AWS CLI

   ```console
   $ aws --version
   aws-cli/2.1.30 Python/3.9.2 Darwin/20.4.0 source/x86_64 prompt/off
   ```
