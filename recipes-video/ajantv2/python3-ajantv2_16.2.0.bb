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

SUMMARY = "AJA NTV2 Python3 Module"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${S}/ajantv2.i;beginline=0;endline=21;md5=42d8804c74790962ad75b8205c6bea74"

SRC_URI = "file://${BPN}"

S = "${WORKDIR}/${BPN}"

inherit cmake python3-dir

DEPENDS += " \
    ajantv2-sdk \
    python3 \
    swig-native \
"

EXTRA_OECMAKE = "-DPYTHON_SITE_PACKAGES=${PYTHON_SITEPACKAGES_DIR}"

FILES:${PN} = " \
    ${PYTHON_SITEPACKAGES_DIR} \
"
