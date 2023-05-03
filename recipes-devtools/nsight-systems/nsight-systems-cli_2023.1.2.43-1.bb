# Copyright (c) 2023, NVIDIA CORPORATION. All rights reserved.
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

SUMMARY = "NVIDIA Nsight Systems CLI"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = "file://${WORKDIR}/${BPN}/${NSIGHT_DIR}/EULA.txt;md5=5c45accabbea4eeeb539ce4cd133d5c2"

inherit nvidia_deb_pkgfeed

PACKAGES = "${PN}"

MAIN_VER = "${@'.'.join(d.getVar('PV').split('.')[:-1])}"
NSIGHT_DIR = "/opt/nvidia/nsight-systems-cli/${MAIN_VER}"

SRC_COMMON_DEBS = "nsight-systems-cli-${MAIN_VER}_${PV}_arm64.deb;subdir=${BPN}"
SRC_URI[sha256sum] = "4bbc8f0188ef047c7293bbfa95476657c95ba902f1b266c96ce81c08f38034ce"

do_install() {
    install -d ${D}${NSIGHT_DIR}
    cp -rd --no-preserve=ownership ${WORKDIR}${NSIGHT_DIR}/target-linux-sbsa-armv8 ${D}${NSIGHT_DIR}

    # Create nsys symlink in the system bin directory.
    install -d ${D}${bindir}
    ln -s ${NSIGHT_DIR}/target-linux-sbsa-armv8/nsys ${D}${bindir}/nsys
}

FILES:${PN} = " \
    ${NSIGHT_DIR} \
    ${bindir} \
"

RDEPENDS:${PN} += " \
    glibc \
    libcomerr \
    libcurl \
    libgcc \
    libgssapi-krb5 \
    libibmad5 \
    libibumad3 \
    libibverbs1 \
    libk5crypto \
    libkrb5 \
    libstdc++ \
    ucx \
    zlib \
"

do_patch[noexec] = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"

EXCLUDE_FROM_SHLIBS = "1"
INSANE_SKIP:${PN} += "ldflags already-stripped dev-so debug-files"
