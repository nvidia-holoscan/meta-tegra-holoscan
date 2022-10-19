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

MAINSUM:aarch64 = "fa289ad608f11cf89c60a87896ec34e6fc34f72fe458ead39d7d1b5ad6f721ef"
MAINSUM:x86-64 = "cfe420d8d7cee76548598983c17eea63d45c75a94f3e9f847b6c7494f0b78339"

DEVSUM:aarch64 = "06e88ffc2d9e698feb23507cd3ea9032085a8341e683b015fdf7bbf2f931ce15"
DEVSUM:x86-64 = "6db6a38a9423dd3698fe3a3a4433910b28d6668cb0b2bc38ff61ffdbf86169b5"

BBCLASSEXTEND = "native nativesdk"
