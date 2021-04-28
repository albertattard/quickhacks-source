# docker build . -t postgresql-client:local
# docker run -it postgresql-client:local /bin/bash
# PGPASSWORD=vNCMQIJc%4PM_WuR psql -h quickhacks-db.chwzrdwn3ehs.eu-central-1.rds.amazonaws.com -d quickhacks_db -U quickhacks
FROM ubuntu:20.04
RUN apt-get update && apt-get install -y postgresql-client-12
