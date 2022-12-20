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

SRC_URI = "git://github.com/nvidia-holoscan/holoscan-sdk.git;branch=main;protocol=https"
SRCREV = "9acd7a263a6a95cfce514bd2b6595bc6c4eb2282"

SRC_URI += " \
    file://0001-Fix-GXF-TypenameAsString-error.patch \
    file://holoscan_endoscopy_data_20221121.zip;subdir=test_data/endoscopy \
    file://holoscan_ultrasound_data_20220608.zip;subdir=test_data/ultrasound \
    file://holoscan_multi_ai_ultrasound_data_20221201.zip;subdir=test_data/multiai_ultrasound \
"

S = "${WORKDIR}/git"

inherit pkgconfig cmake cuda

# Allow the configure and compile steps to fetch dependencies.
# TODO: This should be removed when dependencies are fetched by OE/bitbake.
do_configure[network] = "1"
do_compile[network] = "1"

# Note: These install and data paths needs to match hardcoded paths used in the Holoscan application graphs.
HOLOSCAN_INSTALL_PATH = "/workspace"
HOLOSCAN_DATA_PATH = "/data"

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

# Disable building python bindings.
EXTRA_OECMAKE:append = " \
    -DHOLOSCAN_BUILD_PYTHON=OFF \
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
    patchelf-native \
    libcublas-native \
    onnxruntime \
"

RDEPENDS:${PN} = " \
    gxf-core \
    glfw \
"

do_component_install() {
    bbnote ${CMAKE_VERBOSE} cmake --install '${B}' --prefix '${D}${HOLOSCAN_INSTALL_PATH}' --component "$@"
    eval ${CMAKE_VERBOSE} cmake --install '${B}' --prefix '${D}${HOLOSCAN_INSTALL_PATH}' --component "$@"
}

do_install() {
    # Install the required components.
    do_component_install holoscan-core
    do_component_install holoscan-gxf_extensions
    do_component_install holoscan-apps
    do_component_install holoscan-gxf_libs
    do_component_install holoscan-gxf_bins
    do_component_install holoscan-modules

    # Install test data (and remove incompatible engine files).
    cp -rd --no-preserve=ownership ${WORKDIR}/test_data ${D}/${HOLOSCAN_DATA_PATH}
    mkdir -p ${D}/${HOLOSCAN_DATA_PATH}/endoscopy/model/tool_loc_convlstm_engines
    mkdir -p ${D}/${HOLOSCAN_DATA_PATH}/ultrasound/model/us_unet_256x256_nhwc_engines
    rm -f ${D}/${HOLOSCAN_DATA_PATH}/endoscopy/model/tool_loc_convlstm_engines/*
    rm -f ${D}/${HOLOSCAN_DATA_PATH}/ultrasound/model/us_unet_256x256_nhwc_engines/*
    rm -f ${D}/${HOLOSCAN_DATA_PATH}/multiai_ultrasound/models/*.engine.*

    # Remove unneeded files.
    rm -rf $(find ${D}/${HOLOSCAN_INSTALL_PATH}/apps -name python)
    rm -rf ${D}/${HOLOSCAN_INSTALL_PATH}/lib/cmake
    rm -rf ${D}/${HOLOSCAN_INSTALL_PATH}/examples
    rm -rf ${D}/${HOLOSCAN_INSTALL_PATH}/include
}

FILES:${PN} += " \
    ${HOLOSCAN_INSTALL_PATH}/apps \
    ${HOLOSCAN_INSTALL_PATH}/bin \
    ${HOLOSCAN_INSTALL_PATH}/gxf_extensions \
    ${HOLOSCAN_INSTALL_PATH}/lib \
    ${HOLOSCAN_DATA_PATH} \
"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP:${PN} += "dev-so textrel staticdev useless-rpaths"
