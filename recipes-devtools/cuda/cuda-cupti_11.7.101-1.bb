# Copyright (c) 2022, NVIDIA CORPORATION. All rights reserved.
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

MAINSUM:aarch64 = "da137e5e7ded705ff6131ed024f2d836cf6a86bd7a1357e08546c99f95bf3aaa"
MAINSUM:x86-64 = "d6f6e92319f070fa29719bd661b411e4f8349be71951e9793da9fba105055ece"

DEVSUM:aarch64 = "38d810415e210582dac8d1461875dd6882ac7124c4d9ef5600dd7125f2479ef9"
DEVSUM:x86-64 = "e94144e8645f3b75fbbd86d95960271f4eab258684c7381246d5fd8e167298cd"

RDEPENDS:${PN}-dev += "perl"

BBCLASSEXTEND = "native nativesdk"
