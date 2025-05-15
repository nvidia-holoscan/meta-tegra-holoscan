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

DESCRIPTION = " \
  The NVIDIA Tools Extension SDK (NVTX) is a C-based Application \
  Programming Interface (API) for annotating events, code ranges, and resources \
  in your applications."
HOMEPAGE = "https://github.com/NVIDIA/NVTX"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://include/nvtx3/nvtx3.hpp;endline=15;md5=c03a55fe231fba7bd2f6d33441041844"

SRC_URI = "git://github.com/NVIDIA/NVTX.git;protocol=https;nobranch=1"
# tag: v3.1.0-c-cpp
SRCREV = "a1ceb0677f67371ed29a2b1c022794f077db5fe7"
PV .= "+git${SRCPV}"

S = "${WORKDIR}/git"

inherit cmake

do_install() {
    install -d ${D}/opt/nvidia/nvtx3/include
    cp -R --preserve=mode,links,timestamps ${S}/include/nvtx3/* ${D}/opt/nvidia/nvtx3/include/
}

ALLOW_EMPTY:${PN} = "1"
SYSROOT_DIRS:append = " /opt/nvidia/nvtx3"

FILES:${PN}-dev += "/opt/nvidia/nvtx3"
