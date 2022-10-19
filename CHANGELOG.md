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
- Holoscan SDK Ultrasound segmentation application is not functional on NVIDIA IGX Orin Developer Kit (holoscan-devkit) with iGPU configuration.
- Holoscan SDK High-Speed Endoscopy application is not supported in deployment stack.

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

[0.3.0]: https://github.com/NVIDIA/meta-tegra-clara-holoscan-mgx/compare/v0.2.0...v0.3.0
[0.2.0]: https://github.com/NVIDIA/meta-tegra-clara-holoscan-mgx/releases/tag/v0.2.0
