# Copyright (c) 2023-2024, NVIDIA CORPORATION. All rights reserved.
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

inherit nvidia_deb_pkgfeed

# This recipe builds on top of the meta-tegra version.
require recipes-devtools/gie/tensorrt-core_8.6.2.3-1.bb

LIC_FILES_CHKSUM = "file://usr/include/aarch64-linux-gnu/NvInfer.h;endline=11;md5=117f6d17a39656035fa9d36b73ca4916"

SRC_COMMON_DEBS = "\
    libnvinfer8_${PV}+cuda12.0_arm64.deb;downloadfilename=libnvinfer8_${PV}+cuda12.0_arm64.deb;name=lib;subdir=tensorrt \
    libnvinfer-dev_${PV}+cuda12.0_arm64.deb;downloadfilename=libnvinfer-dev_${PV}+cuda12.0_arm64.deb;name=dev;subdir=tensorrt \
    libnvinfer-headers-dev_${PV}+cuda12.0_arm64.deb;downloadfilename=libnvinfer-headers-dev_${PV}+cuda12.0_arm64.deb;name=headerdev;subdir=tensorrt \
    libnvparsers8_${PV}+cuda12.0_arm64.deb;downloadfilename=libnvparsers8_${PV}+cuda12.0_arm64.deb;name=nvp;subdir=tensorrt \
    libnvparsers-dev_${PV}+cuda12.0_arm64.deb;downloadfilename=libnvparsers-dev_${PV}+cuda12.0_arm64.deb;name=nvpdev;subdir=tensorrt \
"

SRC_URI[lib.sha256sum] = "0628af63634086fc411a6ba96e4ccea586d8417ab29dbe76ffe8541782310b5b"
SRC_URI[dev.sha256sum] = "9fa8de070b28b234af0db5609594067fdac4f849a8113db12b57e5da29052a16"
SRC_URI[headerdev.sha256sum] = "987ab1f2a7c86e8e773fa03a19f801395e22c84deeb6bdc8db8e401e68071316"
SRC_URI[nvp.sha256sum] = "95a2648590856a422abf97c6503e6b10275d087092e22acb47f0b7f602a24498"
SRC_URI[nvpdev.sha256sum] = "b0eae8796b7bd74a9d5c85fb59bd090508e8b60039bd4aa65a9bce1a49575af0"

DEPENDS = ""

do_install() {
    install -d ${D}${includedir}
    install -m 0644 ${S}/usr/include/aarch64-linux-gnu/*.h ${D}${includedir}
    install -d ${D}${libdir}
    install -m 0644 ${S}/usr/lib/aarch64-linux-gnu/libnvparsers.so.${BASEVER} ${D}${libdir}
    install -m 0644 ${S}/usr/lib/aarch64-linux-gnu/libnvinfer.so.${BASEVER} ${D}${libdir}
    install -m 0644 ${S}/usr/lib/aarch64-linux-gnu/libnvinfer_builder_resource.so.${BASEVER} ${D}${libdir}
    install -m 0644 ${S}/usr/lib/aarch64-linux-gnu/libnvinfer_static.a ${D}${libdir}
    install -m 0644 ${S}/usr/lib/aarch64-linux-gnu/libnvparsers_static.a ${D}${libdir}

    ln -s libnvparsers.so.${BASEVER} ${D}${libdir}/libnvcaffe_parser.so.${MAJVER}
    ln -s libnvparsers.so.${BASEVER} ${D}${libdir}/libnvcaffe_parser.so
    ln -s libnvparsers.so.${BASEVER} ${D}${libdir}/libnvcaffe_parser.so.${BASEVER}
    ln -s libnvparsers.so.${BASEVER} ${D}${libdir}/libnvparsers.so.${MAJVER}
    ln -s libnvparsers.so.${BASEVER} ${D}${libdir}/libnvparsers.so
    ln -s libnvinfer.so.${BASEVER} ${D}${libdir}/libnvinfer.so.${MAJVER}
    ln -s libnvinfer.so.${BASEVER} ${D}${libdir}/libnvinfer.so
    ln -s libnvparsers_static.a ${D}${libdir}/libnvcaffe_parser.a

    install -d ${D}${libdir}/stubs
    install -m 0644 ${S}/usr/lib/aarch64-linux-gnu/stubs/libcublasLt_static_stub_trt.a ${D}${libdir}/stubs
    install -m 0644 ${S}/usr/lib/aarch64-linux-gnu/stubs/libcublas_static_stub_trt.a ${D}${libdir}/stubs
    install -m 0644 ${S}/usr/lib/aarch64-linux-gnu/stubs/libcudnn_static_stub_trt.a ${D}${libdir}/stubs
}
