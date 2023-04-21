# Copyright (c) 2022-2023, NVIDIA CORPORATION. All rights reserved.
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

require cuda-binaries-common.inc

MAINSUM:aarch64 = "4484b752343ed2fd244e3e7bf5d14f61c4befb846c2043ec0fa83d18d1578751"
MAINSUM:x86-64 = "c7d8d209c7fc7ac8c229f022fcde499c98228068e7b652ad224edbb6778c35ab"

DEVSUM:aarch64 = "a36de5ae4c0bb704349b3045238838df39a3671f5d65575bcf5645a297c33bc4"
DEVSUM:x86-64 = "85277ab67c04519cd13a98859575e4f727480a44d25e2e6ecc069999138912c2"

RDEPENDS:${PN}-stubs += "glibc"

BBCLASSEXTEND = "native nativesdk"
