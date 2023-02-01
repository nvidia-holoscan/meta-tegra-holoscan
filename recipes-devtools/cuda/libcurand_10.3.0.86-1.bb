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

MAINSUM:aarch64 = "80a2c233d81d81d9e2958c76b08c5af3413b7a7ee3214c5039a39936ce1532be"
MAINSUM:x86-64 = "1af8f0163cf37874fd7f4c4ed2e0f77e6676badda2572773c559c0a42e371dde"

DEVSUM:aarch64 = "dd892e3ded7ebca64513ca8cfdd3c890b313188292c67429f6fab5c7429a3f1e"
DEVSUM:x86-64 = "e94801f76bb6af2c09f9d4166fb33478d08ae205355f3f2d7ae01baff9320ff9"

BBCLASSEXTEND = "native nativesdk"
