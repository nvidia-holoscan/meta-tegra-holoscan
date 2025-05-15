# Copyright (c) 2025, NVIDIA CORPORATION. All rights reserved.
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

DESCRIPTION = "CMake's missing package manager. A small CMake script for \
  setup-free, cross-platform, reproducible dependency management."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=39b0e14f0c3e37b2643eba051401548f"

SRC_URI = "git://github.com/cpm-cmake/CPM.cmake.git;protocol=https;nobranch=1"
#tag: v0.38.5
SRCREV = "d6d5d0d5abca0b9ffe253353f75befc704e81bec"

S = "${WORKDIR}/git"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}${datadir}/cpm
    install -m 0644 ${S}/cmake/CPM.cmake ${D}${datadir}/cpm/CPM_${PV}.cmake
}

ALLOW_EMPTY:${PN} = "1"
FILES:${PN}-dev += "${datadir}/cpm"
