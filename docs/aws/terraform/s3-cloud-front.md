---
layout: default
title: S3 & Cloud Front
parent: Terraform (AWS)
grand_parent: AWS
nav_order: 19
permalink: docs/aws/terraform/s3-cloud-front/
---

# S3 & Cloud Front

---

## Terraform

{% include custom/note.html details="Deployment may take several minute to create all required AWS resources. Don't be
alarmed if a simple deployment of few files take several minutes to complete." %}

### File: `terraform.tf`

The bucket `quickhacks-terraform` needs to be created beforehand. Refer to
[create S3 bucket]({{ "/docs/aws/cli/s3" | absolute_url }}#create-bucket) for more information about that.

{% highlight terraform %}
{% raw_include quickhacks/aws/terraform/s3-cloud-front/terraform/terraform.tf %}
{% endhighlight %}

### File: `providers.tf`

{% highlight terraform %}
{% raw_include quickhacks/aws/terraform/s3-cloud-front/terraform/providers.tf %}
{% endhighlight %}

### File: `modules.tf`

Used this module so that I can easily set the content type of the files. The files to be deployed are found in the
folder `../web-app` with respect to this file.

{% highlight terraform %}
{% raw_include quickhacks/aws/terraform/s3-cloud-front/terraform/modules.tf %}
{% endhighlight %}

### File: `main.tf`

{% highlight terraform %}
{% raw_include quickhacks/aws/terraform/s3-cloud-front/terraform/main.tf %}
{% endhighlight %}

### File: `output.tf`

{% highlight terraform %}
{% raw_include quickhacks/aws/terraform/s3-cloud-front/terraform/output.tf %}
{% endhighlight %}

## Access the default root object

Access the default root object, which is set to be the `index.html` page.

```console
$ curl -v "https://$(AWS_PROFILE="quickhacks" terraform -chdir=terraform output -json domain_name | jq -r ".")"
```

## Versions

1. Terraform

    ```console
    $ terraform --version
    Terraform v0.15.0
    ```

1. cURL

   ```console
   $ curl --version
   curl 7.64.1 (x86_64-apple-darwin20.0) libcurl/7.64.1 (SecureTransport) LibreSSL/2.8.3 zlib/1.2.11 nghttp2/1.41.0
   Release-Date: 2019-03-27
   Protocols: dict file ftp ftps gopher http https imap imaps ldap ldaps pop3 pop3s rtsp smb smbs smtp smtps telnet tftp
   Features: AsynchDNS GSS-API HTTP2 HTTPS-proxy IPv6 Kerberos Largefile libz MultiSSL NTLM NTLM_WB SPNEGO SSL UnixSockets
   ```

1. jq

   ```console
   $ jq --version
   jq-1.6
   ```
