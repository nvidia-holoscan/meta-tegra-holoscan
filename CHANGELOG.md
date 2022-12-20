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
- Support for the NVIDIA IGX Orin Developer Kit (`holoscan-devkit`).
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
  IGX Orin Developer Kit (holoscan-devkit) with iGPU configuration.
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

[0.4.0]: https://github.com/nvidia-holoscan/meta-tegra-holoscan/compare/v0.3.0...v0.4.0
[0.3.0]: https://github.com/nvidia-holoscan/meta-tegra-holoscan/compare/v0.2.0...v0.3.0
[0.2.0]: https://github.com/nvidia-holoscan/meta-tegra-holoscan/releases/tag/v0.2.0
