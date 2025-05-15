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

DESCRIPTION = "CUDA Core Compute Libraries"
HOMEPAGE = "https://github.com/NVIDIA/cccl"
LICENSE = "Apache-2.0 & BSD-3-Clause & MIT & Proprietary & BSL-1.0 & BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d77238474f6020834e49307d63d16ec5"

inherit cmake cuda

SRC_URI = " \
    git://github.com/NVIDIA/cccl.git;protocol=https;nobranch=1 \
    file://0001-Updates-for-OE-cross-builds.patch \
"
# tag: v2.2.0
SRCREV = "36f379f29660761fe033a1306ca9dab6a88cb65c"
PV .= "+git${SRCPV}"

S = "${WORKDIR}/git"
B = "${S}"

EXTRA_OECMAKE:append = " \
    -DCCCL_ENABLE_TESTING=OFF \
    -DCUB_SOURCE_DIR=${B}/cub \
    -DCUB_ENABLE_TESTING=OFF \
    -DCUB_ENABLE_EXAMPLES=OFF \
    -DTHRUST_ENABLE_HEADER_TESTING=OFF \
    -DTHRUST_ENABLE_TESTING=OFF \
    -DTHRUST_ENABLE_EXAMPLES=OFF \
    -DLIBCUDACXX_ENABLE_LIBCUDACXX_TESTS=OFF \
"

CUDA_NVCC_EXTRA_FLAGS:append = "-I${S}/cub -I${S}/libcudacxx -I${S}/thrust"
