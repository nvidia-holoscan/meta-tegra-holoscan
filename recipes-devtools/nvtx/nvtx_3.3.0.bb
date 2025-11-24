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
LIC_FILES_CHKSUM = "file://include/nvtx3/nvtx3.hpp;endline=19;md5=d530f84c03bf0c0b89cc31e07132384b"

SRC_URI = " \
    git://github.com/NVIDIA/NVTX.git;protocol=https;nobranch=1 \
    file://0001-Updates-for-OE-cross-builds.patch \
"
# tag: v3.3.0-c-cpp
SRCREV = "7c1f2d47f153dac7a0703b162f4b2eaa0ac47c83"

S = "${WORKDIR}/git"

inherit cmake
