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
require recipes-devtools/gie/tensorrt-plugins-prebuilt_8.5.2-1.bb

LIC_FILES_CHKSUM = "file://usr/include/aarch64-linux-gnu/NvInferPlugin.h;endline=11;md5=117f6d17a39656035fa9d36b73ca4916"

SRC_COMMON_DEBS = "\
    libnvonnxparsers8_${PV}+cuda12.0_arm64.deb;downloadfilename=libnvonnxparsers8_${PV}+cuda12.0_arm64.deb;name=onnx;subdir=tensorrt \
    libnvonnxparsers-dev_${PV}+cuda12.0_arm64.deb;downloadfilename=libnvonnxparsers-dev_${PV}+cuda12.0_arm64.deb;name=onnxdev;subdir=tensorrt \
    libnvinfer-plugin8_${PV}+cuda12.0_arm64.deb;downloadfilename=libnvinfer-plugin8_${PV}+cuda12.0_arm64.deb;name=plugin;subdir=tensorrt \
    libnvinfer-plugin-dev_${PV}+cuda12.0_arm64.deb;downloadfilename=libnvinfer-plugin-dev_${PV}+cuda12.0_arm64.deb;name=plugindev;subdir=tensorrt \
    libnvinfer-headers-plugin-dev_${PV}+cuda12.0_arm64.deb;downloadfilename=libnvinfer-headers-plugin-dev_${PV}+cuda12.0_arm64.deb;name=headerplugindev;subdir=tensorrt \
"

SRC_URI[onnx.sha256sum] = "d2b33e02cbb8f5111f07d0373788a52fa0eb3dc8817d852c97bad37b53f849fe"
SRC_URI[onnxdev.sha256sum] = "8b86703fff083d9e95a094dee47e48f55d68be058f5b7e4a416bdf1238ff3a0b"
SRC_URI[plugin.sha256sum] = "663199fb9b55a728f23ba765272b095700c3947cf81e02819b7a9aa56a5c5830"
SRC_URI[plugindev.sha256sum] = "558fbe8b30194fd4f34d04c2571ee3916a4da5d5b056a5c0796e4245c3246792"
SRC_URI[headerplugindev.sha256sum] = "f344a691dc2c33988dd3979d6882272b28426b3a9055542da56a900d81d89b2c"
