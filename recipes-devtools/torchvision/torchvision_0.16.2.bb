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

SRC_URI = "git://github.com/pytorch/vision.git;branch=release/0.16;protocol=https"
SRCREV = "c6f39778e636ec40a69bdbc74386818c57a65af3"

S = "${WORKDIR}/git"

inherit cmake cuda

EXTRA_OECMAKE = " \
    -DWITH_CUDA=1 \
    -DCMAKE_SKIP_RPATH=TRUE \
"

DEPENDS += " \
    jpeg \
    libpng \
    pytorch \
"

SOLIBS = "*.so*"
FILES_SOLIBSDEV = ""

INSANE_SKIP:${PN} += "buildpaths"
