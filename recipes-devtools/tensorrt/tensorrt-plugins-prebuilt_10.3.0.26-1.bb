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
require recipes-devtools/gie/tensorrt-plugins-prebuilt_10.3.0.30-1.bb

LIC_FILES_CHKSUM = "file://usr/include/aarch64-linux-gnu/NvInferPlugin.h;endline=11;md5=5b3efc8545af63ab1d12a466cb920c15"

SRC_COMMON_DEBS = "\
    libnvonnxparsers10_${PV}+cuda12.5_arm64.deb;downloadfilename=libnvonnxparsers10_${PV}+cuda12.5_arm64.deb;name=onnx;subdir=tensorrt \
    libnvonnxparsers-dev_${PV}+cuda12.5_arm64.deb;downloadfilename=libnvonnxparsers-dev_${PV}+cuda12.5_arm64.deb;name=onnxdev;subdir=tensorrt \
    libnvinfer-plugin10_${PV}+cuda12.5_arm64.deb;downloadfilename=libnvinfer-plugin10_${PV}+cuda12.5_arm64.deb;name=plugin;subdir=tensorrt \
    libnvinfer-plugin-dev_${PV}+cuda12.5_arm64.deb;downloadfilename=libnvinfer-plugin-dev_${PV}+cuda12.5_arm64.deb;name=plugindev;subdir=tensorrt \
    libnvinfer-headers-plugin-dev_${PV}+cuda12.5_arm64.deb;downloadfilename=libnvinfer-headers-plugin-dev_${PV}+cuda12.5_arm64.deb;name=hplugindev;subdir=tensorrt \
    libnvinfer-vc-plugin10_${PV}+cuda12.5_arm64.deb;downloadfilename=libnvinfer-vc-plugin10_${PV}+cuda12.5_arm64.deb;name=vc;subdir=tensorrt \
    libnvinfer-vc-plugin-dev_${PV}+cuda12.5_arm64.deb;downloadfilename=libnvinfer-vc-plugin-dev_${PV}+cuda12.5_arm64.deb;name=vcdev;subdir=tensorrt \
"

SRC_URI[onnx.sha256sum] = "f9da9e1ea7e2de25fe47cff3c38d8b805e10bd379a4b20289b613f9432d79706"
SRC_URI[onnxdev.sha256sum] = "42e88c91c6b78607c3dcafb1ddeec83e7273893d26da276336785463736b130c"
SRC_URI[plugin.sha256sum] = "1f8fb1f2d80f9b5202d8ae7132d0227e8c7252a3d42312aeb25f724ec74ddbba"
SRC_URI[plugindev.sha256sum] = "cab88148c268d99306f2a58271716854f0ee3addde848948f2d9ee1380d58c61"
SRC_URI[hplugindev.sha256sum] = "ecf6c7a7eb131bc8a0e995d98f6af307f84afbf075b47603bb122b19d5d38013"
SRC_URI[vc.sha256sum] = "3dbf2f819947c393b014a3724d41ca90ea61d3faedf1957247b21c533c231a05"
SRC_URI[vcdev.sha256sum] = "1be538f5e21b8be0e95d76e638f0e8db36f45fab39a956026027b1ccaf32e851"
