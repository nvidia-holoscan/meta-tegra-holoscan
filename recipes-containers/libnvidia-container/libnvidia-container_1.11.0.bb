# Copyright (c) 2023, NVIDIA CORPORATION. All rights reserved.
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

SUMMARY = "NVIDIA Container Library"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://github.com/NVIDIA/libnvidia-container.git;branch=main;protocol=https"
SRCREV = "c8f267be0bac1c654d59ad4ea5df907141149977"

SRC_URI += "file://0001-Modify-makefiles-for-OE-build.patch"

S = "${WORKDIR}/git"

inherit pkgconfig

CPPFLAGS += "-I${RECIPE_SYSROOT}${includedir}/tirpc"

do_install() {
    DESTDIR=${D} oe_runmake install prefix=${prefix}
    ln -s libnvidia-container.so.${PV} ${D}${libdir}/libnvidia-container.so.1
}

DEPENDS += " \
    elfutils \
    libtirpc \
    nvidia-modprobe-utils \
"
