---
layout: default
title: Redshift Cluster (Publicly Accessible)
parent: Terraform
grand_parent: AWS
nav_order: 6
permalink: docs/aws/terraform/redshift-cluster-publicly-accessible/
---

# Redshift Cluster (Publicly Accessible)

{% include custom/pending.html details="Still working on this quickhack" %}

---

## Terraform

{% include custom/note.html details="Deployment may take few minute to create all required AWS resources." %}

### File: `terraform.tf`

The bucket `quickhacks-terraform` needs to be created beforehand. Refer to
[create S3 bucket]({{ "/docs/aws/cli/s3" | absolute_url }}#create-bucket) for more information about that.

{% highlight terraform %}
{% include quickhacks/aws/terraform/redshift-cluster-publicly-accessible/terraform.tf %}
{% endhighlight %}

### File: `providers.tf`

{% highlight terraform %}
{% include quickhacks/aws/terraform/redshift-cluster-publicly-accessible/providers.tf %}
{% endhighlight %}

### File: `main.tf`

{% highlight terraform %}
{% include quickhacks/aws/terraform/redshift-cluster-publicly-accessible/main.tf %}
{% endhighlight %}

### File: `output.tf`

{% highlight terraform %}
{% include quickhacks/aws/terraform/redshift-cluster-publicly-accessible/output.tf %}
{% endhighlight %}

## Connect to the database

{% include custom/pending.html details="Will add this once I figure out the issue" %}

```console
$ 
```

## Versions

1. Terraform

    ```console
    $ terraform --version
    Terraform v0.15.0
    ```
