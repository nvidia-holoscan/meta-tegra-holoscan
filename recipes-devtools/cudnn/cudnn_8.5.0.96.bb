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

def cuda_pkg_arch(d):
    arch = d.getVar('TARGET_ARCH')
    return 'sbsa' if arch == 'aarch64' else arch

CUDA_PKG_ARCH = "${@cuda_pkg_arch(d)}"
CUDNN_PKG_PATH = "cudnn-linux-${CUDA_PKG_ARCH}-${PV}_cuda11-archive"

SRC_URI = "file://${CUDNN_PKG_PATH}.tar.xz"

COMPATIBLE_MACHINE = "(tegra)"
PACKAGE_ARCH = "${TEGRA_PKGARCH}"

S = "${WORKDIR}/${CUDNN_PKG_PATH}"

do_install() {
    install -d ${D}${libdir}
    install -m 0644 ${S}/lib/* ${D}${libdir}

    install -d ${D}${includedir}
    install -m 0644 ${S}/include/* ${D}${includedir}
}

FILES:${PN} = " \
    ${libdir} \
"

FILES:${PN}-dev = " \
    ${includedir} \
"

DEPENDS = " \
    libcublas \
"

RDEPENDS:${PN} = " \
    cuda-toolkit \
    zlib \
"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

INSANE_SKIP:${PN} += "already-stripped"
