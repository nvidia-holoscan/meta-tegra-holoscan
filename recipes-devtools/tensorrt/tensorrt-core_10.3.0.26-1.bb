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

inherit nvidia_deb_pkgfeed

# This recipe builds on top of the meta-tegra version.
require recipes-devtools/gie/tensorrt-core_10.3.0.30-1.bb

LIC_FILES_CHKSUM = "file://usr/include/aarch64-linux-gnu/NvInfer.h;endline=11;md5=5b3efc8545af63ab1d12a466cb920c15"

SRC_COMMON_DEBS = "\
    libnvinfer10_${PV}+cuda12.5_arm64.deb;downloadfilename=libnvinfer10_${PV}+cuda12.5_arm64.deb;name=lib;subdir=tensorrt \
    libnvinfer-dev_${PV}+cuda12.5_arm64.deb;downloadfilename=libnvinfer-dev_${PV}+cuda12.5_arm64.deb;name=dev;subdir=tensorrt \
    libnvinfer-headers-dev_${PV}+cuda12.5_arm64.deb;downloadfilename=libnvinfer-headers-dev_${PV}+cuda12.5_arm64.deb;name=hdev;subdir=tensorrt \
    libnvinfer-dispatch10_${PV}+cuda12.5_arm64.deb;downloadfilename=libnvinfer-dispatch10_${PV}+cuda12.5_arm64.deb;name=disp;subdir=tensorrt \
    libnvinfer-dispatch-dev_${PV}+cuda12.5_arm64.deb;downloadfilename=libnvinfer-dispatch-dev_${PV}+cuda12.5_arm64.deb;name=dispdev;subdir=tensorrt \
    libnvinfer-lean10_${PV}+cuda12.5_arm64.deb;downloadfilename=libnvinfer-lean10_${PV}+cuda12.5_arm64.deb;name=nvl;subdir=tensorrt \
    libnvinfer-lean-dev_${PV}+cuda12.5_arm64.deb;downloadfilename=libnvinfer-lean-dev_${PV}+cuda12.5_arm64.deb;name=nvldev;subdir=tensorrt \
"

SRC_URI[lib.sha256sum] = "3e6eaf03caf417501f121337d9aa861eb64ecf7c0bf141a293cdda66a3f42223"
SRC_URI[dev.sha256sum] = "c019cf3cd29526d7956ea1a4ffbd7cfce7dd8112538fd0f18080dbbd7a3f366f"
SRC_URI[hdev.sha256sum] = "ca0d00005d5994fea77fb14c3ee26b551927befd6c76da71365b2a11439dcc1f"
SRC_URI[disp.sha256sum] = "2430d99a48223b1efcfa300e6e30398f33d593a4c9efd2bbcdacdb929304e706"
SRC_URI[dispdev.sha256sum] = "07f14d72dad20f4e8c0a2af6ec5bc54ce9a6621896b20ae510919e65d868b03d"
SRC_URI[nvl.sha256sum] = "89d8c78a35cfd8534f93456da09152069bb3d3a0a8a446e54ff6a7c97f4c3489"
SRC_URI[nvldev.sha256sum] = "6284321e54503d7523e782408901ee0d634378ffda53712fbee11b83b423800b"

DEPENDS = ""
