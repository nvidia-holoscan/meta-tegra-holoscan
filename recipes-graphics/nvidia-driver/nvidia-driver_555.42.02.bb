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

DESCRIPTION = "Meta-package for bringing in NVIDIA drivers and tools"
LICENSE = "CLOSED"

NVIDIA_DRIVER_PACKAGES = " \
    libnvidia-cfg1 \
    libnvidia-common \
    libnvidia-compute \
    libnvidia-decode \
    libnvidia-encode \
    libnvidia-extra \
    libnvidia-fbc1 \
    libnvidia-gl \
    nvidia-compute-utils \
    nvidia-firmware \
    nvidia-kernel-common \
    nvidia-modprobe \
    nvidia-settings \
    nvidia-utils \
    xserver-xorg-video-nvidia (= ${PV}) \
"

DEPENDS = "${NVIDIA_DRIVER_PACKAGES}"
RDEPENDS:${PN} = "${NVIDIA_DRIVER_PACKAGES}"

do_fetch[noexec] = "1"
do_unpack[noexec] = "1"
do_patch[noexec] = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_install[noexec] = "1"

ALLOW_EMPTY:${PN} = "1"
