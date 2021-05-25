---
layout: default
title: RDS Postgres (Publicly Accessible)
parent: Terraform (AWS)
grand_parent: AWS
nav_order: 18.1
permalink: docs/aws/terraform/rds-postgres-publicly-accessible/
---

# RDS Postgres (Publicly Accessible)

{% include custom/example_not_working.html details="The database is created but not publicly accessible as expected." %}

## Terraform

{% include custom/note.html details="Deployment may take few minute to create all required AWS resources." %}

### File: `terraform.tf`

The bucket `quickhacks-terraform` needs to be created beforehand. Refer to
[create S3 bucket]({{ "/docs/aws/cli/s3" | absolute_url }}#create-bucket) for more information about that.

{% highlight terraform %}
{% raw_include quickhacks/aws/terraform/rds-postgres-publicly-accessible/terraform.tf %}
{% endhighlight %}

### File: `providers.tf`

{% highlight terraform %}
{% raw_include quickhacks/aws/terraform/rds-postgres-publicly-accessible/providers.tf %}
{% endhighlight %}

### File: `main.tf`

{% highlight terraform %}
{% raw_include quickhacks/aws/terraform/rds-postgres-publicly-accessible/main.tf %}
{% endhighlight %}

### File: `output.tf`

{% highlight terraform %}
{% raw_include quickhacks/aws/terraform/rds-postgres-publicly-accessible/output.tf %}
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
