# Quickhacks

Run the site locally

```console
$ docker run --rm -d \
    --name quickhacks \
    --volume="$(pwd):/srv/jekyll" \
    --volume="$(pwd)/vendor/bundle:/usr/local/bundle" \
    --env JEKYLL_ENV=development \
    -p 4000:4000 \
    jekyll/jekyll:4.0 jekyll serve
```

Build the site locally

```console
$ docker run --rm \
    --volume="$(pwd):/srv/jekyll" \
    jekyll/builder:4.2.0 /bin/bash -c "gem install bundler && jekyll build && bundle exec just-the-docs rake search:init"
```
