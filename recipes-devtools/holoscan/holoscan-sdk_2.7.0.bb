# Copyright (c) 2022-2024, NVIDIA CORPORATION. All rights reserved.
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
SRCREV = "68c7b5e361393734104ad9bc6d9d991aa102f5d4"

SRC_URI += " \
    file://desktop-icons \
    file://0001-Fix-GXF-TypenameAsString-error.patch \
    file://0002-Use-external-library-dependencies.patch \
    file://0003-Build-python-libs-with-install-RPATH.patch \
    file://0004-Fix-TensorRT-include-interface.patch \
    file://0005-Move-required-3rdparty-headers-to-holoscan-core.patch \
    file://0006-Disable-various-warnings-as-errors.patch \
    file://0007-Remove-CONFIG-from-Protobuf-find_package.patch \
    file://0008-Fix-ONNXRuntime-include-paths.patch \
    file://0009-Remove-UCX-header-workaround.patch \
    file://0010-Skip-multiai_ultrasound-data-download.patch \
    file://0011-Revert-to-pybind-2.11.1.patch \
    file://0012-Fix-redefinition-errors-in-rmm-patch.patch \
    file://0013-Remove-GXF-python-modules-install.patch \
"

S = "${WORKDIR}/git"

HOLOSCAN_INSTALL_PATH = "/opt/nvidia/holoscan"

inherit pkgconfig cmake cuda setuptools3

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
    -DGXF_DIR=${RECIPE_SYSROOT}/opt/nvidia/gxf/lib/cmake/GXF \
    -Dyaml-cpp_DIR=${RECIPE_SYSROOT}${datadir}/cmake/yaml-cpp \
    -Dajantv2_DIR=${RECIPE_SYSROOT}${libdir}/cmake/ajantv2 \
    -Dglfw3_DIR=${RECIPE_SYSROOT}${libdir}/cmake/glfw3 \
"

# Disable unused Holoscan build steps and components.
EXTRA_OECMAKE:append = " \
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
    glfw \
    glslang-native \
    grpc \
    grpc-native \
    gxf-core \
    ffmpeg-native \
    libcublas-native \
    libnpp-native \
    libxcursor \
    libxinerama \
    magic-enum \
    numactl \
    onnxruntime \
    patchelf-native \
    protobuf \
    protobuf-native \
    pytorch \
    tensorrt-core \
    tensorrt-plugins \
    torchvision \
    ucx \
    v4l-utils \
    vulkan-headers \
    vulkan-loader \
    yaml-cpp \
"

RDEPENDS:${PN} = " \
    bash \
    libnpp \
    libv4l \
    python3-cloudpickle \
    python3-cupy \
    python3-numpy \
    tegra-libraries-multimedia \
    tegra-libraries-multimedia-utils \
    tegra-libraries-multimedia-v4l \
    tegra-libraries-vulkan \
"

RDEPENDS:${PN}-dev = " \
    bash \
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

    # Note that the holoscan component currently needs to be installed after holoscan-core
    # in order for the CMake configuration files to be installed correctly.
    do_component_install holoscan

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

    # Install desktop icons for the applications.
    install -d ${D}${datadir}/applications
    install -m 0644 ${WORKDIR}/desktop-icons/*.desktop ${D}${datadir}/applications
    install -d ${D}${datadir}/pixmaps
    install -m 0644 ${WORKDIR}/desktop-icons/*.png ${D}${datadir}/pixmaps
}

FILES:${PN} += " \
    ${HOLOSCAN_INSTALL_PATH} \
    /opt/nvidia/data \
    ${libdir} \
"

FILES:${PN}-dev += " \
    ${HOLOSCAN_INSTALL_PATH}/include \
    ${HOLOSCAN_INSTALL_PATH}/lib/cmake \
"

FILES:${PN}-staticdev += " \
    ${HOLOSCAN_INSTALL_PATH}/lib/*.a \
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
