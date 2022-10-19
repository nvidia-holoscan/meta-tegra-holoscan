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

MAINSUM:aarch64 = "89bbfe965582164ddaef0171bdfbda9c09f2fcd7adb4c42fab008d486c1e3001"
MAINSUM:x86-64 = "cc00af016899e2c35c3c02ea20a528dbfe91f69b47bc6166b1e16d631e8d069a"

DEVSUM:aarch64 = "7880d1f06a684a116176a8e8bd97441bef661148f3000ddaaa2a265dbf33cad9"
DEVSUM:x86-64 = "bcea3e78da155399cc3b81d51c10294759098b65ebb837c333d924fecb666147"

RDEPENDS:${PN} = "libcublas"

BBCLASSEXTEND = "native nativesdk"
