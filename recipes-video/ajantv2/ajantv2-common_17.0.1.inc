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

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=0ce9f69a0af4b70f1ae5d12b9f986694"

BRANCH="ntv2_${@d.getVar('PV').split('.')[0]}_${@d.getVar('PV').split('.')[1]}"
SRC_URI = "git://github.com/aja-video/libajantv2.git;protocol=https;branch=${BRANCH}"
# tag: ntv2_17_0_1
SRCREV = "b6acce6b135c3d9ae7a2bce966180b159ced619f"

SRC_URI += " \
    file://0001-Fix-nvidia-ko-to-module-symvers-pattern-matching.patch;patchdir=${WORKDIR}/git \
    file://0002-Update-nvidia-ko-to-module-symvers-to-support-CRC-ta.patch;patchdir=${WORKDIR}/git \
    file://0003-rdmawhacker-Restore-AJA_RDMA-build-option.patch;patchdir=${WORKDIR}/git \
    file://0004-Add-ajantv2-CMake-package-config.patch;patchdir=${WORKDIR}/git \
    file://0005-Disable-dvplowlatencydemo-for-OE-build.patch;patchdir=${WORKDIR}/git \
    file://0006-Fix-nv-p2p-symbols-for-iGPU.patch;patchdir=${WORKDIR}/git \
"
