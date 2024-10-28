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

MAINSUM:dgpu:aarch64 = "ed12e4e280119c55a7bf7eadf483237c121ca80cf9dfea3c0ab1699429f0a58f"
MAINSUM:dgpu:x86-64 = "d635f608b882f188983960a2207dbc87afe1047aaa9c32ac8be9b6ceb19e6459"

DEVSUM:dgpu:aarch64 = "fa92ac214b6d132ae4d526e012797d01fa1283cf2a816d534ba806b787aa14fb"
DEVSUM:dgpu:x86-64 = "3a2b6bb297dc8c3830c09c2eda367e2df6ed63f098ddbbb706bb858c37c3e5b2"

RDEPENDS:${PN}-stubs += "glibc"
