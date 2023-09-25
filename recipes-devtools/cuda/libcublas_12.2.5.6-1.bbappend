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

require cuda-sbsa.inc

MAINSUM:dgpu:aarch64 = "7b29ca772e2d52dc5140ca9f924a4f25e678c77a2246f35e99a756989426f084"
MAINSUM:dgpu:x86-64 = "e4949d3673da38dae679d151130693c5c1e280a52805d35c58bbbe68264c12b6"

DEVSUM:dgpu:aarch64 = "4e3dc77f7c65588be4e16006e755eff4d0099e5485309c1792345126ed13b3ba"
DEVSUM:dgpu:x86-64 = "dc51c47137a76589d5f6d40163059e981904eda367e6f3387a7ef675a980d8b8"

RDEPENDS:${PN}-stubs += "glibc"
