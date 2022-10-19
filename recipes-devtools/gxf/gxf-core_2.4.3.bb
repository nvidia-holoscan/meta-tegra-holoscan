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

SUMMARY = "NVIDIA GXF Core"

LICENSE = "CLOSED"

PACKAGES = "${PN} ${PN}-dev"

def gxf_pkg_arch(d):
    arch = d.getVar('TARGET_ARCH')
    return 'arm64' if arch == 'aarch64' else arch

GXF_ARCH = "${@gxf_pkg_arch(d)}"
GXF_PACKAGE = "gxf_2.4.3_20220811_6ff6ffd4_holoscan-sdk_${GXF_ARCH}"
SRC_URI = " \
    file://${GXF_PACKAGE}.tar.gz \
    file://0001-Fix-parameter_storage-build-error.patch \
    file://0002-Remove-TypenameAsString-from-nvidia-namespace.patch \
"

S = "${WORKDIR}/${GXF_PACKAGE}"

do_install () {
    install -d ${D}/opt/nvidia/gxf
    cp -rd --no-preserve=ownership ${S}/common ${D}/opt/nvidia/gxf
    cp -rd --no-preserve=ownership ${S}/gxf ${D}/opt/nvidia/gxf
    cp -rd --no-preserve=ownership ${S}/${GXF_ARCH} ${D}/opt/nvidia/gxf
    find ${D}/opt/nvidia/gxf -regex "\(.*pybind.so\)\|\(.*python_codelet\)" | xargs rm -rf
}

FILES:${PN} += " \
    /opt/nvidia/gxf/${GXF_ARCH} \
"

FILES:${PN}-dev += " \
    /opt/nvidia/gxf/common \
    /opt/nvidia/gxf/gxf \
"

SYSROOT_DIRS = " \
    /opt/nvidia/gxf \
"

DEPENDS = " \
    tensorrt-core \
    tensorrt-plugins \
"

RDEPENDS:${PN} += " \
    libnpp \
"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP:${PN} += "dev-so"
