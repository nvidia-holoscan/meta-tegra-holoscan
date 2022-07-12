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

SUMMARY = "NVIDIA cuDNN"

LICENSE = "CLOSED"

SRC_URI = "file://cudnn-${CUDA_VERSION}-${TARGET_OS}-${TARGET_ARCH}sbsa-v${PV}.tgz"

S = "${WORKDIR}/cuda"

def cuda_pkg_arch(d):
    arch = d.getVar('TARGET_ARCH')
    return 'sbsa' if arch == 'aarch64' else arch

CUDA_PKG_ARCH = "${@cuda_pkg_arch(d)}"

# Note: we need to install to the full "targets" path that is used by cuda-toolkit
#       otherwise we'll have conflicts with the symlinks used by cuda-toolkit.
CUDA_PATH = "/usr/local/cuda-${CUDA_VERSION}/targets/${CUDA_PKG_ARCH}-linux"

do_install() {
    install -d ${D}${CUDA_PATH}
    cp -rd --no-preserve=ownership ${S}/lib64 ${D}${CUDA_PATH}/lib
    cp -rd --no-preserve=ownership ${S}/include ${D}${CUDA_PATH}/include
}

FILES:${PN} = " \
    ${CUDA_PATH}/lib/*.so.* \
"

FILES:${PN}-dev = " \
    ${CUDA_PATH}/lib/*.so \
    ${CUDA_PATH}/include \
"

FILES:${PN}-staticdev = " \
    ${CUDA_PATH}/lib/*.a \
"

RDEPENDS:${PN} = " \
    cuda-toolkit \
"

SYSROOT_DIRS = " \
    ${CUDA_PATH} \
"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

DIRFILES = "1"
INSANE_SKIP:${PN} += "already-stripped libdir"
INSANE_SKIP:${PN}-dev += "already-stripped libdir"
