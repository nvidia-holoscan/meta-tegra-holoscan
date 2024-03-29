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

SUMMARY = "Mellanox Firmware Burning and Diagnostics Tools"
LICENSE = "GPL-2.0-only & BSD-2-Clause"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=79e20039679d6414176a6a04804e40be"

SRC_URI = "git://github.com/Mellanox/mstflint.git;branch=master;protocol=https"
SRCREV = "885b2418543128ba2ae6a8095f39002d7c56c0ef"

SRC_URI += " \
    file://0001-Remove-host-paths-from-build.patch \
    file://0002-Remove-gitversion.h-include.patch \
    file://0003-Fix-include-and-library-build-paths.patch \
    file://0004-Remove-duplicate-pylib_DATA-files.patch \
    file://0005-Remove-EXTERNAL-build-flag.patch \
"

inherit autotools pkgconfig

S = "${WORKDIR}/git"

EXTRA_OECONF = "TOOLS_GIT_SHA=${SRCREV}"
TARGET_CC_ARCH += "${LDFLAGS}"

DEPENDS = " \
    libibmad5 \
    libibverbs1 \
    openssl \
    zlib \
"
