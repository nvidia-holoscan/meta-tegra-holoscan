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

DESCRIPTION = "C++11/14/17 std::expected with functional-style extensions"
HOMEPAGE = "https://github.com/TartanLlama/expected"
LICENSE = "CC0-1.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=65d3616852dbf7b1a6d4b53b00626032"

SRC_URI = " \
    git://github.com/TartanLlama/expected.git;protocol=https;branch=master \
    file://0001-Search-for-Catch2-with-find_package.patch \
    file://0002-update-tl-expected-project-version.patch \
"
# tag: v1.1.0
SRCREV = "292eff8bd8ee230a7df1d6a1c00c4ea0eb2f0362"

S = "${WORKDIR}/git"

inherit cmake

EXTRA_OECMAKE:append = " -DEXPECTED_BUILD_TESTS=OFF"

ALLOW_EMPTY:${PN} = "1"
