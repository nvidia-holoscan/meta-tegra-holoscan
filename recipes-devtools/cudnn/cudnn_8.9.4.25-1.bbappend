# Copyright (c) 2023-2024, NVIDIA CORPORATION. All rights reserved.
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

L4T_DEB_FEED_BASE:dgpu = "https://developer.download.nvidia.com/compute/cuda/repos/ubuntu2204/sbsa"
L4T_DEB_FEED_SKIP_POOL_APPEND:dgpu = "1"

# We patch the download name of SBSA packages to append 'sbsa' so that the iGPU and
# dGPU downloads do not conflict if the tree is ever used to build both configurations.
SRC_COMMON_DEBS:dgpu = "\
    libcudnn8_${PV}+cuda12.2_arm64.deb;subdir=cudnn;downloadfilename=libcudnn8_${PV}+cuda12.2_arm64_sbsa.deb;sha256sum=0de94609f6936e44b3ece98626ad4d4590376e163d0cbc12d721ba4f7ac566a4 \
    libcudnn8-dev_${PV}+cuda12.2_arm64.deb;subdir=cudnn;downloadfilename=libcudnn8-dev_${PV}+cuda12.2_arm64_sbsa.deb;sha256sum=5c3dca83efe325cad7cc74f3a11e48c6111f779e4e1098adc6e1b7a548956033 \
"
