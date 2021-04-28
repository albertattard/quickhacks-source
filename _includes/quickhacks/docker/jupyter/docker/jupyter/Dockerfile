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
