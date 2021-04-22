# Quickhacks

Please visit: [https://albertattard.github.io/quickhacks/](https://albertattard.github.io/quickhacks/) to see the
Quickhacks Github Pages.

## Build and run the application locally

Run the site locally using docker

```console
$ docker run --rm \
    --name quickhacks \
    --volume="$(pwd):/srv/jekyll" \
    --volume="$(pwd)/docker/volume/vendor/bundle:/usr/local/bundle" \
    --env JEKYLL_ENV=development \
    -p 4000:4000 \
    jekyll/jekyll:4.2.0 jekyll serve --host 0.0.0.0 --incremental
```

Stop the docker container

```console
$ docker stop quickhacks
```

Build the site locally

```console
$ docker run --rm \
    --volume="$(pwd):/srv/jekyll" \
    jekyll/builder:4.2.0 /bin/bash -c "gem install bundler && jekyll build && bundle exec just-the-docs rake search:init"
```
