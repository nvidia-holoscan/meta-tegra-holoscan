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

SUMMARY = "NVIDIA CUDA Package Download"

LICENSE = "CLOSED"
LIC_FILES_CHKSUM = "file://${CUDA_UNPACK}/usr/local/cuda-${CUDA_DOTTED_VER}/LICENSE;md5=${LIC_CHKSUM_MD5}"

CUDA_ARCHIVE_NAME = "cuda-repo-rhel8-${CUDA_DASHED_VER}-local-${PV}_${CUDA_INCLUDED_DRIVER_VER}-1.${TARGET_ARCH}.rpm"

SRC_URI = "https://developer.download.nvidia.com/compute/cuda/${PV}/local_installers/${CUDA_ARCHIVE_NAME}"
SRC_URI[md5sum] = "${PKGSUM_MD5}"
SRC_URI[sha256sum] = "${PKGSUM_SHA256}"

BBCLASSEXTEND = "native nativesdk"

do_unpack() {
    # Unpack the main archive.
    rm -rf ${CUDA_UNPACK}
    mkdir -p ${CUDA_UNPACK}
    cd ${CUDA_UNPACK}
    rpm2cpio.sh ${DL_DIR}/${CUDA_ARCHIVE_NAME} | cpio -idmv

    # Unpack and then delete the child packages.
    for p in var/cuda-repo-rhel8-${CUDA_DASHED_VER}-local/*.rpm; do
        rpm2cpio.sh ${p} | cpio -idmv
    done
    rm -rf var/cuda-repo-rhel8-${CUDA_DASHED_VER}-local
}
