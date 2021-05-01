---
layout: default
title: Jupyter
parent: Docker
nav_order: 2
permalink: docs/docker/jupyter/
---

# Jupyter

---

## Docker Compose

### File: `docker-compose.yml`

{% highlight yaml %}
{% raw_include quickhacks/docker/jupyter/docker-compose.yml %}
{% endhighlight %}

### File: `docker/jupyter/Dockerfile`

{% highlight yaml %}
{% raw_include quickhacks/docker/jupyter/docker/jupyter/Dockerfile %}
{% endhighlight %}

## Version

1. Docker

    ```console
    $ docker --version
    Docker version 20.10.5, build 55c4c88
    ```

1. Docker Compose

    ```console
    $ docker-compose --version
    docker-compose version 1.29.0, build 07737305
    ```
