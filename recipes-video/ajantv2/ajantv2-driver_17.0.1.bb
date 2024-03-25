# Copyright (c) 2022-2024, NVIDIA CORPORATION. All rights reserved.
#
# Permission is hereby granted, free of charge, to any person obtaining a
# copy of this software and associated documentation files (the "Software"),
# to deal in the Software without restriction, including without limitation
# the rights to use, copy, modify, merge, publish, distribute, sublicense,
# and/or sell copies of the Software, and to permit persons to whom the
# Software is furnished to do so, subject to the following conditions:
#
# The above copyright notice and this permission notice shall be included in
# all copies or substantial portions of the Software.
#
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
# IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
# THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
# LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
# FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
# DEALINGS IN THE SOFTWARE.

SUMMARY = "AJA NTV2 Driver"

require ajantv2-common_${PV}.inc

S = "${WORKDIR}/git/driver/linux"

inherit module

EXTRA_OEMAKE:append = " \
    KDIR=${STAGING_KERNEL_DIR} \
    AJA_CREATE_DEVICE_NODES=1 \
    AJA_RDMA=1 \
    ${GPU_FLAGS} \
"

# The RDMA (nvidia-p2p) API differs between iGPU and dGPU:
#   For dGPU, RDMA is supported by nvidia.ko provided by nvidia-open-gpu-kernel-modules.
#   For iGPU, RDMA is supported by nvidia-p2p.ko provided by nvidia-kernel-oot.
GPU_FLAGS = " \
    AJA_IGPU=1 \
    NVIDIA_SRC_DIR=${RECIPE_SYSROOT}${includedir}/linux \
    NVIDIA_SYMVERS=${RECIPE_SYSROOT}${includedir}/nvidia-kernel-oot/Module.symvers \
"
GPU_FLAGS:dgpu = " \
    NVIDIA_SRC_DIR=${RECIPE_SYSROOT}${includedir}/nvidia \
    NVIDIA_SYMVERS=${RECIPE_SYSROOT}${includedir}/nvidia-open-gpu-kernel-modules/Module.symvers \
"

DEPENDS = " nvidia-kernel-oot"
DEPENDS:dgpu = " nvidia-open-gpu-kernel-modules"

RPROVIDES:${PN} += "kernel-module-ajantv2"
