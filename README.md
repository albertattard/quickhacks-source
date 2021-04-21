# Quickhacks

```console
$ docker run --rm -d \
    --name quickhacks \
    --volume="$(pwd):/srv/jekyll" \
    --volume="$(pwd)/vendor/bundle:/usr/local/bundle" \
    --env JEKYLL_ENV=development \
    -p 4000:4000 \
    jekyll/jekyll:4.0 jekyll serve
```
