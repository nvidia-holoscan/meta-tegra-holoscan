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
    libcudnn9-cuda-12_${PV}_arm64.deb;subdir=cudnn;downloadfilename=libcudnn9-cuda-12_${PV}_arm64_sbsa.deb;sha256sum=4a46c79a25308d03d6faad9aa15da24baf14de7d77336b59a5d4a901338b2656 \
    libcudnn9-static-cuda-12_${PV}_arm64.deb;subdir=cudnn;downloadfilename=libcudnn9-static-cuda-12_${PV}_arm64_sbsa.deb;sha256sum=851380d937b3bebd8a926f7cf1702b58db1e742a10fbf586afb035f7433ba1a1 \
    libcudnn9-dev-cuda-12_${PV}_arm64.deb;subdir=cudnn;downloadfilename=libcudnn9-dev-cuda-12_${PV}_arm64_sbsa.deb;sha256sum=1dec157ce27f2718fde6215d15dc4e041cf5933e08e0bc967d2f207385cd5b9a \
"
