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
GXF_PACKAGE = "gxf_4.0_20240409_bc03d9d_holoscan-sdk_${GXF_ARCH}"
SRC_URI = "https://edge.urm.nvidia.com/artifactory/sw-holoscan-thirdparty-generic-local/gxf/${GXF_PACKAGE}.tar.gz"
SRC_URI[sha256sum] = "29679672a603c570d23290a0ea8455f7c1331c9d6952530d4affa6a534cd50db"

SRC_URI += " \
    file://0001-Remove-TypenameAsString-from-nvidia-namespace.patch \
"

S = "${WORKDIR}/gxf_236_20240409_bc03d9d_holoscan-sdk_${GXF_ARCH}"

do_install () {
    install -d ${D}/opt/nvidia/gxf
    cp -rd --no-preserve=ownership ${S}/common ${D}/opt/nvidia/gxf
    cp -rd --no-preserve=ownership ${S}/gxf ${D}/opt/nvidia/gxf
    cp -rd --no-preserve=ownership ${S}/lib ${D}/opt/nvidia/gxf
}

# The GXF library is built for and used exclusively by the Holoscan SDK, and it
# is installed to the target as part of the Holoscan SDK installation (see the
# `holoscan-sdk` recipe). This gxf-core recipe is therefore responsible for
# just fetching and extracting the package to make it available during the
# Holoscan SDK installation (where all additional installation steps and
# bitbake dependency checking is performed).

FILES:${PN}-dev += " \
    /opt/nvidia/gxf \
"

SYSROOT_DIRS = " \
    /opt/nvidia/gxf \
"

EXCLUDE_FROM_SHLIBS = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INSANE_SKIP:${PN}-dev += "dev-elf file-rdeps"
