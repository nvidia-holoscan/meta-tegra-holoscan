## [3.3.0] - 2025-05-21
### Changed
- Updated Holoscan SDK and Holohub apps to 3.3.0.
- Update GXF to 5.0.0-20250506-dad6e7b.
- Updated ONNX Runtime to 1.21.0.
- Updated python3-cupy to 13.4.0.
- Updated UCX to use PACKAGECONFIG.
- Updated edk2-firmware-tegra bbappend recipe to 36.4.3.
- Updated tegra-flashtools-native bbappend recipe to 36.4.3
- Updated meta-tegra dependency.
- Updated meta-openembedded dependency.
- Updated meta-virtualization dependency.
- Updated Poky dependency.
- Updated Flashing process. Using QSPI+NVMe instead of QSPI+eMMC.
- Added tegra-eks-image bbappend recipe to allow uses introducing EKS image
  when secure boot is enabled for Disk Encryption feature.
- Added ONNX 1.17.0 recipe to satisfy dependency with Holoscan SDK and ONNX Runtime.
- Added dlpack 1.0 recipe to satisfy dependency with Holoscan SDK and ONNX Runtime.
- Added glfw 3.4 recipe to satisfy dependency with Holoscan SDK.
- Added magic-enum 0.9.3 recipe to satisfy dependency with Holoscan SDK.
- Added RMM 24.04.00 recipe to satisfy dependency with Holoscan SDK.
- Added tl-expected 1.1.0 recipe to satisfy dependency with Holoscan SDK.
- Added cutlass 3.5.1 recipe to satisfy dependency with ONNX Runtime.
- Added nccl 2.26.5 recipe to satisfy dependency with CuPy.
- Added abseil-cpp 20250127.1 recipe to satisfy dependency with ONNX Runtime.
- Added imgui f337378 recipe to satisfy dependency with Holoscan SDK.
- Added stb af1a5bc recipe to satisfy dependency with Holoscan SDK.
- Added jitify 1a0ca0e recipe to satisfy dependency with CuPy.
- Added cccl 2.2.0 recipe to satisfy dependency with CuPy, RMM, jitify and Holoscan SDK.
- Added cpm-cmake 0.38.5 recipe to satisfy dependency with RMM and Holoscan SDK.
- Added rapids-cmake 24.04.00 recipe to satisfy dependency with RMM and Holoscan SDK.
- Added nvtx 3.1.0 recipe to satisfy dependency with CuPy and Holoscan SDK.
- Added python3-fastrlock 0.8.3 recipe to satisfy dependency with CuPy.
- Added boost-mp11 1.84.0 recipe to satisfy dependency with ONNX Runtime.
- Added date 3.0.1 recipe to satisfy dependency with ONNX Runtime.
- Added fmt 10.1.1 recipe to satisfy dependency with RMM and Holoscan SDK.
- Added microsoft-gsl 4.0.0 recipe to satisfy dependency with ONNX Runtime.
- Added nsync 1.26.0 recipe to satisfy dependency with ONNX Runtime.
- Added safeint 3.0.28 recipe to satisfy dependency with ONNX Runtime.
- Added yaml-cpp 0.7.0 recipe to satisfy dependency with Holoscan SDK.
- Dropped nlohmann-json 3.9.1 recipe.

### Fixed
- Fixed PREFERRED_PROVIDER of tegra-libraries-cuda and tegra-libraries-nvml.
- Updated PREFERRED_VERSION for xserver-xorg-video-nvidia.
- Installed nvcudla library which was missing in tegra-libraries-cuda recipe.
- Fixed cuda-nvrtc SHA256SUM for x86_64
- Dropped libcudla dependency for dGPU mode.


## [3.2.0] - 2025-04-30
### Changed
- Updated Holoscan SDK and Holohub apps to 3.2.0


## [3.1.0] - 2025-04-03
### Changed
- Updated Holoscan SDK and Holohub apps to 3.1.0


## [3.0.0] - 2025-03-14
### Changed
- Updated Holoscan SDK and Holohub apps to 3.0.0
- Updated meta-tegra dependency

### Fixed
- Added nv-kernel-module-r8168 to igx-orin-devkit machine configuration.


## [2.9.0] - 2025-01-27
### Changed
- Updated Holoscan SDK and Holohub apps to 2.9.0
- Added ngc-cli 3.57.2 recipe, which is used by the Holohub apps to
  fetch sample datasets.
- Updated meta-tegra dependency


## [2.8.0] - 2025-01-02
### Changed
- Updated Holoscan SDK and Holohub apps to 2.8.0


## [2.7.0] - 2024-12-03
### Changed
- Updated Holoscan SDK and Holohub apps to 2.7.0
- Updated GXF to 4.1-24.10.29


## [2.6.0] - 2024-10-29
### Changed
- Updated L4T to 36.4.0
- Updated all layer dependencies (Scarthgap 5.0.4)
- Updated Holoscan SDK and Holohub apps to 2.6.0
- Updated various component versions
  - CUDA 12.6.68-1
  - cuDNN 9.3.0.75-1
  - TensorRT 10.3.0.26-1
  - PyTorch 2.5.0
  - Torchvision 0.20.1
  - ONNX Runtime 1.18.1
  - UCX 1.17.0
  - GXF 4.1-24.10

### Fixed
- Fixed build issue with native build of python3-numpy 1.26.4
- Updated iGPU include path for ajantv2-driver RDMA support


## [2.5.0] - 2024-10-01
### Changed
- Updated Holoscan SDK and Holohub apps to 2.5.0
- Updated AJA NTV2 to 17.1.0

### Fixed
- Skipped download of unused test data for Holoscan SDK.


## [2.4.0] - 2024-09-06
### Changed
- Updated Holoscan SDK and Holohub apps to 2.4.0


## [2.3.0] - 2024-08-05
### Added
- Support for Kata Containers (3.7.0)

### Changed
- Updated L4T to 36.3.0
- Updated Yocto and all layer dependencies to Scarthgap (5.0.2)
- Updated dGPU drivers to 555.42.02
- Updated CuPy to 13.2.0
- Updated FastRLock to 0.8.2
- Updated Holoscan SDK and Holohub apps to 2.3.0
- Restored IGX Orin Devkit machine configuration, which used to be provided
  by meta-tegra layer.
- Removed use of `PREFERRED_RPROVIDER` to install dGPU drivers, instead fixing
  `MACHINE_EXTRA_RDEPENDS` to remove the Tegra iGPU display drivers then install
  the dGPU drivers instead.

### Fixed
- Fixed the branch name used by the ajantv2 recipe.


## [2.2.0] - 2024-07-02
### Changed
- Updated Holoscan SDK and Holohub apps to 2.2.0
- Updated Clara Viz to 0.4.0


## [2.1.0] - 2024-06-04
### Changed
- Updated Holoscan SDK and Holohub apps to 2.1.0


## [2.0.0] - 2024-04-19
### Added
- Magic Enum C++ 0.9.5 recipe

### Changed
- Updated Holoscan SDK and Holohub apps to 2.0.0
- Updated GXF to 4.0-24.04
- AJA NTV2 now uses official 17.0.1 release instead of the main (unstable)
  development branch.


## [1.0.0] - 2024-02-09
### Added
- A `core-image-holoscan` reference image, based on `core-image-sato` with some
  Holoscan-specific modifications.
- Matchbox desktop customizations for Holoscan SDK and Holohub, including a
  Holoscan application group and desktop icons for the sample applications.

### Changed
- Updated L4T to 36.2.0
- Updated layer for Yocto Nanbield (4.3.2)
- Replaced `TEGRA_DGPU` build variable with `dgpu` machine override.
- Removed support for Clara AGX and Holoscan devkits.
- Common iGPU and dGPU components now use the same major/minor/patch versions.
- Most component versions were updated; see component version table below.

### Component Versions
| Component       | Version       |
| --------------- | ------------- |
| L4T             | 36.2.0        |
| Kernel          | 5.15.122      |
| dGPU Drivers    | 535.154.05    |
| CUDA            | 12.2.2        |
| cuDNN           | 8.9.4.25      |
| CuPy            | 12.3.0        |
| GXF             | 3.1-24.01     |
| Holoscan SDK    | 1.0.3         |
| MLNX OFED       | 23.10-1.1.9.0 |
| Nsight Systems  | 2023.3.3.42   |
| ONNX Runtime    | 1.16.3        |
| PyTorch         | 2.1.2         |
| Rivermax        | 1.40.11       |
| TensorRT        | 8.6.2         |
| Torchvision     | 0.16.2        |
| UCX             | 1.15.0        |
| AJA NTV2        | 17.1          |


## [0.6.0] - 2023-08-01
### Added
- Various dependency recipes
    - Clara Viz 0.3.1
    - PyTorch 2.0.1
    - torchvision 0.15.2
    - libnvjpeg 12.2.0.2
    - nlohmann-json 3.9.1
    - Jansson 2.14
    - cloudpickle 2.2.1
- `nvidia_deb_pkgfeed` class to simplify fetching NVIDIA packages from the
  SBSA repository.

### Changed
- Updated dGPU components
    - Drivers 530.30.02
    - CUDA 12.1.1
    - cuDNN 8.9.0.131
    - TensorRT 8.6.0.12
    - Nsight Systems 2023.1.2
- Other updated components
    - MLNX OFED 5.9-0.5.6.0
    - Rivermax 1.21.10
    - mstflint 4.24.0-1
    - NVIDIA GPU Stress Test 2.4.0
    - UCX 1.14.1
    - CuPy 12.1.0
    - GXF 23.05
- Use public download locations for the following NVIDIA components to avoid the
  need to manually download them outside of the bitbake build process
    - cuDNN
    - TensorRT
    - GXF
    - Nsight Systems
- Replaces deprecated `nv_peer_mem` module with `nvidia-peermem` module built
  from the `nvidia-open-gpu-kernel-modules` source.

### Fixed
- Added patch to `nvidia-display-driver` to fix `nvidia-p2p` symbol conflicts
  with `nvidia.ko`, which fixes RDMA for the iGPU.
- Added missing fan control settings for the IGX Orin Devkit.
- Fixed the iGPU build of the AJA drivers with RDMA enabled.
- Added kernel patch to fix the color format for the Lontium HDMI input board
  supplied with the IGX Orin Devkit.
- Added kernel parameter to disable ACS for the CX7 device in the IGX Orin
  Devkit since RDMA does not work with ACS enabled.

### Known Issues
- The `h264` Holohub applications do not work with the dGPU configuration.
- Emergent cameras and the `high_speed_endoscopy` Holohub application
  do not work with the dGPU configuration.

### Component Versions
|                 | iGPU and dGPU |
| --------------- | ------------- |
| L4T             | 35.3.1        |
| Holoscan SDK    | 0.6.0         |
| MLNX OFED       | 5.9-0.5.6.0   |
| Rivermax        | 1.21.10       |
| AJA NTV2        | 16.2.0        |

|                 | iGPU      | dGPU      |
| --------------- | --------- | --------- |
| GPU Drivers     | 35.2.1    | 530.30.02 |
| CUDA            | 11.4.19   | 12.1.1    |
| cuDNN           | 8.6.0.166 | 8.9.0.131 |
| TensorRT        | 8.5.2.1   | 8.6.0.12  |
| Nsight Systems  | 2022.5.2  | 2023.1.2  |
| Emergent Camera | 2.37.05   | N/A       |


## [0.5.1] - 2023-04-25
### Added
- Secure Boot documentation section
- Patch to append the Microsoft UEFI certificate and UEFI revocation
  list to the Secure Boot key enrollment list

### Changed
- Updated L4T to 35.3.1
- Updated IGX Orin Devkit configuration for latest board revision
- Switched mlnx-ofed kernel patch to config fragment

### Known Issues
- Enabling Secure Boot when using a dGPU causes the UEFI boot menu
  to no longer be displayed on the dGPU video output.


## [0.5.0] - 2023-03-31
### Added
- Support for IGX Orin Developer Kit (`igx-orin-devkit`)
- HoloHub Applications
- Holoscan Test Suite
- Holoscan UEFI boot image
- dGPU container support
    - NVIDIA Container Toolkit 1.9.0
    - libnvidia-container 1.11.0
- Nsight Systems 2023.1.1
- Documentation and support for debugging using GDB
- `PREEMPT_RT` patch support (iGPU only)
- Rivermax generic-sender and generic-receiver samples
- Various dependency recipes
    - Emergent Camera 2.37.05
    - FastRLock 0.8.1
    - CuPy 11.5.0
    - UCX 1.13.1
    - NVIDIA GPU Stress Test 2.3.0

### Changed
- Switched to using Yocto/Poky (Kirkstone 4.0.5)
- Updated documentation to use `core-image-sato` image by default
- Added `kernel-modules` to build template for convenience
- Set default power mode to MAXN

### Fixed
- Added missing dGPU driver library symlinks
- Fixed nvidia-settings application
- Added missing Rivermax library symlinks
- Added runtime check to flash script for required dtc command
- Added missing Mellanox firmware updater tool
- Disabled MetaModes in xorg config to fix Vulkan exclusive display mode

### Component Versions
|                 | iGPU and dGPU |
| --------------- | ------------- |
| L4T             | 35.2.1        |
| Holoscan SDK    | 0.5.0         |
| MLNX OFED       | 5.8-1.0.1.1   |
| Rivermax        | 1.20.10       |
| AJA NTV2        | 16.2.0        |
| Emergent Camera | 2.37.05       |

|                | iGPU      | dGPU      |
| -------------- | --------- | --------- |
| GPU Drivers    | 35.2.1    | 525.85.05 |
| CUDA           | 11.4.19   | 11.8.0    |
| cuDNN          | 8.6.0.166 | 8.7.0.84  |
| TensorRT       | 8.5.2.1   | 8.5.3.1   |
| nvidia-peermem | N/A       | 1.3.0     |
| Nsight Systems | 2022.5.2  | 2023.1.1  |


## [0.4.0] - 2022-12-20
### Added
- ONNX Runtime 1.12.1 recipe
- Missing mlnx-ofed components (libibmad5, libibumad3, libibnetdisc5, etc.)
- mstflint 4.22.0-1 recipe

### Changed
- Renamed layer to meta-tegra-holoscan
- Updated Holsocan SDK to 0.4.0 using new GitHub repository
  (https://github.com/nvidia-holoscan/holoscan-sdk)
- Updated GXF to 2.5.0
- glad is now built as a shared library.
- Updated mlnx-ofed to 5.8-1.0.1.1
- Updated Rivermax to 1.20.10

### Fixed
- Added host network option to docker run commands to work with VPN.
- Added missing Vulkan ICD to GPU drivers.
- The AJA kernel module will now load automatically if an AJA device is
  found, eliminating the need to add it to `KERNEL_MODULE_AUTOLOAD`.

### Known Issues
- A build-time race condition may lead to Rivermax packaging conflicts.
  See the "Building and Flashing" section of README.md for a solution.
- The display may not be detected during first boot.
  See the "Building and Flashing" section of README.md for a solution.

### Component Versions
|                 | iGPU and dGPU |
| --------------- | ------------- |
| L4T             | 35.1.0        |
| Holoscan SDK    | 0.4.0         |
| GXF             | 2.5.0         |
| AJA NTV2        | 16.2.0        |
| MLNX OFED       | 5.8-1.0.1.1   |
| Rivermax        | 1.20.10       |

|                | iGPU     | dGPU      |
| -------------- | -------- | --------- |
| GPU Drivers    | 35.1.0   | 515.65.01 |
| CUDA           | 11.4.239 | 11.7.1    |
| cuDNN          | 8.4.1.50 | 8.5.0.96  |
| TensorRT       | 8.4.1    | 8.4.3.1   |
| nvidia-peermem | N/A      | 1.3.0     |


## [0.3.0] - 2022-10-19
### Added
- Support for the NVIDIA IGX Orin Developer Kit ES (`holoscan-devkit`).
- Ability to support both iGPU and dGPU configurations via separate includes
(`holoscan-igpu.conf` and `holoscan-dgpu.conf`, respectively).
- Mellanox OFED and Rivermax Support.

### Changed
- dGPU kernel modules are now built from the [NVIDIA Linux Open GPU Kernel
Module Source](https://github.com/NVIDIA/open-gpu-kernel-modules).
- CUDA samples for the dGPU are now built from the [CUDA Samples GitHub
Repo](https://github.com/NVIDIA/cuda-samples).

### Known Issues
- Holoscan SDK Ultrasound segmentation application is not functional on NVIDIA
  IGX Orin Developer Kit ES (holoscan-devkit) with iGPU configuration.
- Holoscan SDK High-Speed Endoscopy application is not yet supported.

### Component Versions
|                 | iGPU and dGPU |
| --------------- | ------------- |
| L4T             | 35.1.0        |
| Holoscan SDK    | 0.3.0         |
| GXF             | 2.4.3         |
| AJA NTV2        | 16.2.0        |
| MLNX OFED       | 5.7-1.0.2.0   |
| Rivermax        | 1.11.11       |

|                | iGPU     | dGPU      |
| -------------- | -------- | --------- |
| GPU Drivers    | 35.1.0   | 515.65.01 |
| CUDA           | 11.4.239 | 11.7.1    |
| cuDNN          | 8.4.1.50 | 8.5.0.96  |
| TensorRT       | 8.4.1    | 8.4.3.1   |
| nvidia-peermem | N/A      | 1.3.0     |


## [0.2.0] - 2022-07-12
### Added
- Initial Holoscan MGX release, corresponding with the v0.2.0 release of the
[Clara Holoscan Embedded SDK](https://github.com/NVIDIA/clara-holoscan-embedded-sdk).
- Supports dGPU mode on the Clara AGX Developer Kit (`clara-agx-xavier-devkit`).

### Component Versions
|                | dGPU     |
| -------------- | -------- |
| L4T            | 32.7.1   |
| NVIDIA Drivers | 470.94   |
| CUDA           | 11.4.3   |
| cuDNN          | 8.2.4.15 |
| TensorRT       | 8.2.1.8  |
| GXF            | 2.4.2    |
| AJA NTV2       | 16.2.0   |
| Holoscan SDK   | 0.2.0    |

[3.3.0]: https://github.com/nvidia-holoscan/meta-tegra-holoscan/compare/v3.2.0...v3.3.0
[3.2.0]: https://github.com/nvidia-holoscan/meta-tegra-holoscan/compare/v3.1.0...v3.2.0
[3.1.0]: https://github.com/nvidia-holoscan/meta-tegra-holoscan/compare/v3.0.0...v3.1.0
[3.0.0]: https://github.com/nvidia-holoscan/meta-tegra-holoscan/compare/v2.9.0...v3.0.0
[2.9.0]: https://github.com/nvidia-holoscan/meta-tegra-holoscan/compare/v2.8.0...v2.9.0
[2.8.0]: https://github.com/nvidia-holoscan/meta-tegra-holoscan/compare/v2.7.0...v2.8.0
[2.7.0]: https://github.com/nvidia-holoscan/meta-tegra-holoscan/compare/v2.6.0...v2.7.0
[2.6.0]: https://github.com/nvidia-holoscan/meta-tegra-holoscan/compare/v2.5.0...v2.6.0
[2.5.0]: https://github.com/nvidia-holoscan/meta-tegra-holoscan/compare/v2.4.0...v2.5.0
[2.4.0]: https://github.com/nvidia-holoscan/meta-tegra-holoscan/compare/v2.3.0...v2.4.0
[2.3.0]: https://github.com/nvidia-holoscan/meta-tegra-holoscan/compare/v2.2.0...v2.3.0
[2.2.0]: https://github.com/nvidia-holoscan/meta-tegra-holoscan/compare/v2.1.0...v2.2.0
[2.1.0]: https://github.com/nvidia-holoscan/meta-tegra-holoscan/compare/v2.0.0...v2.1.0
[2.0.0]: https://github.com/nvidia-holoscan/meta-tegra-holoscan/compare/v1.0.0...v2.0.0
[1.0.0]: https://github.com/nvidia-holoscan/meta-tegra-holoscan/compare/v0.6.0...v1.0.0
[0.6.0]: https://github.com/nvidia-holoscan/meta-tegra-holoscan/compare/v0.5.1...v0.6.0
[0.5.1]: https://github.com/nvidia-holoscan/meta-tegra-holoscan/compare/v0.5.0...v0.5.1
[0.5.0]: https://github.com/nvidia-holoscan/meta-tegra-holoscan/compare/v0.4.0...v0.5.0
[0.4.0]: https://github.com/nvidia-holoscan/meta-tegra-holoscan/compare/v0.3.0...v0.4.0
[0.3.0]: https://github.com/nvidia-holoscan/meta-tegra-holoscan/compare/v0.2.0...v0.3.0
[0.2.0]: https://github.com/nvidia-holoscan/meta-tegra-holoscan/releases/tag/v0.2.0
