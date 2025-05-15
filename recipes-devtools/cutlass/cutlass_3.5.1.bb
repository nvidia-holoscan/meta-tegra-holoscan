# Copyright (c) 2025, NVIDIA CORPORATION. All rights reserved.
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

DESCRIPTION = "CUDA Templates for Linear Algebra Subroutines"
HOMEPAGE = "https://github.com/NVIDIA/cutlass"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=1132d6687f729bb3e7bf5d41649513d7"

SRC_URI = " \
    git://github.com/NVIDIA/cutlass.git;protocol=https;branch=main \
    file://0001-Fixups-for-cross-building-in-OE.patch \
"
# tag: v3.5.1
SRCREV = "f7b19de32c5d1f3cedfc735c2849f12b537522ee"
PV .= "+git${SRCPV}"

S = "${WORKDIR}/git"
B = "${S}"

DEPENDS += "cuda-nvrtc cuda-cudart libcublas googletest"

inherit cmake cuda

EXTRA_OECMAKE:append = " \
    -DCUDART_LIBRARY=${STAGING_DIR_HOST}/usr/local/cuda-${CUDA_VERSION}/lib/libcudart.so \
    -DCUDA_DRIVER_LIBRARY=${STAGING_DIR_HOST}/usr/local/cuda-${CUDA_VERSION}/lib/stubs/libcuda.so \
    -DNVRTC_LIBRARY=${STAGING_DIR_HOST}/usr/local/cuda-${CUDA_VERSION}/lib/libnvrtc.so \
    -DCUTLASS_NVCC_ARCHS_SUPPORTED="${@d.getVar('CUDA_ARCHITECTURES') if d.getVar('CUDA_ARCHITECTURES') else 'OFF'}" \
    -DCUTLASS_ENABLE_TESTS=OFF \
    -DCUTLASS_ENABLE_PROFILER=OFF \
    -DCUTLASS_ENABLE_TOOLS=ON \
    -DCUTLASS_ENABLE_EXAMPLES=ON \
    -DCUTLASS_INCLUDE_DIR=${S}/include \
    -DCUTLASS_TOOLS_UTIL_INCLUDE_DIR=${S}/tools/util/include \
"

do_install:append() {
    install -d ${D}${includedir}/41_fused_multi_head_attention
    cp -R --preserve=mode,links,timestamps ${S}/examples/41_fused_multi_head_attention/* ${D}${includedir}/41_fused_multi_head_attention/
}

PACKAGES += "${PN}-test"
FILES:${PN}-dev += "${includedir}/41_fused_multi_head_attention"
FILES:${PN}-test += "${prefix}/test"

INSANE_SKIP:${PN} = "dev-deps buildpaths"
INSANE_SKIP:${PN}-dev = "dev-elf"
