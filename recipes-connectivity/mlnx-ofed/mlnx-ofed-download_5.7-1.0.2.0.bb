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

SUMMARY = "NVIDIA MLNX_OFED Package Download"
LICENSE = "CLOSED"

SRC_URI = "https://www.mellanox.com/downloads/ofed/MLNX_OFED-${PV}/MLNX_OFED_LINUX-${PV}-ubuntu20.04-${TARGET_ARCH}.tgz"
SRC_URI[md5sum] = "7c108889d14a8985701e83cdf1e665c6"
SRC_URI[sha256sum] = "5948b847dd686fd7ebd0c63a016104d5369dc94b920dc26fc77d2335e0f7d667"

WORKDIR = "${TMPDIR}/work-shared/mlnx-ofed-${PV}"

# Disable unused tasks for this download recipe.
do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_install[noexec] = "1"
