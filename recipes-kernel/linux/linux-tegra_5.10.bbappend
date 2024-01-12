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

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI:append = " \
    file://Add-nvidia-platform-t23x-mandalore-kernel-dts.patch \
    file://Add-nvidia-platform-t23x-prometheus-kernel-dts.patch \
    file://prometheus-dts-support-large-BAR1.patch \
    file://Fix-HDMI-input-pixel-format.patch \
    file://Enable-module-signing.cfg \
    file://Disable-modules-provided-by-mlnx-ofed.cfg \
"

SRC_URI:append = "${@'file://Add-MMU_NOTIFIER-dependency-in-nv-p2p-Kconfig.patch' if 'RT_PATCH' in d else ''}"

do_patch:append () {
    if [ "${@d.getVar('RT_PATCH', True)}" = "1" ]; then
        cd ${S}/scripts
        ./rt-patch.sh apply-patches
        # The below changes are needed so that the symlinks are 
        # relative to the local directories and not absolute
        cd ${S}/arch/arm64/configs/
        mv .updated.defconfig updated.defconfig
        mv .orig.defconfig orig.defconfig
        ln -sfn updated.defconfig defconfig
        ln -sfn defconfig tegra_defconfig
    fi
}