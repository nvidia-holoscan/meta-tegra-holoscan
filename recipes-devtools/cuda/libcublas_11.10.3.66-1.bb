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

MAINSUM:aarch64 = "e54dc59adf4d3dff0a8ee4577dcbd5595aa541ce0012d21a8faf0dd075f259f2"
MAINSUM:x86-64 = "31dcf44ada828d1a4a5ac421498ef0a23ef4f858251e8017fb2e4780cecf869f"

DEVSUM:aarch64 = "2227e6e0ea6a03d05be468fcf5711a417663467dd4e8b9dd32be1280cf3f79be"
DEVSUM:x86-64 = "a4f6013d055ae1b990cfbc1b96497c9b8d9889974b61c8bdae1edbcdbcda0ce5"

BBCLASSEXTEND = "native nativesdk"
