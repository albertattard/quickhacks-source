---
layout: default
title: VM (Publicly Accessible)
parent: Terraform (Azure)
grand_parent: Azure
nav_order: 5
permalink: docs/azure/terraform/vm-publicly-accessible/
---

# Virtual Machine (Publicly Accessible)

{% include custom/pending.html details="I am still working on this one" %}

---

## Terraform

### File: `terraform.tf`

{% highlight terraform %}
{% raw_include quickhacks/azure/terraform/vm-publicly-accessible/terraform.tf %}
{% endhighlight %}

### File: `providers.tf`

{% highlight terraform %}
{% raw_include quickhacks/azure/terraform/vm-publicly-accessible/providers.tf %}
{% endhighlight %}

### File: `main.tf`

{% highlight terraform %}
{% raw_include quickhacks/azure/terraform/vm-publicly-accessible/main.tf %}
{% endhighlight %}

### File: `output.tf`

{% highlight terraform %}
{% raw_include quickhacks/azure/terraform/vm-publicly-accessible/output.tf %}
{% endhighlight %}

## SSH to VM

Connect to the VM instance using the private key

```console
$ 
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
