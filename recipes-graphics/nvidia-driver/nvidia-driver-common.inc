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

LICENSE = "CLOSED"

inherit nvidia_deb_pkgfeed

PACKAGES = "${PN}-doc ${PN}"

MAJVER = "${@d.getVar('PV').split('.')[0]}"

SRC_COMMON_DEBS = "${BPN}-${MAJVER}_${PV}-0ubuntu1_arm64.deb;subdir=${BP}"

do_install() {
    if [ -d ${S}${libdir} ]; then
        install -d ${D}${libdir}
        cp -rd --no-preserve=ownership ${S}${libdir}/* ${D}${libdir}
        if [ -d ${D}${libdir}/aarch64-linux-gnu ]; then
            mv ${D}${libdir}/aarch64-linux-gnu/* ${D}${libdir}
            rm -rf ${D}${libdir}/aarch64-linux-gnu
        fi
    fi
    if [ -d ${S}${bindir} ]; then
        install -d ${D}${bindir}
        cp -rd --no-preserve=ownership ${S}${bindir}/* ${D}${bindir}
    fi
    if [ -d ${S}${sysconfdir} ]; then
        install -d ${D}${sysconfdir}
        cp -rd --no-preserve=ownership ${S}${sysconfdir}/* ${D}${sysconfdir}
    fi
    if [ -d ${S}${datadir} ]; then
        install -d ${D}${datadir}
        cp -rd --no-preserve=ownership ${S}${datadir}/* ${D}${datadir}
    fi
}

FILES:${PN} += " \
    ${libdir} \
    ${datadir} \
"

SOLIBS = "*.so*"

INSANE_SKIP:${PN} = "ldflags dev-so"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_SYSROOT_STRIP = "1"
