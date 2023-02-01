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

CUDA_PKG = "${BPN}-dev"

require cuda-binaries-common.inc

DEPENDS:tegra = "nvidia-dgpu-drivers"

DEVSUM:aarch64 = "b7339ad993631d3aed5e41c1eeac04c9f2c1169bbdda17bd17a9665811fabd8d"
DEVSUM:x86-64 = "ee491628df1b08a633d7ca580d11d6aad48bd95af8e5ddb5c382d19ce1fc3596"

ALLOW_EMPTY:${PN} = "1"
EXCLUDE_PACKAGES_FROM_SHLIBS = ""
PRIVATE_LIBS = "libcuda.so.1"

BBCLASSEXTEND = "native nativesdk"
