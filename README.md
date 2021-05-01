# Quickhacks

Please visit: [https://albertattard.github.io/quickhacks/](https://albertattard.github.io/quickhacks/) to see the
Quickhacks Github Pages.

I am using two custom plugins, saved under the `_plugins` folder, that include raw files. These are not supported by
GitHub pages and had to created two repositories, one for the source (this repository) and another that will host the
generated pages ([https://github.com/albertattard/quickhacks/](https://github.com/albertattard/quickhacks/)). Modified
the GitHub action ([.github/workflows/build.yml](.github/workflows/build.yml)) to copy the generated site to
the [https://github.com/albertattard/quickhacks/](https://github.com/albertattard/quickhacks/) repository automatically.

## Build and run the application locally

1. Run the site locally

   ```console
   $ ./run.sh
   ```

1. Build the site locally

   ```console
   $ ./build.sh
   ```
