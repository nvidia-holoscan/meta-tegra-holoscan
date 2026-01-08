# Copyright (c) 2022-2026, NVIDIA CORPORATION. All rights reserved.
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

SUMMARY = "NVIDIA GXF Core"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://gxf-install/include/gxf/core/gxf.h;endline=16;md5=ffcd9e991308047ac45f73c0d6b7dea0"

PACKAGES = "${PN} ${PN}-dev"

GXF_VERSION = "${@d.getVar('PV').replace('-', '_')}"
GXF_PACKAGE = "gxf_${GXF_VERSION}_holoscan-sdk-cu12_${TARGET_ARCH}"
SRC_URI = "https://edge.urm.nvidia.com/artifactory/sw-holoscan-thirdparty-generic-local/gxf/${GXF_PACKAGE}.tar.gz;name=gxf;subdir=${GXF_PACKAGE}"
SRC_URI[gxf.sha256sum] = "4c4ec192dd3bc5ac4a170ae571a3eb38978510c2957589efe2f7df0a925b3dc4"

SRC_URI += " \
    file://0001-OE-cross-build-fixups.patch \
"

S = "${WORKDIR}/${GXF_PACKAGE}"

INSTALL_PATH = "${base_prefix}/opt/nvidia/gxf"

do_install () {
    install -d ${D}${INSTALL_PATH}
    cp -rd --no-preserve=ownership ${S}/gxf-install/bin ${D}${INSTALL_PATH}
    cp -rd --no-preserve=ownership ${S}/gxf-install/include ${D}${INSTALL_PATH}
    cp -rd --no-preserve=ownership ${S}/gxf-install/lib ${D}${INSTALL_PATH}
}

# The GXF library is built for and used exclusively by the Holoscan SDK, and it
# is installed to the target as part of the Holoscan SDK installation (see the
# `holoscan-sdk` recipe). This gxf-core recipe is therefore responsible for
# just fetching and extracting the package to make it available during the
# Holoscan SDK installation (where all additional installation steps and
# bitbake dependency checking is performed).

FILES:${PN}-dev += " \
    ${INSTALL_PATH} \
"

SYSROOT_DIRS = " \
    ${INSTALL_PATH} \
"

EXCLUDE_FROM_SHLIBS = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INSANE_SKIP:${PN}-dev += "dev-elf file-rdeps staticdev"
