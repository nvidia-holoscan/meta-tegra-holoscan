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

SUMMARY = "NVIDIA Linux Open GPU Kernel Modules"
LICENSE = "MIT | GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=1d5fa2a493e937d5a4b96e5e03b90f7c"

SRC_URI = "git://github.com/NVIDIA/open-gpu-kernel-modules.git;branch=${@d.getVar('PV').split('.')[0]};protocol=https"
SRCREV = "5a1c474040e1c3ed20760267510cc9d9332898f1"

SRC_URI:append = " \
    file://0001-Enable-MOFED-peer-memory-symbols.patch \
"

inherit module

S = "${WORKDIR}/git"

MODULES_MODULE_SYMVERS_LOCATION = "kernel-open"

DEPENDS:append = " \
    mlnx-ofed-kernel-dkms \
"

EXTRA_OEMAKE += " \
    TARGET_ARCH='${HOST_ARCH}' \
    SYSSRC='${STAGING_KERNEL_DIR}' \
    SYSOUT='${STAGING_KERNEL_BUILDDIR}' \
"

# The Mellanox OFED symvers file must be explicitly provided since the mlnx-ofed recipe
# doesn't use the kernel-module prefix required for automatic inclusion by module.class
python __anonymous() {
    d.setVar('KBUILD_EXTRA_SYMBOLS', "${STAGING_INCDIR}/mlnx-ofed-kernel-dkms/Module.symvers")
}

do_install:append() {
    install -m 0644 -D ${S}/kernel-open/nvidia/nv-p2p.h ${D}${includedir}/nvidia/nv-p2p.h
}

KERNEL_MODULE_PROBECONF = "nvidia"
module_conf_nvidia = "options nvidia NVreg_OpenRmEnableUnsupportedGpus=1 NVreg_DmaRemapPeerMmio=0"

RPROVIDES:${PN} += " \
    kernel-module-nvidia \
    kernel-module-nvidia-drm \
    kernel-module-nvidia-modeset \
    kernel-module-nvidia-peermem \
    kernel-module-nvidia-uvm \
"

# This package conflicts with the Tegra kernel modules provided by nvidia-kernel-oot
# (which are typically installed with the nvidia-kernel-oot-display meta package, but
# may also be installed by accident by installing all of nvidia-kernel-oot).
RCONFLICTS:${PN} += " \
    nv-kernel-module-nvidia \
    nv-kernel-module-nvidia-drm \
    nv-kernel-module-nvidia-modeset \
    nv-kernel-module-nvidia-peermem \
    nv-kernel-module-nvidia-uvm \
"
