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

MLNX_OFED_VER = "5.7-1.0.2.0"

DEPENDS = " \
    mlnx-ofed-download \
"

do_unpack[depends] += "xz-native:do_populate_sysroot"

DEB_PACKAGE_SUFFIX ?= "arm64"

do_unpack() {
    rm -rf ${S}
    mkdir ${S}
    cd ${S}
    ar x ${TMPDIR}/work-shared/mlnx-ofed-${MLNX_OFED_VER}/MLNX_OFED_LINUX-${MLNX_OFED_VER}-ubuntu20.04-aarch64/DEBS/${PN}_${PV}_${DEB_PACKAGE_SUFFIX}.deb
    tar xf data.tar.xz
    rm control.tar.xz data.tar.xz debian-binary
}

INSANE_SKIP:${PN} += "already-stripped"
