#!/bin/bash

docker run --rm \
  --name quickhacks \
  --volume="$(pwd):/srv/jekyll" \
  --volume="$(pwd)/docker/volume/vendor/bundle:/usr/local/bundle" \
  --env JEKYLL_ENV=development \
  -p 4000:4000 \
  jekyll/jekyll:4.2.0 jekyll serve --host 0.0.0.0
