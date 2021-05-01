---
layout: default
title: Lambda (API Gateway)
parent: Terraform
grand_parent: AWS
nav_order: 12
permalink: docs/aws/terraform/lambda-api-gateway/
---

# Lambda (API Gateway)

---

## Terraform

### File: `terraform.tf`

The bucket `quickhacks-terraform` needs to be created beforehand. Refer to
[create S3 bucket]({{ "/docs/aws/cli/s3" | absolute_url }}#create-bucket) for more information about that.

{% highlight terraform %}
{% raw_include quickhacks/aws/terraform/lambda-api-gateway/terraform.tf %}
{% endhighlight %}

### File: `providers.tf`

{% highlight terraform %}
{% raw_include quickhacks/aws/terraform/lambda-api-gateway/providers.tf %}
{% endhighlight %}

### File: `main.tf`

The Lambda distribution file `lambda.zip` is saved in the same folder as the `main.tf` file. Also, change the `runtime`
and `handler` as required.

{% highlight terraform %}
{% raw_include quickhacks/aws/terraform/lambda-api-gateway/main.tf %}
{% endhighlight %}

### File: `output.tf`

{% highlight terraform %}
{% raw_include quickhacks/aws/terraform/lambda-api-gateway/output.tf %}
{% endhighlight %}

## Invoke Lambda

Make an HTTP request to the lambda function

```console
$ curl $(AWS_PROFILE="quickhacks" terraform output -json invoke_url | jq -r ".") | jq
{
  "quickhacks": "Lambda (API Gateway)"
}
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
