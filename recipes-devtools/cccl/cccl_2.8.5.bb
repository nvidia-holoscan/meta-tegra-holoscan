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
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "https://github.com/NVIDIA/cccl/releases/download/v${PV}/cccl-v${PV}.tar.gz"
SRC_URI[sha256sum] = "93a4d704fd5179293b392e57cdc98df16cffca613b33fbaded395eb3e35125e4"
UPSTREAM_CHECK_REGEX = "releases/tag/v(?P<pver>\d+(\.\d+)+)"
UPSTREAM_CHECK_URI = "https://github.com/NVIDIA/cccl/releases/"


SRC_URI:append = " file://0001-Updates-for-OE-cross-builds.patch"

inherit cuda

S = "${WORKDIR}/cccl-v${PV}"
B = "${S}"

do_install() {
    install -d ${D}${prefix}/local/cuda-${CUDA_VERSION}/
    cp -rd --no-preserve=ownership ${B}/include ${D}${prefix}/local/cuda-${CUDA_VERSION}/
    install -d ${D}${prefix}/local/cuda-${CUDA_VERSION}/lib
    cp -rd --no-preserve=ownership ${B}/lib/cmake ${D}${prefix}/local/cuda-${CUDA_VERSION}/lib/
}

FILES:${PN} += " \
    ${prefix}/local/cuda-${CUDA_VERSION}/include \
    ${prefix}/local/cuda-${CUDA_VERSION}/lib \
"
FILES:${PN}-dev = ""

SYSROOT_DIRS:append = " ${prefix}/local/cuda-${CUDA_VERSION}"

PROVIDES += "cuda-cccl"
RPROVIDES:${PN} += "cuda-cccl"
RCONFLICTS:${PN} = "cuda-cccl"
RREPLACES:${PN} = "cuda-cccl"

BBCLASSEXTEND = "native nativesdk"
