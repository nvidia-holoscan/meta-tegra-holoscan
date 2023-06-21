# Copyright (c) 2022-2023, NVIDIA CORPORATION. All rights reserved.
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
GXF_PACKAGE = "gxf_23.05_20230717_d105fa1c_holoscan-sdk_${GXF_ARCH}${@ '_cuda12' if float(d.getVar('CUDA_VERSION')) > 12.0 else ''}"
SRC_URI = "https://edge.urm.nvidia.com/artifactory/sw-holoscan-thirdparty-generic-local/gxf/${GXF_PACKAGE}.tar.gz"
CUDA_11_SHA = "d1679cb243f8946897eaa154af7fcef5058d064093173339e8f5ff29eadbae01"
CUDA_12_SHA = "863b1e5a8fb39d749e62c5e55be96d990ffda74f5a324ac0121a3a98e8ade3f9"
SRC_URI[sha256sum] = "${CUDA_${@ d.getVar('CUDA_VERSION').split('.')[0]}_SHA}"

SRC_URI += " \
    file://0001-Remove-TypenameAsString-from-nvidia-namespace.patch \
    file://0002-Fix-std-iterator-deprecation.patch \
    file://0003-Fix-unused-parameters.patch \
    file://0004-Fix-GxfEntityCreateInfo-initializer.patch \
    file://0005-Remove-complex-primitives-support.patch \
"

S = "${WORKDIR}/${GXF_PACKAGE}"

do_install () {
    install -d ${D}/opt/nvidia/gxf

    cp -rd --no-preserve=ownership ${S}/common ${D}/opt/nvidia/gxf
    cp -rd --no-preserve=ownership ${S}/gxf ${D}/opt/nvidia/gxf
    cp -rd --no-preserve=ownership ${S}/${GXF_ARCH} ${D}/opt/nvidia/gxf
    find ${D}/opt/nvidia/gxf -regex "\(.*pybind.so\)\|\(.*python_codelet\)" | xargs rm -rf

    # Remove currently unused extensions that use unavailable dependencies.
    rm -rf ${D}/opt/nvidia/gxf/${GXF_ARCH}/stream

    # Remove tests.
    rm -rf ${D}/opt/nvidia/gxf/${GXF_ARCH}/cuda/tests
    rm -rf ${D}/opt/nvidia/gxf/${GXF_ARCH}/test
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

RDEPENDS:${PN} += " \
    cuda-cudart \
    cuda-nvtx \
    libnpp \
    libv4l \
    tegra-libraries-cuda \
    tegra-libraries-multimedia \
    tegra-libraries-multimedia-utils \
    tegra-libraries-multimedia-v4l \
    ucx \
"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
