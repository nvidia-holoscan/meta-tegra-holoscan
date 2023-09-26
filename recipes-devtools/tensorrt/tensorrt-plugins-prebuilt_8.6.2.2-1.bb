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
require recipes-devtools/gie/tensorrt-plugins-prebuilt_8.6.2.3-1.bb

LIC_FILES_CHKSUM = "file://usr/include/aarch64-linux-gnu/NvInferPlugin.h;endline=11;md5=117f6d17a39656035fa9d36b73ca4916"

SRC_COMMON_DEBS = "\
    libnvonnxparsers8_${PV}+cuda12.0_arm64.deb;downloadfilename=libnvonnxparsers8_${PV}+cuda12.0_arm64.deb;name=onnx;subdir=tensorrt \
    libnvonnxparsers-dev_${PV}+cuda12.0_arm64.deb;downloadfilename=libnvonnxparsers-dev_${PV}+cuda12.0_arm64.deb;name=onnxdev;subdir=tensorrt \
    libnvinfer-plugin8_${PV}+cuda12.0_arm64.deb;downloadfilename=libnvinfer-plugin8_${PV}+cuda12.0_arm64.deb;name=plugin;subdir=tensorrt \
    libnvinfer-plugin-dev_${PV}+cuda12.0_arm64.deb;downloadfilename=libnvinfer-plugin-dev_${PV}+cuda12.0_arm64.deb;name=plugindev;subdir=tensorrt \
    libnvinfer-headers-plugin-dev_${PV}+cuda12.0_arm64.deb;downloadfilename=libnvinfer-headers-plugin-dev_${PV}+cuda12.0_arm64.deb;name=headerplugindev;subdir=tensorrt \
"

SRC_URI[onnx.sha256sum] = "1355700bd25a674d2cb3dcc1cc0a2526673ab586a5a74039e63fb859a3e1a668"
SRC_URI[onnxdev.sha256sum] = "3264d812248a7902c85cb761abb6119bd2a0a039bfadf537179e254c5fb386dd"
SRC_URI[plugin.sha256sum] = "0639786d0db2cec39d2e581d2153fa62fa84e4656b68ea54a91726305f8fe646"
SRC_URI[plugindev.sha256sum] = "e9ee1ce87566b585af733a65423483ce0ca7191104c31896a573cd1aca9638f3"
SRC_URI[headerplugindev.sha256sum] = "1caee24d23b7cd9b34e13e6c472a44f01c868f242480b401b9362d68c9668243"

do_install() {
    install -d ${D}${includedir}
    install -m 0644 ${S}/usr/include/aarch64-linux-gnu/*.h ${D}${includedir}
    install -d ${D}${libdir}
    install -m 0644 ${S}/usr/lib/aarch64-linux-gnu/libnvinfer_plugin.so.${BASEVER} ${D}${libdir}
    install -m 0644 ${S}/usr/lib/aarch64-linux-gnu/libnvonnxparser.so.${BASEVER} ${D}${libdir}
    install -m 0644 ${S}/usr/lib/aarch64-linux-gnu/libnvinfer_plugin_static.a ${D}${libdir}
    install -m 0644 ${S}/usr/lib/aarch64-linux-gnu/libnvonnxparser_static.a ${D}${libdir}
    install -m 0644 ${S}/usr/lib/aarch64-linux-gnu/libonnx_proto.a ${D}${libdir}

    ln -s libnvinfer_plugin.so.${BASEVER} ${D}${libdir}/libnvinfer_plugin.so.${MAJVER}
    ln -s libnvinfer_plugin.so.${BASEVER} ${D}${libdir}/libnvinfer_plugin.so
    ln -s libnvonnxparser.so.${BASEVER} ${D}${libdir}/libnvonnxparser.so.${MAJVER}
    ln -s libnvonnxparser.so.${MAJVER} ${D}${libdir}/libnvonnxparser.so
}
