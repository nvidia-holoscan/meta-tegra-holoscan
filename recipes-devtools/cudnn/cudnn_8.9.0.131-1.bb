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

inherit nvidia_deb_pkgfeed

# This recipe builds on top of the meta-tegra version.
require recipes-devtools/cudnn/cudnn_8.6.0.166-1.bb

SRC_COMMON_DEBS = "\
    libcudnn8_${PV}+cuda${CUDA_VERSION}_arm64.deb;name=lib;subdir=cudnn \
    libcudnn8-dev_${PV}+cuda${CUDA_VERSION}_arm64.deb;name=dev;subdir=cudnn \
"

SRC_URI[lib.sha256sum] = "4583c7730d53de98278c55e3beb329dc4df57ad3b4213df9a7330777e3516d06"
SRC_URI[dev.sha256sum] = "cacb8a3179eaf142e584da1638f4717480cda6f76885f416828d34c14bb5260a"
