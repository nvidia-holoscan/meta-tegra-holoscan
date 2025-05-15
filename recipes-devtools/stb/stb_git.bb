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

DESCRIPTION = "single-file libraries for C/C++"
HOMEPAGE = "https://github.com/nothings/stb"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fb994481c55623ef338885280e676f3b"

SRC_URI = "git://github.com/nothings/stb.git;protocol=https;nobranch=1"
SRCREV = "af1a5bc352164740c1cc1354942b1c6b72eacb8a"

PV = "0.0+git"

S = "${WORKDIR}/git"

do_install() {
    install -d ${D}${includedir}/stb
    for file in ${S}/*.h
    do
        install -m 0644 $file ${D}${includedir}/stb/
    done
}

ALLOW_EMPTY:${PN} = "1"
FILES:${PN}-dev += "${includedir}/stb"
