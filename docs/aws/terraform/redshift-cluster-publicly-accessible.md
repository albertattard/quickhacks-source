---
layout: default
title: Redshift Cluster (Publicly Accessible)
parent: Terraform (AWS)
grand_parent: AWS
nav_order: 18.2
permalink: docs/aws/terraform/redshift-cluster-publicly-accessible/
---

# Redshift Cluster (Publicly Accessible)

---

## Terraform

{% include custom/note.html details="Deployment may take few minute to create all required AWS resources." %}

### File: `terraform.tf`

The bucket `quickhacks-terraform` needs to be created beforehand. Refer to
[create S3 bucket]({{ "/docs/aws/cli/s3" | absolute_url }}#create-bucket) for more information about that.

{% highlight terraform %}
{% raw_include quickhacks/aws/terraform/redshift-cluster-publicly-accessible/terraform.tf %}
{% endhighlight %}

### File: `providers.tf`

{% highlight terraform %}
{% raw_include quickhacks/aws/terraform/redshift-cluster-publicly-accessible/providers.tf %}
{% endhighlight %}

### File: `main.tf`

{% highlight terraform %}
{% raw_include quickhacks/aws/terraform/redshift-cluster-publicly-accessible/main.tf %}
{% endhighlight %}

### File: `output.tf`

{% highlight terraform %}
{% raw_include quickhacks/aws/terraform/redshift-cluster-publicly-accessible/output.tf %}
{% endhighlight %}

## Connect to the database

PostgreSQL client can be used to connect to Redshift. The following Dockerfile installs `postgresql-client-12` on a
Ubuntu image and can be used to connect to the Redshift cluster.

{% highlight Dockerfile %}
{% raw_include quickhacks/aws/terraform/redshift-cluster-publicly-accessible/Dockerfile %}
{% endhighlight %}

1. Build the docker image

   ```console
   $ docker build . -t postgresql-client:local 
   ```

1. Connect to the database using the docker container

   The following command needs to be executed from the same directory where the terraform scripts are as it uses the
   terraform output to connect to set two environment variables required to the database.

   ```console
   $ docker run \
     -e PGHOST="$(AWS_PROFILE="quickhacks" terraform output -json | jq -r ".redshift_dns_name.value")" \
     -e PGPASSWORD="$(AWS_PROFILE="quickhacks" terraform output -json | jq -r ".redshift_master_password.value")" \
     -it postgresql-client:local 
   ```

1. Type `\q` to exit

## Versions

1. Terraform

    ```console
    $ terraform --version
    Terraform v0.15.0
    ```
