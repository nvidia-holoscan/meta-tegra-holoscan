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

DEVSUM:aarch64 = "10280a8631ac84cd15c385f8c12f0478bb3554774a90ba8742ccf09916935168"
DEVSUM:x86-64 = "66bee76aec1d59aad169526d7ac6323304baf4195a23730822f4776996cf1408"

ALLOW_EMPTY:${PN} = "1"
FILES:${PN}-dev += "${prefix}/local/cuda-${CUDA_VERSION}/nvml/example"
EXCLUDE_PACKAGES_FROM_SHLIBS = ""
PRIVATE_LIBS = "libnvidia-ml.so.1"

BBCLASSEXTEND = "native nativesdk"
