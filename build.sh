#!/bin/bash

#docker run --rm \
#    --volume="$(pwd):/srv/jekyll" \
#    jekyll/builder:4.2.0 /bin/bash -c "gem install bundler && jekyll build && bundle exec just-the-docs rake search:init"

export DIRECTORY="../quickhacks"

rm -rf "${DIRECTORY}"
git clone "git@github.com:albertattard/quickhacks.git" "${DIRECTORY}"

rm -rf "${DIRECTORY}/assets"
rm -rf "${DIRECTORY}/docs"
find "${DIRECTORY}" -maxdepth 1 -type f -delete

cp -R "_site/" "${DIRECTORY}/"
