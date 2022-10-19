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

SUMMARY = "NVIDIA Linux Open GPU Kernel Modules"
LICENSE = "MIT | GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=1d5fa2a493e937d5a4b96e5e03b90f7c"

SRC_URI = "git://github.com/NVIDIA/open-gpu-kernel-modules.git;branch=main;protocol=https"
SRCREV = "98553501593ef05bddcc438689ed1136f732d40a"

SRC_URI:append = " file://nvidia.conf"

inherit module

S = "${WORKDIR}/git"

MODULES_MODULE_SYMVERS_LOCATION = "kernel-open"

EXTRA_OEMAKE += " \
    TARGET_ARCH='${HOST_ARCH}' \
    SYSSRC='${STAGING_KERNEL_DIR}' \
    SYSOUT='${STAGING_KERNEL_BUILDDIR}' \
"

do_install:append() {
    install -m 0644 -D ${WORKDIR}/nvidia.conf ${D}${sysconfdir}/modprobe.d/nvidia.conf
    install -m 0644 -D ${S}/kernel-open/nvidia/nv-p2p.h ${D}${includedir}/nvidia/nv-p2p.h
}

RPROVIDES:${PN} += " \
    kernel-module-nvidia \
    kernel-module-nvidia-drm \
    kernel-module-nvidia-modeset \
    kernel-module-nvidia-peermem \
    kernel-module-nvidia-uvm \
"

RCONFLICTS:${PN} += " \
    nvidia-display-driver \
"
