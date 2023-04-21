# Copyright (c) 2023, NVIDIA CORPORATION. All rights reserved.
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

MAINSUM:aarch64 = "a00999da5d8833337776c32cc0581871a095e7954e6ba59f764382a1c22330da"
MAINSUM:x86-64 = "a0c90c96e503f27bd283e6e5e3f8086c4d4bd7abe28288b75fccbba2b6c96ea5"

DEVSUM:aarch64 = "4d46ffc4be9c48b0fedccec3d44601fe001d9f9d68f6b722b7b9037c70a4c308"
DEVSUM:x86-64 = "44662c3304cd5742a5f04c8545664ab0bb48f1949fc1e1b1348b8f4eb2b0b363"

do_compile:prepend() {
	rm ${B}/usr/local/cuda-${CUDA_VERSION}/res
}

FILES:${PN} += "${prefix}/local/cuda-${CUDA_VERSION}/res"

BBCLASSEXTEND = "native nativesdk"
