# Copyright (c) 2025, NVIDIA CORPORATION. All rights reserved.
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

DESCRIPTION = "An efficient C++17 GPU numerical computing library"
HOMEPAGE = "https://github.com/NVIDIA/MatX"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=80968939478234f1ff00154a17eda6ed"

inherit cmake cuda

SRC_URI = " \
    git://github.com/NVIDIA/MatX.git;protocol=https;nobranch=1 \
    file://0001-Updates-for-OE-cross-builds.patch \
"
SRCREV = "86d0b82d35180480fe8d69729279835ce9b033ff"

S = "${WORKDIR}/git"

DEPENDS += "cccl cpm-cmake rapids-cmake"

EXTRA_OECMAKE:append = " \
    -DCCCL_DIR=${RECIPE_SYSROOT}/usr/local/cuda-${CUDA_VERSION}/lib/cmake/cccl \
    -DCPM_SOURCE_CACHE=${RECIPE_SYSROOT}${datadir} \
    -DRAPIDS_CMAKE_DIR=${RECIPE_SYSROOT}/opt/nvidia/rapids-cmake \
"
