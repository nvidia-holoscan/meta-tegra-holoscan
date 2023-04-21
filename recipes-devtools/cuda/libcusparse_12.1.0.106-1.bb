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

MAINSUM:aarch64 = "0c02c7e2bebe3568b665ee120f4c521622468bbd1dd3bac7cb7459770ea44795"
MAINSUM:x86-64 = "c57ec6267e3623817e6e6adfb9f7603c96ab72a71d594ee6626b2752bc126934"

DEVSUM:aarch64 = "dc13fed14afba1cc7525de4fe38bdb6b140e4862e91cb885821b2000d6ffc238"
DEVSUM:x86-64 = "7a259ef67b42adc2a9285d041a8b62f16c966d181d9b9b08ef3a9e733e1353a8"

RDEPENDS:${PN} += "libnvjitlink"
RDEPENDS:${PN}-stubs += "libnvjitlink"

BBCLASSEXTEND = "native nativesdk"
