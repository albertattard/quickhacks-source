FROM ubuntu:20.04
RUN apt-get update && apt-get install -y postgresql-client-12

ENV PGPORT=5439
ENV PGUSER="quickhack"
ENV PGDATABASE="quickhack_db"

ENTRYPOINT ["psql"]
