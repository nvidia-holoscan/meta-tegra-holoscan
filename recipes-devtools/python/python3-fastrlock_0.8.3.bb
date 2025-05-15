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

SUMMARY = "Fast, re-entrant optimistic lock implemented in Cython"
HOMEPAGE = "https://github.com/scoder/fastrlock"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=452cbb4febc674b7cc090bb3418f4377"

PYPI_PACKAGE = "fastrlock"

inherit pypi setuptools3

SRC_URI[sha256sum] = "4af6734d92eaa3ab4373e6c9a1dd0d5ad1304e172b1521733c6c3b3d73c8fa5d"

DEPENDS += "python3-cython-native"

BBCLASSEXTEND = "native nativesdk"
