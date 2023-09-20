# Copyright (c) 2022-2024, NVIDIA CORPORATION. All rights reserved.
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

MLNX_OFED_VER = "23.10"
MLNX_OFED_PATCH_VER = "1.1.9.0"

DEB_FILES ?= "${PN}_${PV}_arm64.deb"

do_fetch[noexec] = "1"

do_unpack[depends] += " \
    mlnx-ofed-download:do_unpack \
    xz-native:do_populate_sysroot \
"

do_unpack() {
    rm -rf ${S}
    mkdir ${S}
    cd ${S}
    for deb in ${DEB_FILES}; do
        ar x ${TMPDIR}/work-shared/mlnx-ofed-${MLNX_OFED_VER}-${MLNX_OFED_PATCH_VER}/MLNX_OFED_LINUX-${MLNX_OFED_VER}-${MLNX_OFED_PATCH_VER}-ubuntu22.04-aarch64/DEBS/${deb}
        tar xf data.tar.*
        rm -f control.tar.* data.tar.* debian-binary
    done
}
