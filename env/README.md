# OpenEmbedded/Yocto Build Container for NVIDIA Holoscan

This container image contains runtime dependencies, scripts, and the
NVIDIA-proprietary binary packages that are required to build an OpenEmbedded
BSP image for NVIDIA Holoscan Developer Kits with dGPU support.

The following documentation provides information specific to the usage of the
Holoscan Build Container, and may be missing information from the main
documentation that may be useful to know when configuring or using the BSP.
Please see the main [README](../README.md) file for additional documentation.

> Note: the main `README` file can be found at
> `meta-tegra-holoscan/README.md` after following the `1. Setting up
> the Local Development Environment` section, below.

Also note that building a BSP for NVIDIA Holoscan requires a significant
amount of resources, and at least **250GB of free disk space is required to
build**. See the `System Requirements` section in the main
[README](../README.md) for more details.

## 1. Setting up the Local Development Environment

While it would be possible to build an OE image directly from source that is
stored within a container, doing so would mean that any additions or
modifications to the build recipes would also only live inside the running
container and so would be lost whenever the container terminates. Instead, this
container operates by initially setting up a local host volume with all of the
recipes, dependencies, and initial configuration that is needed for the BSP
build such that all of the recipes, configuration, and build cache is stored in
persistent storage on the host and thus is not limited to the lifespan of a
single container runtime.

In order to perform this initial setup navigate to the directory into which you
would like to initialize the development environment and run the following
(making sure `IMAGE` matches the name and tag of this container image):

```sh
$ export IMAGE=nvcr.io/nvidia/clara-holoscan/holoscan-oe-builder:v0.4.0
$ docker run -it --rm -v $(pwd):/workspace --network host ${IMAGE} setup.sh ${IMAGE} $(id -u) $(id -g)
```

This setup processes initializes the following:

1. The OE recipes and dependencies in these folders:

   ```
   poky
   meta-openembedded
   meta-virtualization
   meta-tegra
   meta-tegra-holoscan
   ```

2. A sample build configuration in the `build` folder.

3. Wrapper script `bitbake.sh`, which runs a build container and passes the
   arguments to the script to the container's bitbake command.

4. A `flash.sh` script to flash the device with the built image.

5. A `.buildimage` file which contains the name of the container image.
   This is used by the `bitbake.sh` script and prevents the need to export
   an IMAGE environment variable anytime a build is performed.

## 2. Configure the Image

The OE image configuration file is created by the previous step and is written
to `build/conf/local.conf`. This file is based on the default `local.conf` that
is created by the Poky environment setup script (`oe-init-build-env`)
and has various NVIDIA configuration defaults and samples added to it.
For example, the `MACHINE` configuration in this file is set to
`igx-orin-devkit` and CUDA, TensorRT, Rivermax, and the Holoscan SDK are
installed by default. This configuration can be used as-is to build a BSP for
the IGX Orin Developer Kit, but it may be neccessary to add components to this
configuration to support additional hardware such as AJA video capture cards or
other non-standard peripherals like wireless keyboard or mouse drivers. See the
`Build Configuration` section in the main [README](../README.md) for more
details.

To see the additional configuration that is added to this file relative to the
standard OpenEmbedded `local.conf`, as well as some documentation as to what
additional components offered by this meta-tegra-holoscan layer may be enabled,
scroll down to the "BEGIN NVIDIA CONFIGURATION" section in this file.

## 3. Build the Image

Once the image has been configured in the local host development tree, the
container image is used again for the actual `bitbake` build process. This
can be done using the `bitbake.sh` build wrapper that is written to the
root of the development directory. This script simply runs the `bitbake`
process in the container and passes the arguments to the script to this
process. For example, to build a core Sato image, use the following:

```sh
$ ./bitbake.sh core-image-sato
```

> Note: If the build fails due to unavailable resource errors, try the build
> again. Builds are extremely resource-intensive, and having a number of
> particularly large tasks running in parallel can exceed even 32GB of system
> memory usage. Repeating the build can often reschedule the tasks so that
> they can succeed. If errors are still encountered, try lowering the value
> of [BB_NUMBER_THREADS](https://docs.yoctoproject.org/ref-manual/variables.html#term-BB_NUMBER_THREADS)
> in `build/conf/local.conf` to reduce the maximum number of tasks that BitBake
> should run in parallel at any one time.

Using the default configuration, the above script will build the BSP image and
write the final output to:

```
build/tmp/deploy/images/igx-orin-devkit/core-image-sato-igx-orin-devkit.tegraflash.tar.gz
```

## 4. Flash the Image

The `flash.sh` script can be used to flash the BSP image that is output by the
previous step onto the Holoscan Developer Kit hardware. For example, to flash the
`core-image-sato` image that was produced by the previous step, connect the
developer kit to the host via the USB-C debug port, put it into recovery
mode, then run:

```sh
$ ./flash.sh core-image-sato
```

> For instructions on how to put the developer kit into recovery mode, see the
> developer kit user guide:
>  - [Clara AGX Developer Kit User Guide](https://developer.nvidia.com/clara-agx-developer-kit-user-guide).
>  - [IGX Orin Developer Kit User Guide](https://developer.nvidia.com/igx-orin-developer-kit-user-guide).

Note that flashing the device will require root privileges and so you may be
asked for a sudo password by this script.

## 5. Running the Holoscan SDK Sample Applications

When the `holoscan-sdk` component is installed, the Holoscan SDK extensions and
sample applications will be installed into the image in the `/workspace`
directory. To run a sample application on the flashed device, navigate to this
directory and launch the corresponding script. For example, the following runs
the endoscopy instrument tracking application using sample recorded video data:

```sh
$ cd /workspace
$ ./apps/endoscopy_tool_tracking/cpp/tracking_replayer
```

Note that the first execution of the samples will rebuild the model engine files
and it will take a few minutes before the application fully loads. These engine
files are then cached and will significantly reduce launch times for successive
executions.
