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

DESCRIPTION = "RAPIDS Memory Manager"
HOMEPAGE = "https://github.com/rapidsai/rmm"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

inherit cmake pkgconfig cuda

SRC_URI = " \
    git://github.com/rapidsai/rmm.git;protocol=https;nobranch=1 \
    file://0001-Updates-for-OE-cross-builds.patch \
"
# tag: v24.04.00
SRCREV = "8f19c9c3aacf6e612a0cd61f4cf882903bf045aa"
PV .= "+git${SRCPV}"

S = "${WORKDIR}/git"

DEPENDS += "fmt spdlog cccl cpm-cmake rapids-cmake"

EXTRA_OECMAKE:append = " \
    -DCPM_SOURCE_CACHE=${RECIPE_SYSROOT}${datadir} \
    -DRAPIDS_CMAKE_DIR=${RECIPE_SYSROOT}/opt/nvidia/rapids-cmake \
    -DBUILD_TESTS=OFF \
"
