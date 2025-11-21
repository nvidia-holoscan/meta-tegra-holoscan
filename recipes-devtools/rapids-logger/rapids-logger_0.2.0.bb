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

DESCRIPTION = "A logging interface for RAPIDS built on spdlog"
HOMEPAGE = "https://github.com/rapidsai/rapids-logger"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI = " \
    git://github.com/rapidsai/rapids-logger.git;protocol=https;nobranch=1 \
    file://0001-Updates-for-OE-cross-builds.patch \
"
SRCREV = "9db6afe15ea80766bbfb65747a9189b1d671df5b"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

DEPENDS += "fmt spdlog cpm-cmake rapids-cmake"

EXTRA_OECMAKE:append = " \
    -DCPM_SOURCE_CACHE=${RECIPE_SYSROOT}${datadir} \
    -DRAPIDS_CMAKE_DIR=${RECIPE_SYSROOT}/opt/nvidia/rapids-cmake \
    -Dspdlog_DIR=${RECIPE_SYSROOT}${libdir}/cmake/spdlog \
    -DBUILD_TESTS=OFF \
"

SOLIBS = "*.so*"
FILES_SOLIBSDEV = ""
