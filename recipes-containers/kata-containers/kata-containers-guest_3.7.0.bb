# Copyright (c) 2024, NVIDIA CORPORATION. All rights reserved.
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

SUMMARY = "Default guest kernel and rootfs provided by official Kata release"
HOMEPAGE = "https://katacontainers.io/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://opt/kata/bin/kata-collect-data.sh;endline=6;md5=6be0f3eae593aa23367c1bf62bd626c6"

SRC_URI = "https://github.com/kata-containers/kata-containers/releases/download/${PV}/kata-static-${PV}-arm64.tar.xz;subdir=${BP}"
SRC_URI[sha256sum] = "23e0d5c4e38c6944729a11c631f947abc5aab19427a44d8691bfb9ad1c38b831"

PACKAGES = "${PN}"

INSTALL_DIR = "${datadir}/kata-containers"

do_install() {
    install -d ${D}${INSTALL_DIR}
    cd ${S}/opt/kata/share/kata-containers
    install -m 0644 $(readlink vmlinux.container) ${D}${INSTALL_DIR}
    ln -s $(readlink vmlinux.container) ${D}${INSTALL_DIR}/vmlinux.container
    install -m 0644 $(readlink kata-containers.img) ${D}${INSTALL_DIR}
    ln -s $(readlink kata-containers.img) ${D}${INSTALL_DIR}/kata-containers.img
}

FILES:${PN} = "${INSTALL_DIR}"

do_patch[noexec] = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"
