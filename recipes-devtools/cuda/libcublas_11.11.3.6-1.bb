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

MAINSUM:aarch64 = "7f95783bbcd223265d4369ac0697a20eb8251bd893bb3ab96ac99014425ae206"
MAINSUM:x86-64 = "fc7c7c6b5faba8410e884ba27759c0f9cacb69432e4e9254c05fc55f4ccfa4d9"

DEVSUM:aarch64 = "7e8d5e12dc9bd566fcdef3372c86c12fbc61097ccf8a19146f8715ea1cb756bc"
DEVSUM:x86-64 = "84616862f8d45a9042bd6ea584440e2b6a6f5615c267ad2d25ab63f2a51d2428"

BBCLASSEXTEND = "native nativesdk"
