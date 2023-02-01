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

MAINSUM:aarch64 = "330061e0b1ed93209b162e336d8b53d54b76fd1839dd79882c2deba363023645"
MAINSUM:x86-64 = "6f083fcd63b7f8ef7478bfb7b8aba938d8ca0b5684d610df1243302b12a54f06"

DEVSUM:aarch64 = "ae19b6895ce27e7df3e14dca38afb255bbc0288488a94887b4d5119505e66cd8"
DEVSUM:x86-64 = "ebf060e5a84fa0c19f7ab444e6be904b5e38c976baef6b1cf6c7887a2543b171"

FILES:${PN}-dev:remove = "${prefix}/local/cuda-${CUDA_VERSION}/${baselib}/*${SOLIBSDEV}"
FILES:${PN} += "${prefix}/local/cuda-${CUDA_VERSION}/${baselib}/libnvrtc-builtins.so"
FILES:${PN}-dev += "${prefix}/local/cuda-${CUDA_VERSION}/${baselib}/libnvrtc.so"
INSANE_SKIP:${PN} += "dev-so"

BBCLASSEXTEND = "native nativesdk"
