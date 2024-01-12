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

inherit nvidia_deb_pkgfeed

# Override the meta-tegra 'targets' path used for the CUDA install.
CUDA_INSTALL_ARCH = "${@nvidia_package_arch(d)}"

# Override the meta-tegra CUDA license details.
CUDA_LICENSE_PKG ?= "cuda-documentation-${CUDA_VERSION_DASHED}_12.1.105-1_${CUDA_DEB_PKGARCH}.deb;name=lic;subdir=${BP}"
LICSUM:aarch64 = "f3d7b4a86100cba53b98242825193dd4fa4cad904809ff0e1ccbc11709c47cb2"
LICSUM:x86-64 = "5a07592fe78277a74e80ce94c8fafa2ecc4ce1d14c043334615e10d673414efc"
LIC_FILES_CHKSUM ?= "file://usr/local/cuda-${CUDA_VERSION}/EULA.txt;md5=d43b70e08c9cd63c838dc661b6f1a763"

# Include the meta-tegra CUDA binary package include.
require recipes-devtools/cuda/cuda-shared-binaries.inc