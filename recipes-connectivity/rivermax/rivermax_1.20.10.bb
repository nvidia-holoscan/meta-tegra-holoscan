# Copyright (c) 2022-2023, NVIDIA CORPORATION. All rights reserved.
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

SUMMARY = "NVIDIA Rivermax"
LICENSE = "CLOSED"

SRC_URI = "file://rivermax_ubuntu2004_${PV}.tar.gz"

extract_deb() {
    cd ${S}
    ar -x ${WORKDIR}/${PV}/Ubuntu.20.04/deb-dist/aarch64/rivermax_14.1.13.10_arm64.deb
    tar xf data.tar.xz
    rm -rf control.tar.xz data.tar.xz debian debian-binary
}

do_unpack[depends] += "xz-native:do_populate_sysroot"
do_unpack:append() {
    bb.build.exec_func("extract_deb", d)
}

do_install() {
    install -d ${D}${libdir}
    cp -d ${S}/usr/lib/aarch64-linux-gnu/* ${D}${libdir}
    install -d ${D}${includedir}/mellanox
    cp -d ${S}/usr/include/mellanox/* ${D}${includedir}/mellanox
}

DEPENDS = " \
    dpcp \
    ibverbs-providers \
    libibverbs1 \
    libnl \
"

RDEPENDS:${PN} += " \
    libvma \
    librdmacm1 \
    mlnx-ofed \
    rivermax-license \
    ${@'nvidia-peermem' if d.getVar('TEGRA_DGPU') == '1' else ''} \
"

SOLIBS = "*.so*"
FILES_SOLIBSDEV = ""

INSANE_SKIP:${PN} += "already-stripped dev-so"
INSANE_SKIP:${PN}-dev += "dev-elf"

# The following include builds and packages the generic_sender application,
# which is still provided by the SDK instead of the Rivermax Applications
# GitHub. This should be removed once the application is moved to GitHub.
include rivermax-generic-sender.inc
