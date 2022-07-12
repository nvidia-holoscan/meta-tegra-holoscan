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
GXF_PACKAGE = "gxf_2.4.2_20220516_f90116f2_holoscan-sdk_${GXF_ARCH}"
SRC_URI = " \
    file://${GXF_PACKAGE}.tar.gz \
    file://0001-Fix-parameter_storage-build-error.patch \
"

S = "${WORKDIR}/${GXF_PACKAGE}"

do_install () {
    # GXF Core
    install -d ${D}/opt/nvidia/gxf-${PV}
    cp -rd --no-preserve=ownership ${S}/common ${D}/opt/nvidia/gxf-${PV}
    cp -rd --no-preserve=ownership ${S}/gxf ${D}/opt/nvidia/gxf-${PV}
    ln -s gxf-${PV} ${D}/opt/nvidia/gxf

    # GXE executable
    install -d ${D}${bindir}
    install -m 0755 ${S}/${GXF_ARCH}/gxe/gxe ${D}${bindir}

    # Libraries
    install -d ${D}${libdir}
    install -m 0644 ${S}/${GXF_ARCH}/core/libgxf_core.so ${D}${libdir}
    install -m 0644 ${S}/${GXF_ARCH}/std/libgxf_std.so ${D}${libdir}
    install -m 0644 ${S}/${GXF_ARCH}/cuda/libgxf_cuda.so ${D}${libdir}
    install -m 0644 ${S}/${GXF_ARCH}/serialization/libgxf_serialization.so ${D}${libdir}
    install -m 0644 ${S}/${GXF_ARCH}/behavior_tree/libgxf_behavior_tree.so ${D}${libdir}
    install -m 0644 ${S}/${GXF_ARCH}/multimedia/libgxf_multimedia.so ${D}${libdir}
    install -m 0644 ${S}/${GXF_ARCH}/network/libgxf_network.so ${D}${libdir}
    install -m 0644 ${S}/${GXF_ARCH}/npp/libgxf_npp.so ${D}${libdir}
    install -m 0644 ${S}/${GXF_ARCH}/tensor_rt/libgxf_tensor_rt.so ${D}${libdir}
    install -m 0644 ${S}/${GXF_ARCH}/dw/libgxf_dw.so ${D}${libdir}
}

FILES:${PN} += " \
    ${libdir} \
    ${bindir} \
"

FILES:${PN}-dev += " \
    /opt/nvidia/gxf-${PV} \
    /opt/nvidia/gxf \
"

SYSROOT_DIRS = " \
    ${libdir} \
    ${bindir} \
    /opt/nvidia/gxf-${PV} \
    /opt/nvidia/gxf \
"

RDEPENDS:${PN} += " \
    cuda-toolkit \
    tensorrt \
    cudnn \
"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP:${PN} += "dev-so"
