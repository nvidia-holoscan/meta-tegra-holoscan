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

inherit module

do_compile[depends] += "virtual/kernel:do_shared_workdir"
EXTRA_OEMAKE:append = " SYSSRC=${STAGING_KERNEL_DIR} KERNEL_OUTPUT=${STAGING_KERNEL_BUILDDIR}"
RCONFLICTS:${PN} += "kernel-module-nvgpu kernel-module-tegra-udrm"

do_install:append() {
    install -d ${D}${prefix}/src/nvidia-${PV}
    cp -rd --no-preserve=ownership ${NVIDIA_SRC}/kernel/* ${D}${prefix}/src/nvidia-${PV}
    ln -sf nvidia-${PV} ${D}${prefix}/src/nvidia
}

FILES:${PN}-dev += " \
    ${prefix}/src \
"

SYSROOT_DIRS += " \
    ${prefix}/src \
"
