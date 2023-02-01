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

MAINSUM:aarch64 = "95422f12602528bab1b81e8f3d0d096fb568d71e65340555058659ba9cb9cfa0"
MAINSUM:x86-64 = "935471d4a2b133697c6221285205f8ae8edd4a8a6eb3b60f9587d24b0244c1d0"

DEVSUM:aarch64 = "ac5d150497519b50f3a5b196e23282b4dfddaae77c7971d5aa6b19f4df4e89f1"
DEVSUM:x86-64 = "96933fb6d9bde2024ff1c2aa66d80858dbc828da918a975ee8afa827001f2a80"

RDEPENDS:${PN} = "libcublas"

BBCLASSEXTEND = "native nativesdk"
