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
* NVIDIA IGX Orin Developer Kit ES (holoscan-devkit)
* NVIDIA IGX Orin Developer Kit (igx-orin-devkit)

## System Requirements

Building a BSP for NVIDIA Holoscan requires a significant amount of system
resources. Available disk space is the only strict requirement that must be
met, with **a minimum of 300GB of free disk space required for a build** using
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
* Disk Space Used: 265GB

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

* #### Poky: https://git.yoctoproject.org/poky/ (commit: `65dafea2`, tag: `kirkstone-4.0.7`)

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
         liblz4-tool git-lfs python3-typing-extensions python3-yaml
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

    | Layer | Repo | Commit |
    | -------------- | ---- | ------ |
    | meta-openembedded/meta-oe <br/> meta-openemdedded/meta-filesystems <br/> meta-openembedded/meta-networking <br/> meta-openembedded/meta-python | https://github.com/openembedded/meta-openembedded | `278ec081` |
    | meta-virtualization | https://git.yoctoproject.org/meta-virtualization | `9a94fa2a` |
    | meta-tegra | https://github.com/nvidia-holoscan/meta-tegra | `98fa4f70` |

* #### Proprietary NVIDIA Binary Packages

    Some of the recipes contained in this layer depend on proprietary NVIDIA
    binary packages that can only be downloaded from the NVIDIA developer
    website using an NVIDIA developer account, and so these packages can not be
    automatically downloaded by the bitbake build process. If these components
    are to be included in the final BSP image, the binary packages must be
    manually downloaded and placed into the corresponding recipe directory
    before building.

    The current list of manually downloaded packages is as follows:

    * #### Rivermax SDK (1.21.10)

      Download: https://developer.nvidia.com/downloads/remma-linu-sdkinstallation-packageversion-121121rivermaubuntu200412110targz  
      Local Destination: `meta-tegra-holoscan/recipes-connectivity/rivermax/files/rivermax_ubuntu2004_1.21.10.tar.gz`

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
| Display Drivers | L4T 35.3.1 | OpenRM 530.30.02 |
| CUDA            | 11.4.19    | 12.1.1           |
| cuDNN           | 8.6.0.166  | 8.9.0.131        |
| TensorRT        | 8.5.2      | 8.6.0.12         |

#### Build Configuration

To configure a Holoscan BSP, the `MACHINE` setting in the
`build/conf/local.conf` configuration file that is initially generated by
`oe-init-build-env` must be changed to one of the boards supported by this
layer (see above), and the iGPU or dGPU configuration must be selected by
including either `conf/holoscan-igpu.conf` or `conf/holoscan-dgpu.conf`,
respectively:

```
MACHINE ??= "igx-orin-devkit"
require conf/holoscan-dgpu.conf
```

> **_Note:_** If the configuration is switched between iGPU and dGPU, the
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

##### Adding Additional Kernel Modules

The machine configuration for a Holoscan devkit includes the minimal set of
kernel modules required to support the onboard components, but it does not
include modules to support additional peripherals such as USB cameras or
wireless keyboards and mice. If such peripherals will be used, it will be
required to add the corresponding kernel modules to the image to support these
devices. For example, to enable a Logitech wireless keyboard or mouse, the
following kernel modules are needed:

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
Note that the `kernel-modules` package has been added to the template
configuration in `env/templates/conf/local.conf` to improve the out-of-the-box
support for additional peripherals during the initial development phase.

##### Enabling PREEMPT_RT patch

`PREEMPT_RT` patch support for the Linux kernel is added by including the
`conf/rt-patch.conf` file in `build/conf/local.conf`, like the following line:

```
require conf/rt-patch.conf
```

The `PREEMPT_RT` patch is currently only supported with iGPU configuration,
enabling the `PREEMPT_RT` patch with dGPU configuration will lead to build 
failures. 

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

Building a BSP is done with `bitbake`; for example, to build a `core-image-sato`
image, use the following:

```sh
$ bitbake core-image-sato
```

> **_Note:_** If the `bitbake` command is not found, ensure that the current
> shell has been initialized using `source poky/oe-init-build-env`.
> This script will add the required paths to the `PATH` environment variable so
> that the `bitbake` command can be run from any directory.

> **_Note:_** For the list of different image targets that are available to build,
> see the [Yocto Project Images List](https://docs.yoctoproject.org/ref-manual/images.html).

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
> $ bitbake core-image-sato
> ```

Using the configuration described above, this will build the BSP image and write
the output to

```
build/tmp/deploy/images/igx-orin-devkit/core-image-sato-igx-orin-devkit.tegraflash.tar.gz
```

The above file can then be extracted and the `doflash.sh` script that it
includes can be used to flash the device while it is in recovery mode and
connected to the host via the USB-C debug port:

```sh
$ tar -xf ${image_path}
$ sudo ./doflash.sh
```

> **_Note:_** If the `doflash.sh` command fails due to a `No such file: 'dtc'`
> error, install the device tree compiler (`dtc`) using the following:
>
> ```sh
> $ sudo apt-get install device-tree-compiler
> ```

> **_Note:_** For instructions on how to put the developer kit into recovery
> mode, see the developer kit user guide:
>  - [Clara AGX Developer Kit User Guide](https://developer.nvidia.com/clara-agx-developer-kit-user-guide).
>  - [IGX Orin Developer Kit User Guide](https://developer.nvidia.com/igx-orin-developer-kit-user-guide).

Once flashed, the Holoscan Developer Kit can then be disconnected from the host
system and booted. A display, keyboard, and mouse should be attached to the
developer kit before it is booted. The display connection depends on the GPU
configuration that was used for the build: the iGPU configuration uses the
onboard Tegra display connection while the dGPU configuration uses one of the
connections on the discrete GPU. Please refer to the developer kit user guide
for diagrams showing the locations of these display connections. During boot
you will see a black screen with only a cursor for a few moments before an X11
terminal or GUI appears (depending on your image type).

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

#### Running the Holoscan SDK and HoloHub Applications

When the `holoscan-sdk` component is installed, the Holoscan SDK is installed
into the image in the `/opt/nvidia/holoscan` directory, with examples present in
the `examples` subdirectory. Due to relative data paths being used by the apps,
these examples should be run from the `/opt/nvidia/holoscan` directory. To run
the C++ version of an example, simply run the executable in the example's `cpp`
subdirectory:

```sh
$ cd /opt/nvidia/holoscan
$ ./examples/hello_world/cpp/hello_world
```

To run the Python version of an example, run the application in the example's
`python` subdirectory using `python3`:

```sh
$ cd /opt/nvidia/holoscan
$ python3 ./examples/hello_world/python/hello_world.py
```

When the `holohub-apps` component is installed, the HoloHub sample applications
are installed into the image in the `/opt/nvidia/holohub` directory, with the
applications present in the `applications` subdirectory. Due to relative data
paths being used by the apps, these applications should be run from the
`/opt/nvidia/holohub` directory. To run the C++ version of an application,
simply run the executable in the applications's `cpp` subdirectory:

```sh
$ cd /opt/nvidia/holohub
$ ./applications/endoscopy_tool_tracking/cpp/endoscopy_tool_tracking
```

To run the Python version of an application, run the application in the
`python` subdirectory using `python3`:

```sh
$ cd /opt/nvidia/holohub
$ python3 ./applications/endoscopy_tool_tracking/python/endoscopy_tool_tracking.py
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

> **_Note:_** the `env` directory in this repository contains the scripts and
> `Dockerfile` that are used to build the Holoscan OpenEmbedded/Yocto Build
> Container image, and can even be used to build the container image locally if
> one so desires.

## Debugging with GDB

Debugging applications on the target can be done using GDB either directly on
the target or remotely using a remote GDB connection. In either scenario, the
debugging tools and symbols can be included in the image by adding the
following to `build/conf/local.conf`:

```
EXTRA_IMAGE_FEATURES:append = " tools-debug dbg-pkgs"
```

Note that in both the local and remote debugging cases the GDB Text User
Interface (TUI) mode is enabled and can be entered by adding the `-tui`
argument to the `gdb` commands below, or toggled using the `C-x a` key binding
once GDB has been launched. For more information on debugging with GDB, see the
[Debugging with GDB](https://ftp.gnu.org/old-gnu/Manuals/gdb/html_chapter/gdb_toc.html)
and [GDB Text User Interface](https://ftp.gnu.org/old-gnu/Manuals/gdb/html_chapter/gdb_19.html#SEC197)
documentation.

### Local Debugging

For debugging locally on the device, the source code packages should also be
installed by adding the following:

```
EXTRA_IMAGE_FEATURES:append = " src-pkgs"
```

With the debugging tools, symbols, and source code installed on the device, an
application can be debugged locally by running `gdb [executable]`, e.g.:

```sh
$ cd /workspace
$ gdb ./apps/multiai/cpp/multiai
```

### Remote Debugging

#### 1. Installing the SDK on the Host

Debugging remotely requires the SDK for the image to be built and installed
on the host device from which debugging will be performed. To build the SDK
package for an image (e.g. `core-image-sato`), run the following:

```sh
$ bitbake core-image-sato -c populate_sdk
```

Once built, the script to install the SDK will be present in
`build/tmp/deploy/sdk`. To install the SDK, run the script that corresponds to
the image. For example, to install the `core-image-sato` SDK, run the following:

```sh
$ ./build/tmp/deploy/sdk/poky-glibc-x86_64-core-image-sato-armv8a-igx-orin-devkit-toolchain-4.0.7.sh
```

Follow the prompts to specify the install path for the SDK. The rest of these
instructions will assume that the default install path of `/opt/poky/4.0.7` is
used.

#### 2. Running the Remote Debugging Server on the Target

Launch the application on the target using the `gdbserver` command along with
the target's network address and port that should be used for the remote
debugging connection:

```sh
$ cd /workspace
$ gdbserver 192.168.0.100:1234 ./apps/multiai/cpp/multiai
Process ./apps/multiai/cpp/multiai created; pid = 1432
Listening on port 1234
```

#### 3. Connecting to the Remote Debugging Session

The SDK installed on the host includes an `environment-setup-armv8a-poky-linux`
script that must be sourced from any terminal before the SDK can be used:

```sh
$ source /opt/poky/4.0.7/environment-setup-armv8a-poky-linux
```

This environment provides the `$GDB` environment variable that points to the
GDB executable that must be used for the remote debugging. Launch this
`$GDB` executable then connect to the remote target using the
`target remote [ip]:[port]` command:

```sh
$ $GDB
GNU gdb (GDB) 11.2
...
(gdb) target remote 192.168.0.100:1234
Remote debugging using 192.168.0.100:1234
Reading /workspace/apps/multiai/cpp/multiai from remote target...
Reading symbols from target:/workspace/apps/multiai/cpp/multiai...
Reading /lib/ld-linux-aarch64.so.1 from remote target...
0x0000fffff7fd9d00 in _start () from target:/lib/ld-linux-aarch64.so.1
(gdb)
```

While the symbols will be loaded remotely from the target, the path to the
source code must be remapped to the local host path for the source using the
`set substitute-path` command. Assuming the path of the host build tree is in
`/holoscan`, this can be done with the following:

```sh
(gdb) set substitute-path /usr/src/debug /holoscan/build/tmp/work/armv8a_tegra234-poky-linux
```

At this point the symbols and source code should be available and remote
debugging can begin.

> **_Note:_** This example also assumes that the application being debugged was
> written to the `armv8a_tegra234-poky-linux` directory of the build tree. This
> may need to change if the application was written to another directory
> (e.g. `armv8a-poky-linux`).

## System Profiling with Nsight Systems

[NVIDIA Nsight Systems](https://developer.nvidia.com/nsight-systems) can be used
by installing the CLI on the target device in order to capture a runtime trace
of an application, which can then be loaded into the Nsight Systems UI on the
host machine to view the trace.

To install the Nsight Systems CLI on the Holoscan device, include the
`nsight-systems-cli` package in the image configuration (`local.conf`):

```
EXTRA_IMAGE_FEATURES:append = " nsight-systems-cli"
```

To install the Nsight Systems UI on the host machine, follow the [CUDA
installation guide](https://docs.nvidia.com/cuda/cuda-installation-guide-linux/index.html#package-manager-installation)
to setup the host package manager to download from the NVIDIA package
repository, then use it to install the corresponding `nsight-systems` package
that matches the version of the CLI that was installed onto the target. For
example, to install Nsight Systems 2023.1.2 on an Ubuntu 22.04 system, use the
following:

```sh
$ wget https://developer.download.nvidia.com/compute/cuda/repos/ubuntu2204/x86_64/cuda-keyring_1.0-1_all.deb
$ sudo dpkg -i cuda-keyring_1.0-1_all.deb
$ sudo apt-get update
$ sudo apt-get install nsight-systems-2023.1.2
```

> **_Note:_** To check which version of the CLI will be installed on the target,
> use the following `bitbake` command:
>
> ```sh
> $ bitbake nsight-systems-cli -e | grep ^PV= | cut -d'"' -f2 | cut -d'.' -f1,2,3
> 2023.1.2
> ```

For further instructions on how to use Nsight Systems, see the [Nsight Systems
User Guide](https://docs.nvidia.com/nsight-systems/UserGuide/index.html).

## Enabling Secure Boot

NVIDIA Jetson Linux platforms, including the Holoscan developer kits, provide
boot security with an on-die BootROM that authenticates boot codes such as BCT
and the bootloader using Public Key Cryptography (PKC) keys stored in
write-once-read-multiple hardware fuses. A Secure Boot Key (SBK) can also be
used to encrypt bootloader images. Enabling SBK is optional, but doing so
requires PKC to be enabled.

The root-of-trust that uses these hardware fuses for authentication ends at the
bootloader. After this, the UEFI bootloader uses the UEFI Security Keys scheme
to authenticate its payloads.

The mechanisms used to enable secure boot are documented in the
[Secure Boot](https://docs.nvidia.com/jetson/archives/r35.3.1/DeveloperGuide/text/SD/Security/SecureBoot.html)
section of the Jetson Linux Developer Guide, and additional Yocto/OE-specific
documentation is provided by the
[Secure Boot Support](https://github.com/OE4T/meta-tegra/wiki/Secure-Boot-Support-in-L4T-R35.2.1)
wiki on the [OE4T/meta-tegra](https://github.com/OE4T/meta-tegra) GitHub page.
Once these documents have been read, the following sections provide examples that
summarize the steps needed to enable PKC/SBK sigining and UEFI secure boot for
an IGX Orin Devkit.

> **_Note:_** The following sections are provided purely as examples, and
> should likely not be followed as-is to create, fuse, and enable encryption
> keys. In most cases OEMs will generate and maintain their own sets of keys
> that will be used for this purpose.

> **_WARNING:_** Burning fuses is a **one-time** operation, so be extremely
> careful. If something is done incorrectly during this process you could
> render the devkit completely and permanently unusable.

> **_WARNING:_** Once the fuses have been burned, be certain to keep the keys
> in a safe location as they will be needed every time the device is flashed.
> Losing the keys means that you will not be able to flash the device again.

The following examples use these three environment variables that must be set
for the commands to work:

* `BUILD_ROOT`: Set to the root of your build tree, i.e. where the
   `meta-tegra-holoscan` directory exists

* `KEYS_PATH`: Set to the path of a subdirectory in `${BUILD_ROOT}` where
  generated key files should be written.

* `L4T_DIR`: Set to the path where a previous successful build with `bitbake`
  has setup the L4T native build tools. This path depends on the current L4T
  version that is being used; for example, for a build using L4T 35.3.1 this
  path will be
  `${BUILD_ROOT}/build/tmp/work-shared/L4T-native-35.3.1-r0/Linux_for_Tegra`

### A) Enabling PKC/SBK Signing

The following example generates PKC and SBK keys and fuses the device, then an
image is signed at flash time before being flashed onto the device.

1. Generate an RSA 3K key pair (`pkc.pem`) for PKC signing:

   ```sh
   $ openssl genrsa -traditional -out ${KEYS_PATH}/pkc.pem 3072
   ```

2. Generate the Public Key Hash (`pkh.txt`) from the PKC key:

   ```sh
   $ ${L4T_DIR}/bootloader/tegrakeyhash --chip 0x23 --pkc ${KEYS_PATH}/pkc.pem | grep -A1 tegra-fuse | grep -v tegra-fuse > ${KEYS_PATH}/pkh.txt
   ```

3. Generate a Secure Boot Key (`sbk.key`):

   ```sh
   $ echo 0x$(openssl rand -hex 32 | fold -w8 | paste -sd' ' | sed 's/ / 0x/g') > ${KEYS_PATH}/sbk.key
   ```

4. Prepare the fuse config file to burn and enable the PKC and SBK fuses:

   ```sh
   $ cat > ${KEYS_PATH}/fuse_config.xml << EOF
   <genericfuse MagicId="0x45535546" version="1.0.0">
       <fuse name="PublicKeyHash" size="64" value="$(cat ${KEYS_PATH}/pkh.txt)"/>
       <fuse name="SecureBootKey" size="32" value="0x$(cat ${KEYS_PATH}/sbk.key | sed 's/0x\| //g')"/>
       <fuse name="BootSecurityInfo" size="4" value="0x209"/>
   </genericfuse>
   EOF
   ```

5. Burn the fuses. With the device in recovery mode and connected to the host
   via the USB-C debug port, use the `odmfuse.sh` script to burn the fuses.

   > **_Note:_** It is recommended to run the `odmfuse.sh` script with the `--test`
   > flag added first to make sure that the operation will succeed. Only if no
   > errors occur should the `--test` flag be removed when run.

   > **_WARNING:_** Burning fuses is an irreversible process. **Do not perform this
   > step until you are certain the fuse configuration (`fuse_config.xlm`) is
   > correct.**

   ```sh
   $ cd ${L4T_DIR}
   $ sudo ./odmfuse.sh --test -X ${KEYS_PATH}/fuse_config.xml -i 0x23 igx-orin-devkit
   {Then, if successful...}
   $ sudo ./odmfuse.sh -X ${KEYS_PATH}/fuse_config.xml -i 0x23 igx-orin-devkit
   ```

6. Flash the image using the `doflash.sh` script from a previously built and
   extracted image as before, but append the `-u` and `-v` arguments to provide
   the paths to the PKC and SBK keys, respectively.

   ```sh
   $ sudo ./doflash.sh -u ${KEYS_PATH}/pkc.pem -v ${KEYS_PATH}/sbk.key
   ```

   > **_Note:_** If using the `flash.sh` script provided by the Holoscan OE
   > Builder container, edit the script to append these arguments to the
   > `doflash.sh` line near the end of the file.

### B) Enabling UEFI Secure Boot

The following example generates the keys and certificates required to enable
UEFI secure boot, then builds and signs an image using these keys such that
the keys will be enrolled at boot time.

> **_Note:_** Post-build UEFI signing is not currently supported.

1. Generate PL, KEK, and DB key pairs and certificates:

   ```sh
   $ mkdir ${KEYS_PATH}/uefi
   $ openssl req -newkey rsa:2048 -nodes -keyout ${KEYS_PATH}/uefi/PK.key -new -x509 -sha256 -days 3650 -subj "/CN=Platform Key/" -out ${KEYS_PATH}/uefi/PK.crt
   $ openssl req -newkey rsa:2048 -nodes -keyout ${KEYS_PATH}/uefi/KEK.key -new -x509 -sha256 -days 3650 -subj "/CN=Key Exchange Key/" -out ${KEYS_PATH}/uefi/KEK.crt
   $ openssl req -newkey rsa:2048 -nodes -keyout ${KEYS_PATH}/uefi/DB.key -new -x509 -sha256 -days 3650 -subj "/CN=Signature Database Key/" -out ${KEYS_PATH}/uefi/DB.crt
   ```

2. Create UEFI keys config file:

   ```sh
   $ cat > ${KEYS_PATH}/uefi/keys.conf << EOF
   UEFI_PK_KEY_FILE="PK.key"
   UEFI_PK_CERT_FILE="PK.crt"
   UEFI_KEK_KEY_FILE="KEK.key"
   UEFI_KEK_CERT_FILE="KEK.crt"
   UEFI_DB_1_KEY_FILE="DB.key"
   UEFI_DB_1_CERT_FILE="DB.crt"
   UEFI_DB_APPEND_MSFT_UEFI=1
   EOF
   ```

   > **_Note:_** The `gen_uefi_default_keys_dts.sh` script that uses the above
   > configuration in the next step has been patched by the `meta-tegra-holoscan`
   > layer to add the `UEFI_DB_APPEND_MSFT_UEFI` option in order to append the
   > [Microsoft UEFI Certificate](https://learn.microsoft.com/en-us/windows-hardware/manufacture/desktop/windows-secure-boot-key-creation-and-management-guidance?view=windows-11#14-signature-databases-db-and-dbx)
   > to the allowed signature database (db) in the generated dts file. This is
   > needed because the Mellanox UEFI firmware has been signed by the Microsoft
   > UEFI key, so this certificate needs to be added to the signature database
   > for the Mellanox firmware drivers to be loaded by UEFI.
   >
   > The `UEFI_KEK_APPEND_MSFT` option can also be enabled in the configuration
   > file to append the [Microsoft KEK](https://learn.microsoft.com/en-us/windows-hardware/manufacture/desktop/windows-secure-boot-key-creation-and-management-guidance?view=windows-11#13-secure-boot-pki-requirements),
   > to the enrolled key exchange keys list, and the
   > `UEFI_DBX_APPEND_UEFI_REVOCATION_LIST` option can be enabled to append the
   > latest [UEFI Revocation List File](https://uefi.org/revocationlistfile) to
   > the `dbx` list.

3. Generate `UefiDefaultSecurityKeys.dts` file:

   ```sh
   $ sudo apt install -y efitools
   $ cd ${KEYS_PATH}/uefi
   $ ${L4T_DIR}/tools/gen_uefi_default_keys_dts.sh keys.conf
   ```

4. Add the `UefiDefaultSecurityKeys.dts` file to a `tegra-uefi-keys-dtb` recipe
   append file in order to enroll the keys during boot.

   ```sh
   $ cd ${BUILD_ROOT}
   $ mkdir -p meta-tegra-holoscan/recipes-bsp/uefi/tegra-uefi-keys-dtb
   $ cp ${KEYS_PATH}/uefi/UefiDefaultSecurityKeys.dts meta-tegra-holoscan/recipes-bsp/uefi/tegra-uefi-keys-dtb
   $ cat > meta-tegra-holoscan/recipes-bsp/uefi/tegra-uefi-keys-dtb.bbappend << EOF
   FILESEXTRAPATHS:prepend := "\${THISDIR}/tegra-uefi-keys-dtb:"
   EOF
   ```

5. Update `local.conf` to add the DB key and certificate to sign UEFI at build
   time. For example, the following command will append to the `local.conf`
   file and the `${KEYS_PATH}` variable will be replaced with the absolute path
   that the variable expands to. If editing the file manually, make sure to
   provide absolute paths to these files.

   ```sh
   $ cat >> ${BUILD_ROOT}/build/conf/local.conf << EOF
   TEGRA_UEFI_DB_KEY = "${KEYS_PATH}/uefi/DB.key"
   TEGRA_UEFI_DB_CERT = "${KEYS_PATH}/uefi/DB.crt"
   EOF
   ```

   > **_Note:_** When using the Holoscan OE Builder container, `${BUILD_ROOT}`
   > is mounted in the container as `/workspace`. Therefore, if `${BUILD_ROOT}`
   > is `/home/user/holoscan` and `${KEYS_PATH}` is `/home/user/holoscan/keys`
   > then the paths used in the `local.conf` file should be:
   >
   > ```sh
   > TEGRA_UEFI_DB_KEY = "/workspace/keys/uefi/DB.key"
   > TEGRA_UEFI_DB_CERT = "/workspace/keys/uefi/DB.crt"
   > ```

6. Build and flash the image to the device. To check that UEFI secure boot has
   been enabled, look for the following output from the console log during
   boot:

   ```sh
   EFI stub: UEFI Secure Boot is enabled.
   ```

   Alternatively, to check the secureboot status at runtime, add the `efivar`
   tool to the image via `CORE_IMAGE_EXTRA_INSTALL` then run the following on
   the device:

   ```sh
   $ efivar -n 8be4df61-93ca-11d2-aa0d-00e098032b8c-SecureBoot
   ```

   If secureboot is enabled, this will output:

   ```sh
   Value:
   00000000  01
   ```

   If secureboot is not enabled, the `01` value will instead be `00`.

   To check the PK, KEK, and db values, the following commands can be used:

   ```sh
   $ efivar -n 8be4df61-93ca-11d2-aa0d-00e098032b8c-PK
   $ efivar -n 8be4df61-93ca-11d2-aa0d-00e098032b8c-KEK
   $ efivar -n d719b2cb-3d3a-4596-a3bc-dad00e67656f-db
   ```
