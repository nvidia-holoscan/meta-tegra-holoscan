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

SUMMARY = "NVIDIA Holoscan Test Suite"
HOMEPAGE = "https://github.com/nvidia-holoscan/holoscan-test-suite"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${S}/LICENSE.txt;md5=480539e97a7fb5ad9df72e45418ff4ec"

#
# The holoscan-test-suite package uses systemd to launch the test suite webserver
# at boot time within a screen session (see: https://linux.die.net/man/1/screen).
# Observing the console output from the webserver can be done by attaching to
# the screen session:
#
#    screen -d -r holoscan-test-suite
#
# The holoscan-test-suite-controls package also adds the test suite controls
# server, whose screen session can be attached with:
#
#    screen -d -r holoscan-test-suite-controls
#
# To disconnect from a screen session, press Ctrl-A then d. This will detach
# the console but the screen session (and server) will continue to run.
#
PACKAGES = "${PN} ${PN}-controls"

SRC_URI = "git://github.com/nvidia-holoscan/holoscan-test-suite.git;branch=main;protocol=https"
SRCREV = "e7a809d6e83998d74cc4bd1dcbbc4a75337b716c"

SRC_URI += " \
    file://services \
"

S = "${WORKDIR}/git"

inherit systemd setuptools3

# Select the appropriate test suite service configuration based on MACHINE.
HOLOSCAN_TEST_SUITE_SERVICE = "${PN}-${MACHINE}.service"
HOLOSCAN_TEST_SUITE_CONTROLS_SERVICE = "${PN}-controls.service"

# Start the test suite services at boot time.
SYSTEMD_PACKAGES = "${PACKAGES}"
SYSTEMD_SERVICE:${PN} = "${HOLOSCAN_TEST_SUITE_SERVICE}"
SYSTEMD_SERVICE:${PN}-controls = "${HOLOSCAN_TEST_SUITE_CONTROLS_SERVICE}"

do_install:append() {
    install -d ${D}/opt/nvidia
    cp -rd --no-preserve=ownership ${S}/src ${D}/opt/nvidia/holoscan-test-suite
    echo ${PV}-${@d.getVar('SRCREV')[:8]} > ${D}/opt/nvidia/holoscan-test-suite/project-version

    install -d ${D}/${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/services/${HOLOSCAN_TEST_SUITE_SERVICE} ${D}/${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/services/${HOLOSCAN_TEST_SUITE_CONTROLS_SERVICE} ${D}/${systemd_unitdir}/system
}

FILES:${PN} += " \
    /opt/nvidia/holoscan-test-suite \
    ${systemd_unitdir}/system/${HOLOSCAN_TEST_SUITE_SERVICE} \
"

FILES:${PN}-controls += " \
    ${systemd_unitdir}/system/${HOLOSCAN_TEST_SUITE_CONTROLS_SERVICE} \
"

RDEPENDS:${PN} = " \
    ajantv2-sdk \
    ethtool \
    gstreamer1.0-plugins-base \
    gstreamer1.0-plugins-good \
    nvme-cli \
    opencv \
    python3-ajantv2 \
    python3-flask \
    python3-pytest \
    python3-pyyaml \
    python3-smbus2 \
    screen \
    util-linux-lsblk \
    v4l-utils \
"

RDEPENDS:${PN}-controls = " \
    ${PN} \
    iperf3 \
    memtester \
    nvidia-gpu-stress-test \
    python3-flask-socketio \
    util-linux-taskset \
"
