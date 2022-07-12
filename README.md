# OpenEmbedded/Yocto layer for NVIDIA Clara Holoscan MGX

This layer adds OpenEmbedded recipes and sample build configurations to build
BSPs for NVIDIA Clara Holoscan MGX Developer Kits that feature support for
discrete GPUs (dGPU), AJA Video Systems I/O boards, and the Clara Holoscan
Embedded SDK. These BSPs are built on a developer's host machine and are then
flashed onto a Clara Holoscan MGX Developer Kit using provided scripts.

This is an add-on layer to the [meta-tegra](https://github.com/OE4T/meta-tegra)
BSP layer with additions to enable the discrete GPU (dGPU) and other hardware
drivers and toolkits that are used by the Clara Holoscan Embedded SDK.

## Supported Boards

* Clara AGX Developer Kit (clara-agx-xavier-devkit)

## System Requirements

Building a BSP for Clara Holoscan MGX requires a significant amount of system
resources. Available disk space is the only strict requirement that must be
met, with **200GB of free disk space required for an MGX build** using the
default configuration as described in this document. It is recommended,
however, that the development system have many CPU cores, a fast internet
connection, and a large amount of memory and disk bandwidth in order to
minimize the amount of time that is required to build the BSP.

For reference, using this system:

* CPU: Intel Core i7-7800X, 6 Cores @ 3.50GHz
* RAM: 32GB DDR4-2133
* Disk: Samsung EVO 870 2TB SSD
* Network: 1 Gigabit Fiber Internet
* Operating System: Ubuntu 18.04

A complete MGX build using the `core-image-x11` target and the default
package configuration (including CUDA, TensorRT, and Holoscan SDK) required:

* Build Time: 2 hours and 35 minutes
* Package Downloads: 22GB
* Disk Space Used: 167GB

## Development Environment Options

There are two options available to set up a development environment and start
building Holoscan MGX BSP images using this layer. The first sets up a
traditional local build environment in which all dependencies are fetched and
installed manually by the developer directly on their host machine. The second
uses a Holoscan MGX Build Container that is provided by NVIDIA which contains
all of the dependencies and configuration scripts such that the entire process
of building and flashing a BSP can be done with just a few simple commands.

### 1. Local Build Environment

This section outlines what is needed to use this layer for a local build
environment in which all dependencies are manually downloaded and installed
on the host machine.

#### Dependencies

Unless otherwise stated, the following dependencies should all be cloned into
the same working directory that this `meta-tegra-clara-holoscan-mgx` repo
has been cloned into.

Also note that these dependencies are being actively developed, so
compatibility can not be guaranteed when using the latest versions. Each
dependency below has a commit ID that has been verified to work with this
layer and thus should be used to ensure the build completes. To use these
commit IDs, change into the directory that a dependency has been cloned
into and then run `git checkout {commit id}`.

* #### BitBake: https://github.com/openembedded/bitbake (commit: `f89a3515`)

    The BitBake task execution engine is used for all Holoscan MGX builds.

    See the [Yocto Project System Requirements](https://docs.yoctoproject.org/ref-manual/system-requirements.html#required-packages-for-the-build-host)
    for the most up to date list of requirements that are needed to use BitBake.
    Specifically, see the `Essentials: Packages needed to build an image on a
    headless system` section for your particular operating system. For Ubuntu
    and Debian operating systems, this list is currently as follows:

    ```sh
    sudo apt install gawk wget git diffstat unzip texinfo gcc build-essential \
         chrpath socat cpio python3 python3-pip python3-pexpect xz-utils \
         debianutils iputils-ping python3-git python3-jinja2 libegl1-mesa \
         libsdl1.2-dev pylint3 xterm python3-subunit mesa-common-dev zstd \
         liblz4-tool
    ```

* #### OpenEmbedded Core: https://github.com/openembedded/openembedded-core (commit: `95cc2d04`)

    The OpenEmbedded Core layer provides the core metadata and script that is
    used to setup the build environment. To setup the build environment (which
    must be done in any shell that will be used to run `bitbake`),
    run the `oe-init-build-env` script:

    ```sh
    $ source openembedded-core/oe-init-build-env
    ```

* #### Additional Metadata Layers

    The following additional layers are required and must be added to the
    `BBLAYERS` list (along with this `meta-tegra-clara-holoscan-mgx` layer
    itself) in the `build/conf/bblayers.conf` configuration file that is
    created by the `oe-init-build-env` script. Note that the paths in this
    configuration file must be full paths; see the template file in
    `meta-tegra-clara-holoscan-mgx/env/templates/conf/bblayers.conf` for an
    example when these layers are cloned into a `/workspace` root directory.
    Also note that this `meta-tegra-clara-holoscan-mgx` layer must be listed
    before the `meta-tegra` layer in `bblayers.conf`.

    * **meta-openembedded/meta-oe**: https://github.com/openembedded/meta-openembedded **(commit: `2d4090a7`)**
    * **meta-tegra**: https://github.com/OE4T/meta-tegra **(commit: `515f4005`)**

* #### Proprietary NVIDIA Binary Packages

    Some of the recipes contained in this layer depend on proprietary NVIDIA
    binary packages that can only be downloaded from the NVIDIA developer
    website using an NVIDIA developer account, and so these packages can not be
    automatically downloaded by the bitbake build process. If these components
    are to be included in the final BSP image, the binary packages must be
    manually downloaded and placed into the corresponding recipe directory
    before building.

    The current list of manually downloaded packages is as follows:

    * #### cuDNN (8.2.4.15)

      Download: https://developer.nvidia.com/compute/machine-learning/cudnn/secure/8.2.4/11.4_20210831/cudnn-11.4-linux-aarch64sbsa-v8.2.4.15.tgz  
      Local Destination: `meta-tegra-clara-holoscan-mgx/recipes-devtools/cudnn/files/cudnn-11.4-linux-aarch64sbsa-v8.2.4.15.tgz`

    * #### TensorRT (8.2.1.8)

      Download: https://developer.nvidia.com/compute/machine-learning/tensorrt/secure/8.2.1/tars/tensorrt-8.2.1.8.ubuntu-20.04.aarch64-gnu.cuda-11.4.cudnn8.2.tar.gz  
      Local Destination: `meta-tegra-clara-holoscan-mgx/recipes-devtools/tensorrt/files/TensorRT-8.2.1.8.Ubuntu-20.04.aarch64-gnu.cuda-11.4.cudnn8.2.tar.gz`

    * #### GXF (2.4.2)

      Download: https://catalog.ngc.nvidia.com/orgs/nvidia/teams/clara-holoscan/resources/gxf_arm64_holoscan_sdk  
      Local Destination: `meta-tegra-clara-holoscan-mgx/recipes-devtools/gxf/files/gxf_2.4.2_20220516_f90116f2_holoscan-sdk_arm64.tar.gz`

      > Note that when GXF is downloaded from NGC it will download a ZIP archive
      > named `files.zip` that contains the above `tar.gz` file; extract this
      > `tar.gz` file from the archive and move it to the destination given
      > above.

    * #### Holoscan SDK (0.2.0)

      Endoscopy Sample Data: https://catalog.ngc.nvidia.com/orgs/nvidia/teams/clara-holoscan/resources/holoscan_endoscopy_sample_data  
      Local Destination: `meta-tegra-clara-holoscan-mgx/recipes-devtools/holoscan/files/holoscan_endoscopy_data.zip`

      Ultrasound Sample Data: https://catalog.ngc.nvidia.com/orgs/nvidia/teams/clara-holoscan/resources/holoscan_ultrasound_sample_data  
      Local Destination: `meta-tegra-clara-holoscan-mgx/recipes-devtools/holoscan/files/holoscan_ultrasound_data.zip`

      > Note that when downloading these resources from NGC they will be
      > downloaded using the name `files.zip`, so they will need to be renamed
      > when they are moved to the correct destinations given above.

#### Build Configuration

To configure a Holoscan MGX BSP, the `MACHINE` setting in the
`build/conf/local.conf` configuration file that is initially generated by
`oe-init-build-env` must be changed to one of the boards supported by this
layer (see above), and the dGPU configuration must be enabled by including
`conf/tegra-dgpu.conf`:

```
MACHINE ??= "clara-agx-xavier-devkit"
require conf/tegra-dgpu.conf
```

Additional components from this layer can then be added to the BSP by appending
them to `CORE_IMAGE_EXTRA_INSTALL`. For example, to install CUDA, TensorRT,
and the Holoscan Embedded SDK, add the following:

```
CORE_IMAGE_EXTRA_INSTALL:append = " \
    cuda-toolkit \
    tensorrt \
    holoscan-sdk \
"
```

A template configuration file that does the above is provided in
`env/templates/conf/local.conf` and can be used as-is to replace the initial
`local.conf` file that was generated by `oe-init-build-env`. This template
also includes additional documentation about components and features provided by
this layer such as enabling an AJA Video I/O device. This additional
documentation can be seen by scrolling to the `BEGIN NVIDIA CONFIGURATION`
section at the bottom of the file.

##### Adding Keyboard and Mouse Drivers

The default build has support for standard wired keyboards and mice, but if you
are using a wireless device and/or one with more advanced features then it may
be required to add additional drivers to your image. For example, to enable a
Logitech wireless keyboard or mouse, the following kernel modules are needed:

```
CORE_IMAGE_EXTRA_INSTALL:append = " \
    kernel-module-hid-logitech-dj \
    kernel-module-hid-logitech-hidpp \
"
```

If you are unsure what drivers are needed, one method of determining what is
needed is to connect the devices to your host development machine and look to
see which drivers were loaded using `lsmod`.

##### Enabling AJA Video Devices

To enable support for AJA Video I/O devices, the AJA NTV2 kernel module can be
built into the image by adding the `kernel-module-ajantv2` component to
`CORE_IMAGE_EXTRA_INSTALL` as described above:

```
CORE_IMAGE_EXTRA_INSTALL:append = " kernel-module-ajantv2"
```

This will build the kernel module but it will not load the module
automatically, and so the module would need to be loaded every time the device
boots with `insmod`. To load the module and initialize the AJA devices
automatically when the device boots, the `ajantv2` module can be added to
`KERNEL_MODULE_AUTOLOAD` using the following:

```
KERNEL_MODULE_AUTOLOAD:append = " ajantv2"
```

#### Building and Flashing

Building a BSP is done with `bitbake`; for example, to build a `core-image-x11`
image, use the following:

```sh
$ bitbake core-image-x11
```

> Note: If the `bitbake` command is not found, ensure that the current shell
> has been initialized using `source openembedded-core/oe-init-build-env`. This
> script will add the required paths to the `PATH` environment variable so that
> the `bitbake` command can be run from any directory.

Using the configuration described above, this will build the BSP image and write
the output to

```
build/tmp-glibc/deploy/images/clara-agx-xavier-devkit/core-image-x11-clara-agx-xavier-devkit.tegraflash.tar.gz
```

The above file can then be extracted and the `doflash.sh` script that it
includes can be used to flash the device while it is in recovery mode and
connected to the host via the USB-C debug port:

> To put a Clara AGX Developer Kit into recovery mode, first remove the
> left-hand side cover to expose the recover and reset buttons; then while the
> unit is powered on, press the recovery and reset buttons, then release both
> buttons. For more information see the [Clara AGX Developer Kit User Guide](https://developer.nvidia.com/clara-agx-developer-kit-user-guide).

```sh
$ tar -xf ${image_path}
$ sudo ./doflash.sh
```

Once flashed, the Clara AGX Developer Kit can then be disconnected from the
host system and booted. This Holoscan MGX layer currently only supports the
RTX 6000 dGPU, so the display must also be connected to one of the DisplayPort
ports on the RTX 6000 in order for the display to work. During boot you will see
a black screen with only a cursor for a few moments before an X11 terminal
appears (no additional GUI will appear).

#### Running the Holoscan Embedded SDK Sample Applications

When the `holoscan-sdk` component is installed, the Holoscan SDK extensions and
sample applications will be installed into the image in the `/workspace`
directory. To run a sample application on the flashed device, navigate to this
directory and launch the corresponding script. For example, the following runs
the endoscopy instrument tracking application using sample recorded video data:

```sh
$ cd /workspace
$ ./tracking_replayer
```

Note that the first execution of the samples will rebuild the model engine files
and it will take a few minutes before the application fully loads. These engine
files are then cached and will significantly reduce launch times for successive
executions.

### 2. Holoscan MGX Build Container

Instead of downloading and installing all of the build tools, dependencies, and
proprietary binaries manually, NVIDIA also provides a [Holoscan MGX Build
Container](https://catalog.ngc.nvidia.com/orgs/nvidia/teams/clara-holoscan/containers/holoscan-mgx-oe-builder)
on the [NVIDIA GPU Cloud (NGC)](https://catalog.ngc.nvidia.com/) website.  This
container image includes all of the tools and dependencies that are needed
either within the container or as part of a setup script that initializes a
local build tree, and it simplifies the process such that building and flashing
a Holoscan MGX BSP can be done in just a few simple commands. See
[env/README.md](env/README.md) for documentation on the Holoscan MGX Build
Container.

> Note: the `env` directory in this repository contains the scripts and
> `Dockerfile` that are used to build the Holoscan MGX Build Container image,
> and can even be used to build the container image locally if one so desires.
