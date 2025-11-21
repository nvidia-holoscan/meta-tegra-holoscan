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

DESCRIPTION = "Very fast, header only, C++ logging library."
HOMEPAGE = "https://github.com/gabime/spdlog/wiki"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9573510928429ad0cbe5ba4de77546e9"

SRC_URI = " \
    git://github.com/gabime/spdlog.git;protocol=https;branch=v1.x \
    file://CVE-2025-6140.patch \
"
SRCREV = "27cb4c76708608465c413f6d0e6b8d99a4d84302"

DEPENDS = "fmt"

S = "${WORKDIR}/git"

inherit cmake

EXTRA_OECMAKE += " \
    -DSPDLOG_INSTALL=ON \
    -DSPDLOG_BUILD_SHARED=ON \
    -DSPDLOG_BUILD_EXAMPLE=OFF \
    -DSPDLOG_FMT_EXTERNAL=ON \
"

BBCLASSEXTEND = "native"
