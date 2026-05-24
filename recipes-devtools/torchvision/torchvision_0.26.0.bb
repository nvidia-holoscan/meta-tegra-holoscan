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

SUMMARY = "Datasets, Transforms, and Models specific to computer vision"
HOMEPAGE = "https://github.com/pytorch/vision"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=bd7749a3307486a4d4bfefbc81c8b796"

SRC_URI = "git://github.com/pytorch/vision.git;protocol=https;branch=release/0.26"
SRCREV = "336d36e8db990a905498c73933e35231876e28bc"

S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "(cuda)"

inherit cmake cuda

EXTRA_OECMAKE = " \
    -DWITH_CUDA=1 \
    -DCMAKE_SKIP_RPATH=TRUE \
    -DTORCH_CUDA_ARCH_LIST=${@' '.join(['%s.%s' % (a[:-1], a[-1]) for a in d.getVar('CUDA_ARCHITECTURES').split()])} \
    -DCMAKE_CUDA_IMPLICIT_INCLUDE_DIRECTORIES=${RECIPE_SYSROOT}/usr/include \
"

# Caffe2's public/cuda.cmake (included via find_package(Torch)) defaults
# CUDA_ARCH_NAME to "Auto", which calls select_compute_arch.cmake which in
# turn invokes try_run() on a probe binary -- not supported in Yocto's
# cross-compile sysroot. Setting TORCH_CUDA_ARCH_LIST above takes the
# Manual branch and skips the try_run() call entirely.

DEPENDS += " \
    jpeg \
    libcublas \
    libpng \
    pytorch \
"

SOLIBS = "*.so*"
FILES_SOLIBSDEV = ""

INSANE_SKIP:${PN} += "buildpaths"
