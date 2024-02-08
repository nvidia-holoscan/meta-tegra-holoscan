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

SUMMARY = "NumPy/SciPy-compatible Array Library for GPU-accelerated Computing with Python"
HOMEPAGE = "https://cupy.dev/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file:///${S}/${WHEEL_BASE}.dist-info/license.rst;md5=b459cbee05a9bd6f6fecc52c37c2cce4"

CUDA_BASE_VER = "${@ d.getVar('CUDA_VERSION').split('.')[0] }"
WHEEL_BASE = "cupy_cuda${CUDA_BASE_VER}x-${PV}"
WHEEL_PYVER = "${@ "cp" + d.getVar('PYTHON_BASEVERSION').replace('.','') }"
WHEEL_NAME = "${WHEEL_BASE}-${WHEEL_PYVER}-${WHEEL_PYVER}-manylinux2014_${TARGET_ARCH}"

SRC_URI = "https://github.com/cupy/cupy/releases/download/v${PV}/${WHEEL_NAME}.whl;downloadfilename=${WHEEL_NAME}.zip;subdir=${BP}"
SRC_URI[sha256sum] = "e8a4906da820f6ce39a3a1d18c4504da4e0faad87598761ea9d6bf8288423d69"

inherit python3-dir cuda

do_install() {
    install -d ${D}${PYTHON_SITEPACKAGES_DIR}
    cp -rd --no-preserve=ownership ${S}/* ${D}${PYTHON_SITEPACKAGES_DIR}
}

RDEPENDS:${PN} = " \
    bash \
    cuda-nvtx \
    libgomp \
    python3-fastrlock \
"

FILES:${PN} = "${PYTHON_SITEPACKAGES_DIR}"
