# Copyright (c) 2022, NVIDIA CORPORATION. All rights reserved.
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

SUMMARY = "NVIDIA Holoscan SDK"

LICENSE = "CLOSED"
LIC_FILES_CHKSUM = "file://${S}/LICENSE.txt;md5=a71310eff4bf2b4be70c50985bd81d85"

PACKAGES = "${PN}"

SRC_URI = "git://github.com/NVIDIA/clara-holoscan-embedded-sdk.git;branch=main;protocol=https"
SRCREV = "f5776732588856bfbdf98c5440d06de365d02725"

SRC_URI += " \
    file://0001-Holoscan-v0.2.0-MGX-Fixes.patch \
    file://holoscan_endoscopy_data.zip;subdir=test_data/endoscopy \
    file://holoscan_ultrasound_data.zip;subdir=test_data/ultrasound \
"

S = "${WORKDIR}/git"

inherit pkgconfig cmake cuda

# Note: This install path needs to match hardcoded paths used in the Holoscan application graphs.
HOLOSCAN_INSTALL_PATH = "/workspace"

EXTRA_OECMAKE:append = " \
    -DGXF_DIR=${RECIPE_SYSROOT}/opt/nvidia/gxf \
    -Dyaml-cpp_DIR=${RECIPE_SYSROOT}${datadir}/cmake/yaml-cpp \
    -Dajantv2_DIR=${RECIPE_SYSROOT}${libdir}/cmake/ajantv2 \
    -Dglad_DIR=${RECIPE_SYSROOT}${libdir}/cmake/glad \
    -Dglfw3_DIR=${RECIPE_SYSROOT}${libdir}/cmake/glfw3 \
    -Dnanovg_DIR=${RECIPE_SYSROOT}${libdir}/cmake/nanovg \
    -DTensorRT_DIR=${RECIPE_SYSROOT}${includedir}/tensorrt \
    -DHOLOSCAN_INSTALL_PATH=${HOLOSCAN_INSTALL_PATH} \
"

DEPENDS += " \
    cuda-toolkit \
    gxf-core \
    yaml-cpp \
    glfw \
    glad \
    ajantv2-sdk \
    nanovg \
    tensorrt \
"

RDEPENDS:${PN} = " \
    nvidia-driver \
    cuda-toolkit \
    gxf-core \
"

do_install:append () {
    # Install test data (and remove incompatible engine files).
    cp -rd --no-preserve=ownership ${WORKDIR}/test_data ${D}/${HOLOSCAN_INSTALL_PATH}
    rm ${D}/${HOLOSCAN_INSTALL_PATH}/test_data/endoscopy/model/tool_loc_convlstm_engines/*
    rm ${D}/${HOLOSCAN_INSTALL_PATH}/test_data/ultrasound/model/us_unet_256x256_nhwc_engines/*
}

FILES:${PN} += " \
    ${libdir} \
    ${HOLOSCAN_INSTALL_PATH} \
"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP:${PN} += "dev-so textrel"
