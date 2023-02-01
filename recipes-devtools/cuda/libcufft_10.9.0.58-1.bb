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

MAINSUM:aarch64 = "1e5229b478b002f675321f756ce2904057969d29c248833fa487741c0d4d683c"
MAINSUM:x86-64 = "9f90b56c83b922e4c7a972b813bd29387be05c56c6c1dbce4c2c6ef459323d28"

DEVSUM:aarch64 = "29b63f873b71d7cf33fd80e2a14921357a7334e2322ad18c30d9bc0c8ebc0ed9"
DEVSUM:x86-64 = "ef6a4db5e7ca7889cd1c9c68a7808d163e487cbdfe91ed10def587242909dcd3"

BBCLASSEXTEND = "native nativesdk"
