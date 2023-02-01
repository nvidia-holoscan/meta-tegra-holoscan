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

MAINSUM:aarch64 = "660d0bb202eab721396e5de46eac05ea47178690b1c53aa7f63832be1e57b476"
MAINSUM:x86-64 = "6f75e8540f50dd3461ccc2efa6352b80c81a30de7e43ffa703cb8f044d8bfcd3"

DEVSUM:aarch64 = "3804bbf164f6af6e317f7fa27757d97dd3da77dcc7e47744f7591a6d20a90811"
DEVSUM:x86-64 = "1e7d6cde014143f9eeb6d5e6383f6cca301e3746af5409e2d072bf35ccff8ff4"

BBCLASSEXTEND = "native nativesdk"
