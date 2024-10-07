# Copyright (c) 2022-2024, NVIDIA CORPORATION. All rights reserved.
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

SUMMARY = "ONNX Runtime Prebuilt Libraries"
HOMEPAGE = "https://github.com/microsoft/onnxruntime"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${B}/LICENSE;md5=0f7e3b1308cb5c00b372a6e78835732d"

SRC_URI = "https://github.com/microsoft/onnxruntime/releases/download/v${PV}/onnxruntime-linux-${TARGET_ARCH}-${PV}.tgz"
SRC_URI[sha256sum] = "c1dcd8ab29e8d227d886b6ee415c08aea893956acf98f0758a42a84f27c02851"

B = "${WORKDIR}/onnxruntime-linux-${TARGET_ARCH}-${PV}"

do_install() {
    install -d ${D}${libdir}
    cp -d --no-preserve=ownership ${B}/lib/* ${D}${libdir}

    install -d ${D}${includedir}
    cp -d --no-preserve=ownership ${B}/include/* ${D}${includedir}

    # Note that the VERSION_NUMBER file is used by the Holoscan SDK build.
    install -m 0644 ${B}/VERSION_NUMBER ${D}${prefix}
}

FILES:${PN}-dev += " \
    ${prefix}/VERSION_NUMBER \
"

SYSROOT_DIRS += " \
    ${prefix} \
"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

INSANE_SKIP:${PN} += "already-stripped"
