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
SRCREV = "5caf9680aa2b82ae90306d949789324b6bc0a45a"

SRC_URI += " \
    file://0001-Add-Wno-deprecated-declarations-build-flag.patch \
    file://0002-Fix-HOLOSCAN_LOG_ERROR-issue-with-YAML-Node.patch \
    file://0003-Add-install-rules-for-ping_tx-rx-libraries.patch \
    file://0004-Holoviz-add-stdexcept-includes.patch \
    file://0005-Remove-Findajantv2.cmake-from-install-list.patch \
    file://holoscan_endoscopy_data.zip;subdir=test_data/endoscopy \
    file://holoscan_ultrasound_data.zip;subdir=test_data/ultrasound \
"

S = "${WORKDIR}/git"

inherit pkgconfig cmake cuda

# Allow the configure and compile steps to fetch dependencies.
# TODO: This should be removed when dependencies are fetched by OE/bitbake.
do_configure[network] = "1"
do_compile[network] = "1"

# Note: This install path needs to match hardcoded paths used in the Holoscan application graphs.
HOLOSCAN_INSTALL_PATH = "/workspace"

export VULKAN_SDK="${RECIPE_SYSROOT}${prefix}"

# Add extra build paths.
EXTRA_OECMAKE:append = " \
    -DCMAKE_INSTALL_PREFIX=${HOLOSCAN_INSTALL_PATH} \
    -DGXF_SDK_PATH=${RECIPE_SYSROOT}/opt/nvidia/gxf \
    -Dyaml-cpp_DIR=${RECIPE_SYSROOT}${datadir}/cmake/yaml-cpp \
    -Dajantv2_DIR=${RECIPE_SYSROOT}${libdir}/cmake/ajantv2 \
    -Dglad_DIR=${RECIPE_SYSROOT}${libdir}/cmake/glad \
    -Dglfw3_DIR=${RECIPE_SYSROOT}${libdir}/cmake/glfw3 \
    -Dnanovg_DIR=${RECIPE_SYSROOT}${libdir}/cmake/nanovg \
    -DTensorRT_DIR=${RECIPE_SYSROOT}${libdir}/cmake/tensorrt \
    -DVULKANSDK_ROOT_DIR=${RECIPE_SYSROOT}${includedir} \
"

# Allow cmake to fetch dependencies.
# TODO: This should be removed and replaced with OE-installed dependencies.
EXTRA_OECMAKE:append = " \
    -DFETCHCONTENT_FULLY_DISCONNECTED=OFF \
"

# Disable unused Holoscan build components.
EXTRA_OECMAKE:append = " \
    -DHOLOSCAN_DOWNLOAD_DATASETS=OFF \
    -DHOLOSCAN_BUILD_TESTS=OFF \
    -DHOLOSCAN_BUILD_DOCS=OFF \
    -DHOLOSCAN_USE_CCACHE=OFF \
"

# Set a flag used by the tl-expected library to avoid build errors.
EXTRA_OECMAKE:append = " \
    -DEXPECTED_ENABLE_TESTS=OFF \
"

DEPENDS += " \
    ajantv2-sdk \
    glad \
    glfw \
    gxf-core \
    libnpp-native \
    nanovg \
    tensorrt-core \
    tensorrt-plugins \
    yaml-cpp \
    vulkan-headers \
    vulkan-loader \
    shaderc \
    glslang-native \
    libxxf86vm \
"

RDEPENDS:${PN} = " \
    gxf-core \
    glfw \
"

do_install:append () {
    # Install test data (and remove incompatible engine files).
    install -d ${D}/${HOLOSCAN_INSTALL_PATH}
    cp -rd --no-preserve=ownership ${WORKDIR}/test_data ${D}/${HOLOSCAN_INSTALL_PATH}
    rm ${D}/${HOLOSCAN_INSTALL_PATH}/test_data/endoscopy/model/tool_loc_convlstm_engines/*
    rm ${D}/${HOLOSCAN_INSTALL_PATH}/test_data/ultrasound/model/us_unet_256x256_nhwc_engines/*

    # Remove unneeded files.
    rm -rf ${D}/${HOLOSCAN_INSTALL_PATH}/cmake
    rm -rf ${D}/${HOLOSCAN_INSTALL_PATH}/include
    rm -rf ${D}/${HOLOSCAN_INSTALL_PATH}/src
    rm -rf ${D}/${HOLOSCAN_INSTALL_PATH}/share
    rm ${D}/${HOLOSCAN_INSTALL_PATH}/lib/libglfw*

    # Add library links to system library path.
    install -d ${D}${libdir}
    for i in ${D}/${HOLOSCAN_INSTALL_PATH}/lib/*.so ${D}/${HOLOSCAN_INSTALL_PATH}/lib/*.so.*; do
        # Note that the destination path is relative to the symlink location, hence no ${D}.
        ln -s ${HOLOSCAN_INSTALL_PATH}/lib/$(basename $i) ${D}${libdir}
    done
}

FILES:${PN} += " \
    ${HOLOSCAN_INSTALL_PATH}/apps \
    ${HOLOSCAN_INSTALL_PATH}/bin \
    ${HOLOSCAN_INSTALL_PATH}/gxf_extensions \
    ${HOLOSCAN_INSTALL_PATH}/lib \
    ${HOLOSCAN_INSTALL_PATH}/test_data \
    ${libdir} \
"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP:${PN} += "dev-so textrel staticdev"
