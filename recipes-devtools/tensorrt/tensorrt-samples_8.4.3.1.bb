# Copyright (c) 2022, NVIDIA CORPORATION. All rights reserved.
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

SUMMARY = "NVIDIA TensorRT Samples"

LICENSE = "CLOSED"

include tensorrt-common.inc

SRC_URI += " \
    file://0001-Fix-samples-build.patch \
"

S = "${WORKDIR}/TensorRT-${PV}/samples"

inherit cuda

DEPENDS:append = " \
    cuda-profiler-api \
    cudnn \
    tensorrt \
"

EXTRA_OEMAKE = " \
    TARGET="${TARGET_ARCH}" \
    CUDA_INSTALL_DIR="${CUDA_PATH}" \
    CUDNN_INSTALL_DIR="${CUDA_PATH}" \
    TRT_LIB_DIR="${STAGING_DIR_HOST}${libdir}" \
    PROTOBUF_INSTALL_DIR="${STAGING_DIR_HOST}" \
"

TARGET_CC_ARCH += "${LDFLAGS}"

do_install() {
    install -d ${D}${bindir}/tensorrt-samples
    install -m 0755 `find ${S}/../bin/ -maxdepth 1 -type f` ${D}${bindir}/tensorrt-samples
    cp -rd --no-preserve=ownership ${S}/../data ${D}${bindir}/tensorrt-samples
}
