# Copyright (c) 2023-2025, NVIDIA CORPORATION. All rights reserved.
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

SUMMARY = "NumPy/SciPy-compatible Array Library for GPU-accelerated Computing with Python"
HOMEPAGE = "https://cupy.dev/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6b47a0b47b880f6f283bbed9af6176e5"

SRC_URI = " \
    git://github.com/cupy/cupy.git;protocol=https;nobranch=1 \
"
SRCREV = "25e552d5d679dcdc6f7cc3d81310a9b265463137"

SRC_URI:append = " file://0001-Fixups-for-cross-building-in-OE.patch"

S = "${WORKDIR}/git"

DEPENDS += " \
    jitify cuda-profiler-api cuda-cudart cuda-nvrtc cuda-nvtx \
    cuda-cccl libcublas libcufft libcurand libcusparse nccl \
    dlpack python3-cython-native python3-fastrlock-native python3-numpy-native \
"

inherit python_setuptools_build_meta cuda

do_compile() {
    export NVCC="${CUDA_NVCC_EXECUTABLE} -ccbin ${CUDAHOSTCXX} ${CUDAFLAGS}"
    export CUPY_NVCC_GENERATE_CODE=""
    export CUDA_VERSION="${CUDA_VERSION}"
    export VERBOSE=1
    python_pep517_do_compile
}

RDEPENDS:${PN} += " \
    python3-numpy \
    python3-fastrlock \
    bash \
"

INSANE_SKIP:${PN} = "buildpaths"
INSANE_SKIP:${PN}-src = "buildpaths"
