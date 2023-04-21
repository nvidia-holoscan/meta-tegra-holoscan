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
require recipes-devtools/gie/tensorrt-core_8.5.2-1.bb

LIC_FILES_CHKSUM = "file://usr/include/aarch64-linux-gnu/NvInfer.h;endline=11;md5=117f6d17a39656035fa9d36b73ca4916"

SRC_COMMON_DEBS = "\
    libnvinfer8_${PV}+cuda12.0_arm64.deb;downloadfilename=libnvinfer8_${PV}+cuda12.0_arm64.deb;name=lib;subdir=tensorrt \
    libnvinfer-dev_${PV}+cuda12.0_arm64.deb;downloadfilename=libnvinfer-dev_${PV}+cuda12.0_arm64.deb;name=dev;subdir=tensorrt \
    libnvinfer-headers-dev_${PV}+cuda12.0_arm64.deb;downloadfilename=libnvinfer-headers-dev_${PV}+cuda12.0_arm64.deb;name=headerdev;subdir=tensorrt \
    libnvparsers8_${PV}+cuda12.0_arm64.deb;downloadfilename=libnvparsers8_${PV}+cuda12.0_arm64.deb;name=nvp;subdir=tensorrt \
    libnvparsers-dev_${PV}+cuda12.0_arm64.deb;downloadfilename=libnvparsers-dev_${PV}+cuda12.0_arm64.deb;name=nvpdev;subdir=tensorrt \
"

SRC_URI[lib.sha256sum] = "9e37d16b351ba371b92ac68873aa10f245e92d26aa32518c4985765285e39fd1"
SRC_URI[dev.sha256sum] = "08924be3472f364159985e8536aabaaec0d6479d9548ab27a02fe4ab07f7304d"
SRC_URI[headerdev.sha256sum] = "76a5667e7f59ef650ee05525cb329b09a3b17a5c6729731f6415f14418a9f3b1"
SRC_URI[nvp.sha256sum] = "be1b66c0fcfb3cf5d6eea541b3922b9971b5bd6718e6d7c8312401b52d7b20da"
SRC_URI[nvpdev.sha256sum] = "67babfda410ac328e1a11bf457e68ad2108009cfbfa6f27fe40ec37861cb1f37"

DEPENDS = ""
