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

MAINSUM:aarch64 = "3b44e8e87d06992c18ed7edf3c3d75c1abcc237f8f76ffac8f8a2dc946fd4eac"
MAINSUM:x86-64 = "0476ff6b40b32351ed149066f4ecc092f94dc8787b2e6afb4e02f8dad6ad3a39"

DEVSUM:aarch64 = "f5a64bf7701df7715aa157f2157aa0fc64bd25ba989a3baf18a54bbe512aedf8"
DEVSUM:x86-64 = "cabef4484d35873c040e292492c50424c6c82f1ecbc004687b8d403d1545cc88"

RDEPENDS:${PN}-dev += "perl"

BBCLASSEXTEND = "native nativesdk"
