# Copyright (c) 2022-2023, NVIDIA CORPORATION. All rights reserved.
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

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${S}/LICENSE.txt;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://github.com/nvidia-holoscan/holoscan-sdk.git;branch=main;protocol=https"
SRCREV = "f4095926e9e643b96e8d50ad7e4501bdc6a28a6b"

SRC_URI += " \
    file://0001-Fix-GXF-TypenameAsString-error.patch \
    file://0002-Use-external-library-dependencies.patch \
    file://0003-Build-python-libs-with-install-RPATH.patch \
    file://0004-Fix-ONNX-and-TensorRT-include-interfaces.patch \
    file://0005-Fix-double-install-of-gxf_holoscan_wrapper-library.patch \
    file://0006-Move-required-3rdparty-headers-to-holoscan-core.patch \
    file://0007-Remove-multiai_ultrasound-dataset.patch \
    file://0008-Remove-extra-CUDA-toolchain-include.patch \
    file://0009-Fix-AJA-RDMA-for-iGPU.patch \
"

S = "${WORKDIR}/git"

HOLOSCAN_INSTALL_PATH = "/opt/nvidia/holoscan"

inherit pkgconfig cmake cuda setuptools3

# Fetch and unpack the datasets using custom bitbake tasks instead of
# downloading them at compile time via CMake.
require holoscan_download_datasets.inc

# Allow CMake to fetch dependencies during the configure and compile steps.
# TODO: This should be removed when OE-provided versions of the dependencies
#       are used instead of fetching and building them with CMake.
do_configure[network] = "1"
do_compile[network] = "1"
EXTRA_OECMAKE:append = " \
    -DFETCHCONTENT_FULLY_DISCONNECTED=OFF \
"

# Add extra build paths.
EXTRA_OECMAKE:append = " \
    -DCMAKE_INSTALL_PREFIX=${HOLOSCAN_INSTALL_PATH} \
    -DGXF_SDK_PATH=${RECIPE_SYSROOT}/opt/nvidia/gxf \
    -Dyaml-cpp_DIR=${RECIPE_SYSROOT}${datadir}/cmake/yaml-cpp \
    -Dajantv2_DIR=${RECIPE_SYSROOT}${libdir}/cmake/ajantv2 \
    -Dglad_DIR=${RECIPE_SYSROOT}${libdir}/cmake/glad \
    -Dglfw3_DIR=${RECIPE_SYSROOT}${libdir}/cmake/glfw3 \
"

# Disable unused Holoscan build steps and components.
EXTRA_OECMAKE:append = " \
    -DHOLOSCAN_DOWNLOAD_DATASETS=OFF \
    -DHOLOSCAN_INSTALL_EXAMPLE_SOURCE=OFF \
    -DHOLOSCAN_BUILD_TESTS=OFF \
    -DHOLOSCAN_BUILD_DOCS=OFF \
    -DHOLOSCAN_USE_CCACHE=OFF \
"

# Set a flag used by the tl-expected library to avoid build errors.
# TODO: This should be removed when the tl-expected library is provided by its
#       own recipe instead of being fetched and built for this recipe by CMake.
EXTRA_OECMAKE:append = " \
    -DEXPECTED_ENABLE_TESTS=OFF \
"

DEPENDS += " \
    ajantv2-sdk \
    glad \
    glfw \
    glslang-native \
    gxf-core \
    libcublas-native \
    libnpp-native \
    libxcursor \
    libxinerama \
    onnxruntime \
    patchelf-native \
    tensorrt-core \
    tensorrt-plugins \
    vulkan-headers \
    vulkan-loader \
    yaml-cpp \
"

RDEPENDS:${PN} = " \
    libnpp \
    libv4l \
    python3-cupy \
    python3-numpy \
    tegra-libraries-multimedia \
    tegra-libraries-multimedia-utils \
    tegra-libraries-multimedia-v4l \
"

# This explicit do_configure avoids conflicts between the cmake and setuptools3 classes.
do_configure() {
    cmake_do_configure
}

# This explicit do_compile avoids conflicts between the cmake and setuptools3 classes.
do_compile() {
    cmake_do_compile
}

do_component_install() {
    bbnote ${CMAKE_VERBOSE} cmake --install '${B}' --prefix '${D}${HOLOSCAN_INSTALL_PATH}' --component "$@"
    eval ${CMAKE_VERBOSE} cmake --install '${B}' --prefix '${D}${HOLOSCAN_INSTALL_PATH}' --component "$@"
}

do_install() {
    do_component_install holoscan-core
    do_component_install holoscan-gxf_extensions
    do_component_install holoscan-examples
    do_component_install holoscan-gxf_libs
    do_component_install holoscan-gxf_bins
    do_component_install holoscan-modules
    do_component_install holoscan-python_libs
    do_component_install holoscan-data

    # Create symlinks to the SDK libraries in the system lib directory.
    install -d ${D}${libdir}
    for i in $(find ${D}/${HOLOSCAN_INSTALL_PATH}/lib -name "*.so*" -printf "%P\n"); do
        ln -s ${HOLOSCAN_INSTALL_PATH}/lib/$i ${D}${libdir}
    done

    # Link to the python packages in the system site-packages directory.
    install -d ${D}${PYTHON_SITEPACKAGES_DIR}
    ln -s ${HOLOSCAN_INSTALL_PATH}/python/lib/holoscan ${D}${PYTHON_SITEPACKAGES_DIR}

    # Create a data directory symlink to fix some relative runtime path issues.
    ln -s ${HOLOSCAN_INSTALL_PATH}/data ${D}/opt/nvidia/data
}

FILES:${PN} += " \
    ${HOLOSCAN_INSTALL_PATH} \
    /opt/nvidia/data \
"

FILES:${PN}-dev += " \
    ${HOLOSCAN_INSTALL_PATH}/include \
    ${HOLOSCAN_INSTALL_PATH}/lib/cmake \
"

SYSROOT_DIRS = " \
    ${HOLOSCAN_INSTALL_PATH} \
"

# The GXF libraries (and symlinks) use the .so suffix but need to be installed
# in the non-dev package.
FILES_SOLIBSDEV = ""
INSANE_SKIP:${PN} += "dev-so"

# Prebuilt binaries (e.g. 'gxe') are already stripped of debugging information.
INSANE_SKIP:${PN} += "already-stripped"

# Relative RPATHs are used within Holoscan (e.g. $ORIGIN/../lib) and are
# required, but they incorrectly trigger the "probably-redundant RPATH" checks.
INSANE_SKIP:${PN} += "useless-rpaths"
