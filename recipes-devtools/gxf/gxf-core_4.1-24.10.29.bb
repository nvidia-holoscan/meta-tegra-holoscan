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

SUMMARY = "NVIDIA GXF Core"

LICENSE = "CLOSED"

PACKAGES = "${PN} ${PN}-dev"

def gxf_pkg_arch(d):
    arch = d.getVar('TARGET_ARCH')
    return 'arm64' if arch == 'aarch64' else arch

GXF_ARCH = "${@gxf_pkg_arch(d)}"
GXF_PACKAGE = "gxf_447_20241029_bf72709_holoscan-sdk_${GXF_ARCH}"
SRC_URI = "https://edge.urm.nvidia.com/artifactory/sw-holoscan-thirdparty-generic-local/gxf/${GXF_PACKAGE}.tar.gz"
SRC_URI[sha256sum] = "fca04194e5648ed62e49b2ba8ec64e96cde5814b4cec3d15b9e9bf88fa1948df"

SRC_URI += " \
    file://0001-Remove-TypenameAsString-from-nvidia-namespace.patch \
"

S = "${WORKDIR}/${GXF_PACKAGE}"

INSTALL_PATH = "${base_prefix}/opt/nvidia/gxf"

do_install () {
    install -d ${D}${INSTALL_PATH}
    cp -rd --no-preserve=ownership ${S}/common ${D}${INSTALL_PATH}
    cp -rd --no-preserve=ownership ${S}/gxf ${D}${INSTALL_PATH}
    cp -rd --no-preserve=ownership ${S}/lib ${D}${INSTALL_PATH}
    cp -rd --no-preserve=ownership ${S}/python ${D}${INSTALL_PATH}
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
INSANE_SKIP:${PN}-dev += "dev-elf file-rdeps"
