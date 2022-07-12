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

SUMMARY = "A small antialiased vector graphics rendering library for OpenGL"
LICENSE = "Zlib"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE.txt;md5=f803cbcf0f736b636f95e1b43b83e7f6"

SRC_URI = "git://github.com/memononen/nanovg.git;branch=master;protocol=https"
SRCREV = "397f3300bc14bdb856ec429c71c345ab0340b0a6"

SRC_URI += " \
    file://0001-Add-CMake-support.patch;patchdir=${WORKDIR}/git \
"

inherit cmake

S = "${WORKDIR}/git"
