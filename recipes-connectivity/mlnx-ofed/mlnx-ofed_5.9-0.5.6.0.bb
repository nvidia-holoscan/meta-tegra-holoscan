# Copyright (c) 2022-2023, NVIDIA CORPORATION. All rights reserved.
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

SUMMARY = "Meta recipe for bringing in all Mellanox OFED components"
LICENSE = "CLOSED"

MLNX_OFED_INSTALL_PACKAGES = " \
    dpcp \
    ibacm \
    ibdump \
    ibsim \
    ibutils2 \
    ibverbs-providers \
    ibverbs-utils \
    infiniband-diags \
    kernel-mft-dkms \
    libibmad5 \
    libibnetdisc5 \
    libibumad3 \
    libibverbs1 \
    libopensm \
    librdmacm1 \
    libvma \
    libvma-utils \
    libxlio \
    libxlio-utils \
    mft \
    mlnx-ethtool \
    mlnx-ofed-kernel-dkms \
    mlnx-ofed-kernel-utils \
    mlnx-tools \
"

MLNX_OFED_BUILD_PACKAGES = " \
    libopenvswitch \
"

DEPENDS = "${MLNX_OFED_INSTALL_PACKAGES} ${MLNX_OFED_BUILD_PACKAGES}"
RDEPENDS:${PN} = "${MLNX_OFED_INSTALL_PACKAGES}"

# Disable unused tasks for this dummy recipe.
do_fetch[noexec] = "1"
do_unpack[noexec] = "1"
do_patch[noexec] = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_install[noexec] = "1"

ALLOW_EMPTY:${PN} = "1"
