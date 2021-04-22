---
layout: default
title: Jupyter
parent: Docker
nav_order: 1
permalink: docs/docker/jupyter/
---

# Jupyter
{: .no_toc }

## Table of contents
{: .no_toc .text-delta }

1. TOC
   {:toc}

---

## Docker and Docker Compose Version

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

## Docker Compose

```yaml
version: "3.9"

services:
  jupyter:
    container_name: "quickhacks-docker-jupyter-jupyter"
    build: "docker/jupyter"
    ports:
      - "8888:8888"
    volumes:
      - "jupyter:/home/jovyan/work"
    healthcheck:
      start_period: "5s"
      interval: "5s"
      retries: 5
    restart: "always"
    depends_on:
      db:
        condition: "service_healthy"
    networks:
      - "quickhacks"

volumes:
  jupyter:
    name: "quickhacks-docker-jupyter-jupyter"
    driver: "local"
    driver_opts:
      type: "none"
      o: "bind"
      device: "./work"

networks:
  quickhacks:
    name: "quickhacks-docker-jupyter-network"
```

Dockerfile: `docker/jupyter/Dockerfile`

```dockerfile
# I had to downgrade as the newer version was raising a ResourceClosedError everytime I ran a query that returned
# nothing, such as an INSERT statement.
# FROM jupyter/datascience-notebook:python-3.8.8
FROM jupyter/datascience-notebook:python-3.7.6

USER root
RUN apt update && apt install -y gnupg2 wget ca-certificates postgresql postgresql-contrib default-jdk

USER jovyan
RUN pip install ipython-sql psycopg2-binary pyspark

# There does not seem to be a standard way to have healthcheck for Jupyer notbooks according to
# https://github.com/jupyter/docker-stacks/issues/915.
HEALTHCHECK CMD pgrep "jupyter" > /dev/null || exit 1
```