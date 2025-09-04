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

DESCRIPTION = "A collection of CMake modules that are useful for all CUDA RAPIDS projects"
HOMEPAGE = "https://github.com/rapidsai/rapids-cmake"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3ced5b165b4647193d419f133b7461c2"

SRC_URI = "git://github.com/rapidsai/rapids-cmake.git;protocol=https;nobranch=1"
# tag: v24.12.00
SRCREV = "4cb2123dc08ef5d47ecdc9cc51c96bea7b5bb79c"

S = "${WORKDIR}/git"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}/opt/nvidia/rapids-cmake
    cp -R --preserve=mode,links,timestamps ${S}/* ${D}/opt/nvidia/rapids-cmake
}

ALLOW_EMPTY:${PN} = "1"
SYSROOT_DIRS:append = " /opt/nvidia/rapids-cmake"

FILES:${PN}-dev += "/opt/nvidia/rapids-cmake"
RDEPENDS:${PN}-dev += "bash"

INSANE_SKIP:${PN}-dev += "staticdev"
