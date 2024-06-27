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

SUMMARY = "NVIDIA Clara Viz"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=7bb1df9531208a3d5eaeaa657cc1205b"

SRC_URI = "git://github.com/NVIDIA/clara-viz.git;branch=main;protocol=https"
SRCREV = "b48d0dccfb3a20ddebd6bc23d9fe0f28a591e166"

SRC_URI += " \
    file://0001-Fix-OE-build.patch \
"

S = "${WORKDIR}/git"

inherit pkgconfig cmake cuda

EXTRA_OECMAKE:append = " \
    -DCLARA_VIZ_WITH_GRPC=OFF \
    -DCLARA_VIZ_WITH_OPENH264=OFF \
    -DCLARA_VIZ_WITH_EXAMPLES=OFF \
"

CUDA_NVCC_EXTRA_FLAGS += " \
    --use_fast_math \
    --expt-relaxed-constexpr \
"

do_install:append() {
    install -m 0644 ${S}/cmake/clara_viz_renderer-targets.cmake ${D}${libdir}/cmake/claraviz
    install -m 0644 ${S}/lib/${TARGET_ARCH}/libclara_viz_renderer.so.${PV} ${D}${libdir}
    ln -s libclara_viz_renderer.so.${PV} ${D}${libdir}/libclara_viz_renderer.so.0
    ln -s libclara_viz_renderer.so.0 ${D}${libdir}/libclara_viz_renderer.so
}

DEPENDS += " \
    nlohmann-json \
    zlib \
"

INSANE_SKIP:${PN} += "already-stripped"
