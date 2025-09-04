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

DESCRIPTION = "A fast multi-producer, multi-consumer lock-free concurrent queue for C++11"
HOMEPAGE = "https://github.com/cameron314/concurrentqueue"
LICENSE = "BSD-2-Clause & BSL-1.0"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=3e3bd5d3367ca1cdb53ff11ab44322ea"

inherit cmake

SRC_URI = "git://github.com/cameron314/concurrentqueue.git;protocol=https;nobranch=1"
SRCREV = "6dd38b8a1dbaa7863aa907045f32308a56a6ff5d"

S = "${WORKDIR}/git"
B = "${S}"
