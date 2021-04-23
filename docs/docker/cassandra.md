---
layout: default
title: Cassandra
parent: Docker
nav_order: 1
permalink: docs/docker/cassandra/
---

# Cassandra

---

## Docker Compose

```yaml
version: "3.9"

services:
   db:
      container_name: "quickhacks-docker-cassandra-db"
      image: "cassandra:3.11.10"
      ports:
         - "7000:7000"
         - "7001:7001"
         - "9042:9042"
         - "7199:7199"
      volumes:
         - "cassandra-db:/var/lib/cassandra"
      healthcheck:
         test: [ "CMD-SHELL", "[ $$(nodetool statusgossip) = running ]" ]
         start_period: "5s"
         interval: "5s"
         retries: 5
      restart: "always"
      networks:
         - "lab"

volumes:
   cassandra-db:
      name: "quickhacks-docker-cassandra-db"
      driver: "local"
      driver_opts:
         type: "none"
         o: "bind"
         device: "./cassandra"

networks:
   lab:
      name: "quickhacks-docker-cassandra-network"
```

## Versions

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
