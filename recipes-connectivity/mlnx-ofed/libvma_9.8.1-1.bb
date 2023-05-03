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

SUMMARY = "Mellanox libvma"
LICENSE = "CLOSED"

require mlnx-ofed-package.inc

DEB_FILES = " \
    libvma_${PV}_arm64.deb \
    libvma-dbg_${PV}_arm64.deb \
    libvma-dev_${PV}_arm64.deb \
"

FILES_SOLIBSDEV = ""
FILES:${PN} += "${libdir}"

RDEPENDS:${PN} += " \
    dpcp \
    ibverbs-providers \
    libibverbs1 \
    libnl \
    libnl-route \
    librdmacm1 \
"

INSANE_SKIP:${PN} += "dev-so"
