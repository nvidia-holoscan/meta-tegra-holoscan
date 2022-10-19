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

SUMMARY = "NVIDIA GPUDirect Peer Mem Module"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://README.md;md5=5a4d62b0f44a21b1e51ad898279d152e"

SRC_URI = "git://github.com/Mellanox/nv_peer_memory.git;branch=master;protocol=https"
SRCREV = "a15f0f666f40efa33b2719f80fe3d968586d2d4d"

SRC_URI += "file://0001-Makefile-changes-for-MGX-build.patch"

inherit module

S = "${WORKDIR}/git"

DEPENDS:append = " \
    mlnx-ofed-kernel-dkms \
    ${PREFERRED_RPROVIDER_kernel-module-nvidia} \
"

EXTRA_OEMAKE += " \
    OFA_KERNEL=${RECIPE_SYSROOT}${prefix}/src/mlnx-ofed-kernel-dkms \
    OFA_SYMVERS=${RECIPE_SYSROOT}${includedir}/mlnx-ofed-kernel-dkms/Module.symvers \
    NVIDIA_SYMVERS=${RECIPE_SYSROOT}${includedir}/${PREFERRED_RPROVIDER_kernel-module-nvidia}/Module.symvers \
    KDIR=${STAGING_KERNEL_DIR} \
"
