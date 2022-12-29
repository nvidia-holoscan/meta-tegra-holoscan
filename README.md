# OpenEmbedded/Yocto layer for NVIDIA Holoscan

This layer adds OpenEmbedded recipes and sample build configurations to build
BSPs for NVIDIA Holoscan Developer Kits that feature support for discrete GPUs
(dGPU), Rivermax, AJA Video Systems I/O boards, and the NVIDIA Holoscan SDK.
These BSPs are built on a developer's host machine and are then flashed onto a
Holoscan Developer Kit using provided scripts.

This is an add-on layer to the [meta-tegra](https://github.com/OE4T/meta-tegra)
BSP layer with additions to enable the discrete GPU (dGPU) and other hardware
drivers and toolkits that are used by the NVIDIA Holoscan SDK.

## Supported Boards

* Clara AGX Developer Kit (clara-agx-xavier-devkit)
* NVIDIA IGX Orin Developer Kit (holoscan-devkit)

## System Requirements

Building a BSP for NVIDIA Holoscan requires a significant amount of system
resources. Available disk space is the only strict requirement that must be
met, with **a minimum of 250GB of free disk space required for a build** using
the default configuration as described in this document. It is recommended,
however, that the development system have many CPU cores, a fast internet
connection, and a large amount of memory and disk bandwidth in order to
minimize the amount of time that is required to build the BSP.

For example, on a system with the following specifications:

* CPU: Intel Core i7-7800X, 6 Cores @ 3.50GHz
* RAM: 32GB DDR4-2133
* Disk: Samsung EVO 870 2TB SSD
* Network: 1 Gigabit Fiber Internet
* Operating System: Ubuntu 18.04

a complete build using the `core-image-x11` target and the default
package configuration (including CUDA, TensorRT, and Holoscan SDK) takes:

* Build Time: 3 hours and 5 minutes
* Package Downloads: 22GB
* Disk Space Used: 193GB

## Build Environment Options

There are two options available to set up a build environment and start
building Holoscan BSP images using this layer. The first sets up a
traditional local build environment in which all dependencies are fetched and
installed manually by the developer directly on their host machine. The second
uses a Holoscan OpenEmbedded/Yocto Build Container that is provided by NVIDIA
which contains all of the dependencies and configuration scripts such that the
entire process of building and flashing a BSP can be done with just a few simple
commands.

### 1. Local Build Environment

This section outlines what is needed to use this layer for a local build
environment in which all dependencies are manually downloaded and installed
on the host machine.

#### Dependencies

Unless otherwise stated, the following dependencies should all be cloned into
the same working directory that this `meta-tegra-holoscan` repo has been cloned
into.

Also note that these dependencies are being actively developed, so
compatibility can not be guaranteed when using the latest versions. Each
dependency below has a commit ID that has been verified to work with this
layer and thus should be used to ensure the build completes. To use these
commit IDs, change into the directory that a dependency has been cloned
into and then run `git checkout {commit id}`.

* #### Poky: https://git.yoctoproject.org/poky/ (commit: `2e79b199`, tag: `kirkstone-4.0.5`)

    The Poky Build Tool and Metadata for the Yocto Project.

    See the [Yocto Project System Requirements](https://docs.yoctoproject.org/ref-manual/system-requirements.html#required-packages-for-the-build-host)
    for the most up to date list of requirements that are needed for Yocto.
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

* #### Additional Metadata Layers

    The following additional layers are required and must be added to the
    `BBLAYERS` list (along with this `meta-tegra-holoscan` layer itself) in the
    `build/conf/bblayers.conf` configuration file that is created by the
    `oe-init-build-env` script. Note that the paths in this configuration file
    must be full paths; see the template file in
    `meta-tegra-holoscan/env/templates/conf/bblayers.conf` for an example when
    these layers are cloned into a `/workspace` root directory.

    Note that the repo used here for the `meta-tegra` layer is a fork of the
    official `meta-tegra` repo found at https://github.com/OE4T/meta-tegra.
    Changes from this fork will be upstreamed to the official repo when
    appropriate, but this fork may otherwise contain Holoscan-specific changes
    that aren't suitable for the upstream repo.

    * **meta-openembedded/meta-oe**: https://github.com/openembedded/meta-openembedded **(commit: `a8055484`)**
    * **meta-tegra**: https://github.com/nvidia-holoscan/meta-tegra **(commit: `05b5583c`)**

* #### Proprietary NVIDIA Binary Packages

    Some of the recipes contained in this layer depend on proprietary NVIDIA
    binary packages that can only be downloaded from the NVIDIA developer
    website using an NVIDIA developer account, and so these packages can not be
    automatically downloaded by the bitbake build process. If these components
    are to be included in the final BSP image, the binary packages must be
    manually downloaded and placed into the corresponding recipe directory
    before building.

    The current list of manually downloaded packages is as follows:

    * #### cuDNN (8.5.0.96)

      Download: https://developer.nvidia.com/compute/cudnn/secure/8.5.0/local_installers/11.7/cudnn-linux-sbsa-8.5.0.96_cuda11-archive.tar.xz  
      Local Destination: `meta-tegra-holoscan/recipes-devtools/cudnn/files/cudnn-linux-sbsa-8.5.0.96_cuda11-archive.tar.xz`

    * #### TensorRT (8.4.3.1)

      Download: https://developer.nvidia.com/compute/machine-learning/tensorrt/secure/8.4.3/tars/tensorrt-8.4.3.1.ubuntu-20.04.aarch64-gnu.cuda-11.6.cudnn8.4.tar.gz  
      Local Destination: `meta-tegra-holoscan/recipes-devtools/tensorrt/files/TensorRT-8.4.3.1.Ubuntu-20.04.aarch64-gnu.cuda-11.6.cudnn8.4.tar.gz`

    * #### GXF (2.5.0)

      Download: https://catalog.ngc.nvidia.com/orgs/nvidia/teams/clara-holoscan/resources/gxf_arm64_holoscan_sdk  
      Local Destination: `meta-tegra-holoscan/recipes-devtools/gxf/files/gxf_22.11_20221116_8fbe43cb_holoscan-sdk_arm64.tar.gz`

      > **_Note:_** When GXF is downloaded from NGC it will download a ZIP
      > archive named `files.zip` that contains the above `tar.gz` file; extract
      > this `tar.gz` file from the archive and move it to the destination given
      > above.

    * #### Holoscan SDK (0.4.0)

      Endoscopy Sample Data (20221121): https://catalog.ngc.nvidia.com/orgs/nvidia/teams/clara-holoscan/resources/holoscan_endoscopy_sample_data  
      Local Destination: `meta-tegra-holoscan/recipes-devtools/holoscan/files/holoscan_endoscopy_data_20221121.zip`

      Ultrasound Sample Data (20220608): https://catalog.ngc.nvidia.com/orgs/nvidia/teams/clara-holoscan/resources/holoscan_ultrasound_sample_data  
      Local Destination: `meta-tegra-holoscan/recipes-devtools/holoscan/files/holoscan_ultrasound_data_20220608.zip`

      Multi-AI Sample Data (20221201): https://catalog.ngc.nvidia.com/orgs/nvidia/teams/clara-holoscan/resources/holoscan_multi_ai_ultrasound_sample_data  
      Local Destination: `meta-tegra-holoscan/recipes-devtools/holoscan/files/holoscan_multi_ai_ultrasound_data_20221201.zip`

      > **_Note:_** When downloading these resources from NGC they will be
      > downloaded using the name `files.zip`, so they will need to be renamed
      > when they are moved to the correct destinations given above.

    * #### Rivermax SDK (1.20.10)

      Download: https://developer.nvidia.com/networking/secure/rivermax-linux-sdk/installation-package/version-1.20.x/1.20/rivermax_ubuntu2004_1.20.10.tar.gz  
      Local Destination: `meta-tegra-holoscan/recipes-connectivity/rivermax/files/rivermax_ubuntu2004_1.20.10.tar.gz`

#### iGPU and dGPU Configurations

This layer provides both iGPU and dGPU support for the Holoscan platforms
via the `conf/holoscan-igpu.conf` and `conf/holoscan-dgpu.conf` configuration
files, respectively.

When using the iGPU configuration, the majority of the runtime components come
from the standard Tegra packages used by the `meta-tegra` layer. When using the
dGPU configuration, some of the Tegra packages are overridden with drivers and
binary packages defined by this recipe layer that are needed to support the dGPU.

The versions of the components used for iGPU and dGPU mode differ slightly, as
given in the following table:

| Component       | iGPU       | dGPU             |
| --------------- | ---------- | ---------------- |
| Display Drivers | L4T 35.1.0 | OpenRM 515.65.01 |
| CUDA            | 11.4.14    | 11.7.1           |
| cuDNN           | 8.4.1.50   | 8.5.0.96         |
| TensorRT        | 8.4.1      | 8.4.3.1          |

#### Build Configuration

To configure a Holoscan BSP, the `MACHINE` setting in the
`build/conf/local.conf` configuration file that is initially generated by
`oe-init-build-env` must be changed to one of the boards supported by this
layer (see above), and the iGPU or dGPU configuration must be selected by
including either `conf/holoscan-igpu.conf` or `conf/holoscan-dgpu.conf`,
respectively:

```
MACHINE ??= "holoscan-devkit"
require conf/holoscan-dgpu.conf
```

> **_NOTE:_** If the configuration is switched between iGPU and dGPU, the
> graphics driver packages need to be cleaned before building the BSP in order
> to prevent file conflict errors. To clean these packages, issue these
> commands:
>
> ```sh
> $ bitbake nvidia-display-driver -c clean
> $ bitbake nvidia-open-gpu-kernel-modules -c clean
> ```

Additional components from this layer can then be added to the BSP by appending
them to `CORE_IMAGE_EXTRA_INSTALL`. For example, to install the Holoscan SDK
and its dependencies, add the following:

```
CORE_IMAGE_EXTRA_INSTALL:append = " \
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

If you are unsure what drivers are needed, the generic `kernel-modules` package
can be added to the install list instead to install all of the upstream kernel
modules. This will increase build time and image size, so it is suggested to
install just the specific kernel modules that are actually needed if possible.

##### Enabling Rivermax

Rivermax support is added by including the `conf/rivermax.conf` file:

```
require conf/rivermax.conf
```

Using Rivermax requires a valid license file, and the `rivermax-license` recipe
is responsible for installing the Rivermax license file provided by
`meta-tegra-holoscan/recipes-connectivity/rivermax/files/rivermax.lic`.
This file is an empty (invalid) license file by default, and must be replaced
with a valid Rivermax license file in order to fully enable Rivermax support.
Alternatively, the license file can be copied to the device at runtime by
replacing `/opt/mellanox/rivermax/rivermax.lic`.

##### Enabling AJA Video Devices

To enable support for AJA Video I/O devices, the AJA NTV2 kernel module can be
built into the image by adding the `kernel-module-ajantv2` component to
`CORE_IMAGE_EXTRA_INSTALL` as described above:

```
CORE_IMAGE_EXTRA_INSTALL:append = " kernel-module-ajantv2"
```

#### Building and Flashing

Building a BSP is done with `bitbake`; for example, to build a `core-image-x11`
image, use the following:

```sh
$ bitbake core-image-x11
```

> **_Note:_** If the `bitbake` command is not found, ensure that the current
> shell has been initialized using `source poky/oe-init-build-env`.
> This script will add the required paths to the `PATH` environment variable so
> that the `bitbake` command can be run from any directory.

> **_Note:_** If the build fails due to unavailable resource errors, try the
> build again. Builds are extremely resource-intensive, and having a number of
> particularly large tasks running in parallel can exceed even 32GB of system
> memory usage. Repeating the build can often reschedule the tasks so that
> they can succeed. If errors are still encountered, try lowering the value
> of [BB_NUMBER_THREADS](https://docs.yoctoproject.org/ref-manual/variables.html#term-BB_NUMBER_THREADS)
> in `build/conf/local.conf` to reduce the maximum number of tasks that BitBake
> should run in parallel at any one time.

> **_Note:_** Race conditions have been encountered that lead to errors during
> the `do_rootfs` stage of the build such as `Couldn't find anything to satisfy
> 'rivermax'`. If this occurs, try cleaning the failing package, build the
> package by itself, then build the image again. For example, if the `rivermax`
> package fails to install, try the following:
>
> ```sh
> $ bitbake rivermax -c cleansstate
> $ bitbake rivermax
> $ bitbake core-image-x11
> ```

Using the configuration described above, this will build the BSP image and write
the output to

```
build/tmp/deploy/images/holoscan-devkit/core-image-x11-holoscan-devkit.tegraflash.tar.gz
```

The above file can then be extracted and the `doflash.sh` script that it
includes can be used to flash the device while it is in recovery mode and
connected to the host via the USB-C debug port:

```sh
$ tar -xf ${image_path}
$ sudo ./doflash.sh
```

> **_Note:_** For instructions on how to put the developer kit into recovery
> mode, see the developer kit user guide:
>  - [Clara AGX Developer Kit User Guide](https://developer.nvidia.com/clara-agx-developer-kit-user-guide).
>  - [IGX Orin Developer Kit User Guide](https://developer.nvidia.com/igx-orin-developer-kit-user-guide).

Once flashed, the Holoscan Developer Kit can then be disconnected from the
host system and booted. The display connection that is used depends on the GPU
configuration that was used for the build: the iGPU configuration uses the
onboard HDMI or DisplayPort connection on the developer kit, while the dGPU
configuration uses one of the DisplayPort connections on the discrete GPU.
During boot you will see a black screen with only a cursor for a few moments
before an X11 terminal appears (no additional GUI will appear).

> **_Note:_** If the monitor never receives a signal there may be an issue
> configuring the monitor during the initial boot process. If this occurs,
> the `xrandr` utility can generally be used from a remote shell to display
> the available monitor modes and to select a current mode. For example, to
> configure a 1920x1080 display connected to the HDMI-0 output, use the
> following:
>
> ```sh
> $ export DISPLAY=:0
> $ xrandr --output HDMI-0 --mode 1920x1080
> ```

#### Running the Holoscan SDK Sample Applications

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

### 2. Holoscan OpenEmbedded/Yocto Build Container

Instead of downloading and installing all of the build tools, dependencies, and
proprietary binaries manually, NVIDIA also provides a [Holoscan OpenEmbedded/Yocto Build
Container](https://catalog.ngc.nvidia.com/orgs/nvidia/teams/clara-holoscan/containers/holoscan-oe-builder)
on the [NVIDIA GPU Cloud (NGC)](https://catalog.ngc.nvidia.com/) website.  This
container image includes all of the tools and dependencies that are needed
either within the container or as part of a setup script that initializes a
local build tree, and it simplifies the process such that building and flashing
a Holoscan BSP can be done in just a few simple commands. See
[env/README.md](env/README.md) for documentation on the Holoscan OpenEmbedded/Yocto
Build Container.

> Note: the `env` directory in this repository contains the scripts and
> `Dockerfile` that are used to build the Holoscan OpenEmbedded/Yocto Build
> Container image, and can even be used to build the container image locally if
> one so desires.
