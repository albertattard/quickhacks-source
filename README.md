# Quickhacks

Please visit: [https://albertattard.github.io/quickhacks/](https://albertattard.github.io/quickhacks/) to see the
Quickhacks Github Pages.

I am using two custom plugins, `_plugins`, that include raw files. These are not supported by GitHub pages and had to
created two repositories, one for the source (this repository) and another that will host the generated
pages ([https://github.com/albertattard/quickhacks/](https://github.com/albertattard/quickhacks/)). The `build.sh`
script, clones the repo and copies the changes automatically, but these changes need to be committed manually.

## Build and run the application locally

1. Run the site locally

   ```console
   $ ./run.sh
   ```

1. Build the site locally and copy the changes to the actual site

   ```console
   $ ./build.sh
   ```
