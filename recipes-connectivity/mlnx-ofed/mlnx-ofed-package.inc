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

require mlnx-ofed-common.inc

do_patch[noexec] = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    if [ -d ${S}/usr ]; then
        install -d ${D}${prefix}
        cp -rd --no-preserve=ownership ${S}/usr/* ${D}${prefix}
    fi
    if [ -d ${S}/etc ]; then
        install -d ${D}${sysconfdir}
        cp -rd --no-preserve=ownership ${S}/etc/* ${D}${sysconfdir}
    fi
    if [ -d ${D}${prefix}/lib/aarch64-linux-gnu ]; then
        install -d ${D}${libdir}
        mv ${D}${prefix}/lib/aarch64-linux-gnu/* ${D}${libdir}
        rm -r ${D}${prefix}/lib/aarch64-linux-gnu
    fi
    rm -rf ${D}${datadir}/lintian
}

INSANE_SKIP:${PN} += "already-stripped"
