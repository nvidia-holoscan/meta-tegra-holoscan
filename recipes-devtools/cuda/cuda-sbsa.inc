# Copyright (c) 2023-2024, NVIDIA CORPORATION. All rights reserved.
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

CUDA_INSTALL_ARCH:dgpu:aarch64 = "sbsa"

L4T_DEB_FEED_BASE:dgpu = "https://developer.download.nvidia.com/compute/cuda/repos/ubuntu2204/${CUDA_INSTALL_ARCH}"
L4T_DEB_FEED_SKIP_POOL_APPEND:dgpu = "1"

CUDA_LICENSE_PKG:dgpu = "cuda-documentation-${CUDA_VERSION_DASHED}_12.6.68-1_${CUDA_DEB_PKGARCH}.deb;name=lic;subdir=${BP}"
LICSUM:dgpu:aarch64 = "e5d62a7dc89e5d9db35e544be6b7ecc6fc366bbee6202dae5aa22aaf4d32478e"
LICSUM:dgpu:x86-64 = "56799fe630027f4f0a1612fbb3a4892e8fb9113a067d7f9467c778c7a4d62c3d"
LIC_FILES_CHKSUM:dgpu = "file://usr/local/cuda-${CUDA_VERSION}/EULA.txt;md5=929c95a6164f11b95d0001c9bc136926"

# We patch the download name of SBSA packages to append 'sbsa' so that the iGPU and
# dGPU packages do not conflict if the tree is ever used to build both configurations.
def sbsa_debs(d):
    lic_pkg = d.getVar('CUDA_LICENSE_PKG').split(';')
    lic_pkg.append('downloadfilename=%s' % lic_pkg[0].replace('.deb', '_sbsa.deb'))
    debs = [';'.join(lic_pkg)]
    for pkg in d.getVar('CUDA_PKG').split():
        debbase = '%s-${CUDA_VERSION_DASHED}_${PV}_${CUDA_DEB_PKGARCH}' % pkg
        deb = '%s.deb' % (debbase)
        download = '%s_%s.deb' % (debbase, 'sbsa')
        name = 'dev' if pkg.endswith('-dev') else 'main'
        debs.append('%s;name=%s;subdir=${BP};downloadfilename=%s' % (deb, name, download))
    return ' '.join(debs)

SRC_COMMON_DEBS:dgpu = "${@sbsa_debs(d)}"
