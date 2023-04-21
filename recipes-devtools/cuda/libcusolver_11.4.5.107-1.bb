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

MAINSUM:aarch64 = "56499191fe74ac2c1aff271c17f9bdae33e0914da0ad038039dc5347eb5611f2"
MAINSUM:x86-64 = "ef4d9f5daf647fdcdb4970b5a20c65b1b074bd5eb8332fea059dfd00ac78cbab"

DEVSUM:aarch64 = "6c8180bfd24beedf1ecc18c7865b1f0acbd02434b7c3530beb3688fbe7f90731"
DEVSUM:x86-64 = "fcad647b3f79acc8ceeb2bf2ec0d0efe48fa21f46ff89a3b8dc2a36d40363d92"

RDEPENDS:${PN} = " \
    libcublas \
    libcusparse \
    libnvjitlink \
"

BBCLASSEXTEND = "native nativesdk"
