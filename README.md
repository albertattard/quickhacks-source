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


```console
$ docker run --rm \
    --name quickhacks-builder \
    --volume="$(pwd):/srv/jekyll" \
    --env JEKYLL_ENV=production \
    jekyll/jekyll:4.0 jekyll build
```