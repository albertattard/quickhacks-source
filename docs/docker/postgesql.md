---
layout: default
title: PostgreSQL
parent: Docker
nav_order: 1
permalink: docs/docker/postgresql/
---

# PostgreSQL
{: .no_toc }

---

## Docker Compose

```yaml
version: "3.9"

services:
  db:
    container_name: "quickhacks-docker-postgresql-db"
    image: "postgres:13.2-alpine"
    ports:
      - "5432:5432"
    environment:
      POSTGRES_DB: "db-quickhacks"
      POSTGRES_PASSWORD: "db-password"
      POSTGRES_USER: "db-username"
    volumes:
      - "postgres-db:/var/lib/postgresql/data"
    healthcheck:
      test: [ "CMD", "pg_isready", "-U", "db-username" ]
      start_period: "5s"
      interval: "5s"
      retries: 5
    restart: "always"
    networks:
      - "quickhacks"

volumes:
  postgres-db:
    name: "quickhacks-docker-postgresql-db"
    driver: "local"
    driver_opts:
      type: "none"
      o: "bind"
      device: "./postgres"

networks:
  quickhacks:
    name: "quickhacks-docker-postgresql-network"
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
