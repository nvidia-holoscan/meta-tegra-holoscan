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

SUMMARY = "NVIDIA Rivermax Sample Applications"
LICENSE = "CLOSED"

SRC_URI = "file://rivermax_ubuntu2004_1.20.10.tar.gz"

inherit cuda

S = "${WORKDIR}/${PV}/apps"

CUDA_HOST_CXX =  "${@cuda_extract_compiler('CXX_FOR_CUDA', d)[0]}"

EXTRA_OEMAKE:append = " \
    CC='${CUDA_HOST_CXX}' \
    NVCC='${CUDACXX} -ccbin ${CUDA_HOST_CXX}' \
    CUDA='y' \
    CFLAGS='-std=c++11 -I${RECIPE_SYSROOT}${includedir} -I${RECIPE_SYSROOT}${includedir}/mellanox -I. -O3 --sysroot=${RECIPE_SYSROOT}' \
    NVCFLAGS='-std=c++11 -I${RECIPE_SYSROOT}${includedir} -I${RECIPE_SYSROOT}${includedir}/mellanox -I. -I./cuda -O3 -m64 -DCUDA_ENABLED ${CUDAFLAGS} \
              ${@"-DTEGRA_ENABLED" if d.getVar("TEGRA_DGPU") == "0" else ""}' \
    NVLFLAGS='-L${RECIPE_SYSROOT}${libdir} -L${RECIPE_SYSROOT}/usr/local/cuda-${CUDA_VERSION}/lib -L${RECIPE_SYSROOT}/usr/local/cuda-${CUDA_VERSION}/lib/stubs -Xcompiler --sysroot=${RECIPE_SYSROOT} ${@cuda_meson_ldflags(d)} -lrivermax -lcuda \
              ${@"-lnvidia-ml" if d.getVar("TEGRA_DGPU") == "1" else ""}' \
"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/generic_sender ${D}${bindir}
}

DEPENDS = " \
    rivermax \
    cuda-nvml \
"
