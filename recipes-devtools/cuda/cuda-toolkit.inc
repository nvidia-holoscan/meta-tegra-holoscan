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

SUMMARY = "NVIDIA CUDA Toolkit"

LICENSE = "CLOSED"

DEPENDS = "cuda-package-download"

PACKAGES = "${PN} ${PN}-dev"

BBCLASSEXTEND = "native"

do_install() {
    install -d ${D}/usr/local
    cp -rd --no-preserve=ownership ${CUDA_UNPACK}/usr/local/cuda-${CUDA_DOTTED_VER} ${D}/usr/local
    ln -sf cuda-${CUDA_DOTTED_VER} ${D}/usr/local/cuda-${CUDA_MAJOR_VER}
    ln -sf cuda-${CUDA_MAJOR_VER} ${D}/usr/local/cuda

    install -d ${D}${sysconfdir}
    cp -rd --no-preserve=ownership ${CUDA_UNPACK}/etc/ld.so.conf.d ${D}${sysconfdir}

    install -d ${D}${libdir}
    cp -rd --no-preserve=ownership ${CUDA_UNPACK}/usr/lib64/pkgconfig ${D}${libdir}
    cp -rd --no-preserve=ownership ${CUDA_UNPACK}/usr/lib64/libXNVCtrl.so* ${D}${libdir}

    install -d ${D}${includedir}
    cp -rd --no-preserve=ownership ${CUDA_UNPACK}/usr/include/NVCtrl ${D}${includedir}
}

FILES:${PN} = " \
    /usr/local/cuda-${CUDA_DOTTED_VER}/targets/${CUDA_PKG_ARCH}-linux/lib/*.so.* \
    /usr/local/cuda-${CUDA_DOTTED_VER}/lib64 \
    /usr/local/cuda-${CUDA_MAJOR_VER} \
    /usr/local/cuda \
    ${sysconfdir} \
"

FILES:${PN}-dev = " \
    /usr/local/cuda-${CUDA_DOTTED_VER}/* \
    ${libdir} \
    ${includedir} \
"

RDEPENDS:${PN}:class-target = " \
    nvidia-driver (>= ${CUDA_INCLUDED_DRIVER_VER}) \
    glibc \
    libgcc \
    libstdc++ \
"

RDEPENDS:${PN}-dev:class-target = " \
    gcc (<= 10) \
    glibc \
    libgcc \
    libstdc++ \
    libx11 \
    libxext \
    bash \
    expat \
    perl \
"

sysroot_stage_dirs:append() {
    sysroot_stage_dir $from/usr/local/cuda-${CUDA_DOTTED_VER} $to$prefix/local/cuda-${CUDA_DOTTED_VER}
}

do_fetch[noexec] = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"

EXCLUDE_FROM_SHLIBS = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP:${PN} += "ldflags already-stripped libdir"
INSANE_SKIP:${PN}-dev += "dev-elf ldflags already-stripped libdir staticdev"
