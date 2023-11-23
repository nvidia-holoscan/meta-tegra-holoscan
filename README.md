# meta-nvidia-open-gpu-kernel-modules
OpenEmbedded/Yocto layer for NVIDIA Linux Open GPU Kernel Modules

Forked from https://github.com/nvidia-holoscan/meta-tegra-holoscan

* This meta-layer is mostly to build NVIDIA Linux Open GPU Kernel Modules and the required user-scpace tools on AMD64 architecture.

**It was validated using the Foundries.io Linux microPlatform:**

```bash
fio@intel-corei7-64:~$ dmesg|grep nvidia
[    1.783147] nvidia: loading out-of-tree module taints kernel.
[    1.867629] nvidia-nvlink: Nvlink Core is being initialized, major device number 243
[    1.882064] nvidia 0000:00:06.0: vgaarb: changed VGA decodes: olddecodes=io+mem,decodes=none:owns=io+mem
[    1.942144] nvidia-modeset: Loading NVIDIA UNIX Open Kernel Mode Setting Driver for x86_64  530.30.02  Release Build  (@2a91ba5bf613)  Thu 23 Nov 2023 04:50:51 PM UTC
[    1.944852] [drm] [nvidia-drm] [GPU ID 0x00000006] Loading driver
[    1.946084] [drm] Initialized nvidia-drm 0.0.0 20160202 for 0000:00:06.0 on minor 0
[    1.963802] nvidia-uvm: Loaded the UVM driver, major device number 241.
```


**Also validated with docker:**

_The NGC catalog hosts containers for AI/ML, metaverse, and HPC applications and are performance-optimized, tested, and ready to deploy on GPU-powered on-prem, cloud, and edge systems._
https://catalog.ngc.nvidia.com/containers

```bash
$ fio@intel-corei7-64:~$ docker run --rm --gpus all nvcr.io/nvidia/k8s/cuda-sample:devicequery-cuda11.7.1-ubuntu20.04
Unable to find image 'nvcr.io/nvidia/k8s/cuda-sample:devicequery-cuda11.7.1-ubuntu20.04' locally
devicequery-cuda11.7.1-ubuntu20.04: Pulling from nvidia/k8s/cuda-sample
3b65ec22a9e9: Pull complete 
9bfa49b064c8: Pull complete 
cde16ef91ac2: Pull complete 
978ea3dcd5fb: Pull complete 
6c10428f3e1b: Pull complete 
4f4fb700ef54: Pull complete 
eb93a482f729: Pull complete 
04b6eda36a81: Pull complete 
ad6594c18b21: Pull complete 
fba3ec0e18ff: Pull complete 
8fc0b94104ee: Pull complete 
Digest: sha256:e72dbd0d7e07101c2d2377ee3455e3cc572ff5a71ecf52743a212912e80df62b
Status: Downloaded newer image for nvcr.io/nvidia/k8s/cuda-sample:devicequery-cuda11.7.1-ubuntu20.04
[   43.303309] docker0: port 1(vethdaee1b8) entered blocking state
[   43.303734] docker0: port 1(vethdaee1b8) entered disabled state
[   43.304221] device vethdaee1b8 entered promiscuous mode
[   43.428512] NVRM objClInitPcieChipset: *** Chipset Setup Function Error!
[   43.429059] NVRM objClInitPcieChipset: *** Unable to get PCI port handles
[   43.429739] ACPI Warning: \_SB.PCI0.S30._DSM: Argument #4 type mismatch - Found [Buffer], ACPI requires [Package] (20220331/nsarguments-61)
[   45.493798] NVRM objClInitPcieChipset: *** Unable to get PCI port handles
[   47.739484] eth0: renamed from veth533f3b4
[   47.741504] IPv6: ADDRCONF(NETDEV_CHANGE): vethdaee1b8: link becomes ready
[   47.742002] docker0: port 1(vethdaee1b8) entered blocking state
[   47.742438] docker0: port 1(vethdaee1b8) entered forwarding state
[   47.742876] IPv6: ADDRCONF(NETDEV_CHANGE): docker0: link becomes ready
[   47.789299] NVRM objClInitPcieChipset: *** Unable to get PCI port handles
/cuda-samples/sample Starting...

 CUDA Device Query (Runtime API) version (CUDART static linking)

Detected 1 CUDA Capable device(s)

Device 0: "NVIDIA T400 4GB"
  CUDA Driver Version / Runtime Version          12.1 / 11.7
  CUDA Capability Major/Minor version number:    7.5
  Total amount of global memory:                 3768 MBytes (3951230976 bytes)
  (006) Multiprocessors, (064) CUDA Cores/MP:    384 CUDA Cores
  GPU Max Clock rate:                            1425 MHz (1.42 GHz)
  Memory Clock rate:                             5001 Mhz
  Memory Bus Width:                              64-bit
  L2 Cache Size:                                 524288 bytes
  Maximum Texture Dimension Size (x,y,z)         1D=(131072), 2D=(131072, 65536), 3D=(16384, 16384, 16384)
  Maximum Layered 1D Texture Size, (num) layers  1D=(32768), 2048 layers
  Maximum Layered 2D Texture Size, (num) layers  2D=(32768, 32768), 2048 layers
  Total amount of constant memory:               65536 bytes
  Total amount of shared memory per block:       49152 bytes
  Total shared memory per multiprocessor:        65536 bytes
  Total number of registers available per block: 65536
  Warp size:                                     32
  Maximum number of threads per multiprocessor:  1024
  Maximum number of threads per block:           1024
  Max dimension size of a thread block (x,y,z): (1024, 1024, 64)
  Max dimension size of a grid size    (x,y,z): (2147483647, 65535, 65535)
  Maximum memory pitch:                          2147483647 bytes
  Texture alignment:                             512 bytes
  Concurrent copy and kernel execution:          Yes with 3 copy engine(s)
  Run time limit on kernels:                     No
  Integrated GPU sharing Host Memory:            No
  Support host page-locked memory mapping:       Yes
  Alignment requirement for Surfaces:            Yes
  Device has ECC support:                        Disabled
  Device supports Unified Addressing (UVA):      Yes
  Device supports Managed Memory:                Yes
  Device supports Compute Preemption:            Yes
  Supports Cooperative Kernel Launch:            Yes
  Supports MultiDevice Co-op Kernel Launch:      Yes
  Device PCI Domain ID / Bus ID / location ID:   0 / 0 / 6
  Compute Mode:
     < Default (multiple host threads can use ::cudaSetDevice() with device simultaneously) >

deviceQuery, CUDA Driver = CUDART, CUDA Driver Version = 12.1, CUDA Runtime Version = 11.7, NumDevs = 1
Result = PASS
[   50.121203] docker0: port 1(vethdaee1b8) entered disabled state
[   50.122442] veth533f3b4: renamed from eth0
[   50.138676] docker0: port 1(vethdaee1b8) entered disabled state
[   50.140422] device vethdaee1b8 left promiscuous mode
[   50.140861] docker0: port 1(vethdaee1b8) entered disabled state
```
