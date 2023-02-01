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

MAINSUM:aarch64 = "c92d66864653aaa22a00c2931615fda6de46ba984c8c4daf2eb9d3200ffec13c"
MAINSUM:x86-64 = "7922e002784d4569902b2e0c3ac0396411e61e64a7bc4ac62c6bda678c4a590a"

DEVSUM:aarch64 = "2364edd47c3b1d93eea2f3af82cd3c2c577341c38e78d42c8292b9724db31cf5"
DEVSUM:x86-64 = "3af21112a3a26ddd72e0dcde81f6af8973a817bdcce8d99ed06e81d6a667f9c0"

RDEPENDS:${PN}-dev += "perl"

BBCLASSEXTEND = "native nativesdk"
