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

SUMMARY = "Emergent Camera SDK"
LICENSE = "CLOSED"

PACKAGES = "${PN} ${PN}-dev"

SRC_URI = "https://emergentvisiontec.com/wp-content/uploads/downloads-resources/SoftwareSDK/EVT_SDK_Installer_2_37_05_20803_Ubuntu_20_04_4__5_10_65_arm64_JP_50_HP_RMAX.tgz"
SRC_URI[md5sum] = "34df912b9be5113d33a863da1bac58a8"
SRC_URI[sha256sum] = "caa5ba1567531147c634e8fc87bfe84424523cbde816814805672d3e80056328"

inherit cuda

APPS = " \
    EVT_AcquisitionControl \
    EVT_AnalogControl \
    EVT_BenchmarkHS \
    EVT_DeviceInformation \
    EVT_GPIO \
    EVT_ImageFormatControl \
    EVT_Mcast/EVT_Mcast_Master \
    EVT_Mcast/EVT_Mcast_Slave \
    EVT_PTP \
"

extract_deb() {
    cd ${S}
    ar x ${WORKDIR}/emergent_camera.deb
    tar xf data.tar.xz
    rm control.tar.xz data.tar.xz debian-binary
}

do_unpack[depends] += "xz-native:do_populate_sysroot"
do_unpack:append () {
    bb.build.exec_func("extract_deb", d)
}

do_configure() {
    for subdir in ${APPS}; do
        rm -rf "${S}/opt/EVT/eSDK/Examples/${subdir}/.out"
    done
}

do_compile() {
    oldwd="${PWD}"
    for subdir in ${APPS}; do
        cd "${S}/opt/EVT/eSDK/Examples/${subdir}"
        oe_runmake
    done
}

EXTRA_OEMAKE += " \
    CXX="${CXX_FOR_CUDA}" \
    EMERGENT_DIR="${S}/opt/EVT" \
    LDLIBS_FLAGS="-L${S}/opt/EVT/eSDK/lib/ -lEmergentCamera -lEmergentGenICam -lEmergentGigEVision \
                  -L${S}/opt/EVT/eSDK/genicam/bin/Linux64_ARM -lGCBase_gcc49_v3_2 -lGenApi_gcc49_v3_2" \
"

do_install () {
    install -d ${D}${libdir}
    install -m 0644 ${S}/opt/EVT/eSDK/lib/*.so* ${D}${libdir}
    install -m 0644 ${S}/opt/EVT/eSDK/genicam/bin/Linux64_ARM/*.so* ${D}${libdir}

    install -d ${D}/opt/EVT/eSDK
    cp -rd --no-preserve=ownership ${S}/opt/EVT/eSDK/include ${D}/opt/EVT/eSDK

    install -d ${D}${bindir}
    for subdir in ${APPS}; do
        install -m 0755 ${S}/opt/EVT/eSDK/Examples/${subdir}/out/$(basename ${subdir}) ${D}${bindir}
    done
}

FILES:${PN} += " \
    ${libdir} \
"

FILES:${PN}-dev += " \
    /opt/EVT/eSDK/include \
"

SYSROOT_DIRS = " \
    ${libdir} \
    /opt/EVT/eSDK \
"

DEPENDS = " \
    ffmpeg (= 4.4.1) \
    tiff \
"

RDEPENDS:${PN} += " \
    rivermax \
"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP:${PN} += "dev-so already-stripped ldflags"
