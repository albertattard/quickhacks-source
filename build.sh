#!/bin/bash

docker run --rm \
    --volume="$(pwd):/srv/jekyll" \
    jekyll/builder:4.2.0 /bin/bash -c "gem install bundler && jekyll build && bundle exec just-the-docs rake search:init"
