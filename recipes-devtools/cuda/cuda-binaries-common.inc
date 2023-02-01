# Copyright (c) 2022-2023, NVIDIA CORPORATION. All rights reserved.
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

def cuda_install_arch(d):
    arch = d.getVar('HOST_ARCH')
    return 'sbsa' if arch == 'aarch64' else arch

# Override the meta-tegra 'targets' path used for the CUDA install.
CUDA_INSTALL_ARCH = "${@cuda_install_arch(d)}"

# Override the meta-tegra L4T deb package feed.
L4T_DEB_FEED_BASE = "https://developer.download.nvidia.com/compute/cuda/repos/ubuntu2004/${CUDA_INSTALL_ARCH}"
L4T_DEB_FEED_SKIP_POOL_APPEND = "1"

# Override the meta-tegra CUDA license details.
CUDA_LICENSE_PKG ?= "cuda-documentation-${CUDA_VERSION_DASHED}_11.8.86-1_${CUDA_DEB_PKGARCH}.deb;name=lic;subdir=${BP}"
LICSUM:aarch64 = "64f9eb8571447497e94e811174080089157df5d8fa50402b6b144f91499c2ce1"
LICSUM:x86-64 = "be5d9b32d6cb81e27b670d37090442f12d81592b37502b971b03d21fea5eb262"
LIC_FILES_CHKSUM ?= "file://usr/local/cuda-${CUDA_VERSION}/EULA.txt;md5=d43b70e08c9cd63c838dc661b6f1a763"

# Include the meta-tegra CUDA binary package include.
require recipes-devtools/cuda/cuda-shared-binaries.inc
