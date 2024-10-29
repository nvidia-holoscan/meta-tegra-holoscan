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

SUMMARY = "Unified Communication X"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=cbe4fe88c540f18985ee4d32d590f683"

SRC_URI = "git://github.com/openucx/ucx.git;protocol=https;branch=v1.17.x"
SRCREV = "4ef9a097c12ee6f7a8d3e41c317ea2d47e424b32"

S = "${WORKDIR}/git"

SRC_URI += " \
    file://0001-Fix-CMAKE-library-import-paths.patch \
    file://0002-Add-option-to-enable-NVML.patch \
"

inherit autotools pkgconfig cuda

EXTRA_OECONF:append = " \
    --with-cuda=${RECIPE_SYSROOT}/usr/local/cuda-${CUDA_VERSION} \
    --with-rdmacm=${RECIPE_SYSROOT}${prefix} \
    --with-verbs=${RECIPE_SYSROOT}${prefix} \
    --with-mlx5-dv \
    --disable-logging \
    --disable-debug \
    --disable-assertions \
    --disable-params-check \
    --enable-mt \
"

EXTRA_OECONF:append:dgpu = " --enable-nvml"

DEPENDS += " \
    libibverbs1 \
    libnl \
    librdmacm1 \
"

DEPENDS:append:dgpu = " cuda-nvml"

INSANE_SKIP:${PN} += "dev-so"
