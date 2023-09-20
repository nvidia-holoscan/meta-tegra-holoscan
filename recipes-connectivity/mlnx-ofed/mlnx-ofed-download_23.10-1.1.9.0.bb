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

SUMMARY = "NVIDIA MLNX_OFED Package Download"
LICENSE = "CLOSED"

SRC_URI = "https://www.mellanox.com/downloads/ofed/MLNX_OFED-${PV}/MLNX_OFED_LINUX-${PV}-ubuntu22.04-${TARGET_ARCH}.tgz"
SRC_URI[sha256sum] = "a95daa85de5d9a9ca2b8f0000fdfb15a5f664713b5c54474d2c22c62af804476"

WORKDIR = "${TMPDIR}/work-shared/mlnx-ofed-${PV}"

# Disable unused tasks for this download recipe.
do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_install[noexec] = "1"
