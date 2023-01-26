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

SUMMARY = "NVIDIA modprobe-utils static library"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "git://github.com/NVIDIA/nvidia-modprobe.git;branch=main;protocol=https"
SRCREV = "d2cda5b1de0b8c8c99a1e02f3da0722e7049a466"

SRC_URI += " \
    file://0001-Add-nvidia-modprobe-utils-static-library.patch \
    file://0002-Extern-nvidia_cap_get_device_file_attrs.patch \
"

S = "${WORKDIR}/git"

EXTRA_OEMAKE += " \
    TARGET_ARCH='${HOST_ARCH}' \
    COMPILE.c='$(CC) $(CFLAGS) $(CPPFLAGS) -c' \
"

do_compile() {
    oe_runmake modprobe-utils/libnvidia-modprobe-utils.a
}

do_install() {
    install -d ${D}${libdir}
    install -m 0644 modprobe-utils/libnvidia-modprobe-utils.a ${D}${libdir}

    install -d ${D}${includedir}
    install -m 0644 modprobe-utils/nvidia-modprobe-utils.h ${D}${includedir}
    install -m 0644 modprobe-utils/pci-enum.h ${D}${includedir}
}
