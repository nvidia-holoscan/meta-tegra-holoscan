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

SUMMARY = "NVIDIA HoloHub Applications"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI = "git://github.com/nvidia-holoscan/holohub.git;branch=main;protocol=https"
SRCREV = "79758770ed0ffb0bd0b1fd84e4ffa31fe857926f"
PV = "2.0.0+git${SRCPV}"

SRC_URI += " \
    file://desktop-icons \
    file://0001-Add-install-rules.patch \
    file://0002-Fix-undefined-fmt-errors.patch \
    file://0003-Fix-ajantv2-dependencies.patch \
    file://0004-Remove-relative-gxf_extension-paths.patch \
    file://0005-Build-python-libs-with-install-RPATH.patch \
    file://0006-Enable-Emergent-apps.patch \
    file://0007-Fix-default-data-paths-in-python-apps.patch \
    file://0008-Fix-volume_renderer-application.patch \
    file://0009-Update-AJA-NTV2-dependency-to-17.0.patch \
"

S = "${WORKDIR}/git"

HOLOHUB_INSTALL_PATH = "/opt/nvidia/holohub"

inherit pkgconfig cmake cuda setuptools3-base

EXTRA_OECMAKE:append = " \
    -DBUILD_SAMPLE_APPS=ON \
    -DHOLOHUB_DOWNLOAD_DATASETS=ON \
    -DCMAKE_INSTALL_PREFIX=${HOLOHUB_INSTALL_PATH} \
    -Dholoscan_DIR=${RECIPE_SYSROOT}/opt/nvidia/holoscan/lib/cmake/holoscan \
    -Dajantv2_DIR=${RECIPE_SYSROOT}${libdir}/cmake/ajantv2 \
"

# Enable the Emergent apps if Emergent Camera support is enabled.
EXTRA_OECMAKE:append = " \
    ${@'-DHOLOHUB_ENABLE_EMERGENT=ON' if d.getVar('EMERGENT_CAMERA') == '1' else ''} \
    ${@'-DEMERGENT_ROOT_DIR=${RECIPE_SYSROOT}/opt/EVT/eSDK' if d.getVar('EMERGENT_CAMERA') == '1' else ''} \
"

# Allow CMake to fetch datasets or dependencies during the configure and compile steps.
do_configure[network] = "1"
do_compile[network] = "1"
EXTRA_OECMAKE:append = " \
    -DFETCHCONTENT_FULLY_DISCONNECTED=OFF \
"

EXCLUDE_ICONS = "volume-rendering"
EXCLUDE_ICONS:dgpu = ""

do_install:append() {
    # Create symlinks to the libraries in the system lib directory.
    install -d ${D}${libdir}
    for i in $(find ${D}/${HOLOHUB_INSTALL_PATH}/lib -name "*.so*" -printf "%P\n"); do
        ln -s ${HOLOHUB_INSTALL_PATH}/lib/$i ${D}${libdir}
    done

    # Link to the python packages in the system site-packages directory.
    install -d ${D}${PYTHON_SITEPACKAGES_DIR}
    ln -s ${HOLOHUB_INSTALL_PATH}/python/lib/holohub ${D}${PYTHON_SITEPACKAGES_DIR}

    # Install the datasets.
    if [ -d ${B}/data ]; then
        cp -rd --no-preserve=ownership ${B}/data ${D}${HOLOHUB_INSTALL_PATH}
    fi

    # Install desktop icons for the applications.
    install -d ${D}${datadir}/applications
    install -m 0644 ${WORKDIR}/desktop-icons/*.desktop ${D}${datadir}/applications
    for i in ${EXCLUDE_ICONS}; do
        rm ${D}${datadir}/applications/holohub-${i}.desktop
    done
    install -d ${D}${datadir}/pixmaps
    install -m 0644 ${WORKDIR}/desktop-icons/*.png ${D}${datadir}/pixmaps
    for i in ${EXCLUDE_ICONS}; do
        rm ${D}${datadir}/pixmaps/holohub-${i}.png
    done
}

DEPENDS += " \
    ajantv2-sdk \
    claraviz \
    ffmpeg-native \
    holoscan-sdk \
    libcublas-native \
    libnpp-native \
    python3-numpy-native \
    tensorrt-core \
    ${@'emergent-camera' if d.getVar('EMERGENT_CAMERA') == '1' else ''} \
"

RDEPENDS:${PN} += " \
    ${@'emergent-camera' if d.getVar('EMERGENT_CAMERA') == '1' else ''} \
"

FILES:${PN}-staticdev += " \
    ${HOLOHUB_INSTALL_PATH}/lib/*.a \
"

FILES:${PN} += " \
    ${HOLOHUB_INSTALL_PATH} \
    ${libdir} \
"

# The GXF libraries (and symlinks) use the .so suffix but need to be installed
# in the non-dev package.
FILES_SOLIBSDEV = ""
INSANE_SKIP:${PN} += "dev-so"

# Relative RPATHs are used within HoloHub (e.g. $ORIGIN/../lib) and are
# required, but they incorrectly trigger the "probably-redundant RPATH" checks.
INSANE_SKIP:${PN} += "useless-rpaths"
