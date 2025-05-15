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

SUMMARY = "Optimized primitives for collective multi-GPU communication"
HOMEPAGE = "https://github.com/NVIDIA/nccl"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=a443d82dbb2d29c3bc9ed8be7b9b2e5d"

SRC_URI = " \
    git://github.com/NVIDIA/nccl.git;protocol=https;branch=master \
    file://0001-Fixups-for-cross-building-in-OE.patch \
"

# tag: v2.26.5-1
SRCREV = "3000e3c797b4b236221188c07aa09c1f3a0170d4"
PV .= "+git${SRCPV}"

S = "${WORKDIR}/git"

inherit cuda

DEPENDS += "coreutils-native"

do_compile () {
    export CXX="${CXX_FOR_CUDA}"
    export NVCC="${CUDA_NVCC_EXECUTABLE}"
    export CUDA_HOME="${CUDA_TOOLKIT_ROOT}"
    export NVCC_GENCODE="${CUDA_NVCC_ARCH_FLAGS}"
    export NVCUFLAGS="${CUFLAGS}"
    oe_runmake src.build
}

do_install () {
    export PREFIX="${D}${prefix}"
    oe_runmake src.install
}

INSANE_SKIP:${PN} = "ldflags"
