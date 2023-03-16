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

SUMMARY = "NVIDIA GPU Stress test"
HOMEPAGE = "https://github.com/NVIDIA/GPUStrestTest/"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://License;md5=5d5211213623d8c0032f9a9bcb4e514c"

SRC_URI = "git://github.com/NVIDIA/GPUStressTest.git;branch=2.3;protocol=https"
SRCREV = "f15f6c5f8b2d46219de1f0115d907b30ae991fbb"

SRC_URI += "file://0001-Update-CMakefile-for-OE-build.patch"

S = "${WORKDIR}/git"

inherit cmake cuda
