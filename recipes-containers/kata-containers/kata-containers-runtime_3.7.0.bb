# Copyright (c) 2024, NVIDIA CORPORATION. All rights reserved.
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

SUMMARY = "Lightweight Virtual Machines (VMs) that feel and perform like containers"
HOMEPAGE = "https://katacontainers.io/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI = "git://${GO_IMPORT};branch=main;protocol=https"
SRCREV = "6aff5f300a9823394d45054f3225446836928446"

# This recipe builds and configures the runtime components of Kata containers to use QEMU.
# It also depends on the kata-containers-guest package, which installs the default guest
# kernel and rootfs that is prebuilt and provided with the official Kata release.

inherit go

GO_IMPORT = "github.com/kata-containers/kata-containers"
GO_INSTALL = " \
    ${GO_IMPORT}/src/runtime/cmd/kata-runtime \
    ${GO_IMPORT}/src/runtime/cmd/kata-monitor \
    ${GO_IMPORT}/src/runtime/cmd/containerd-shim-kata-v2 \
"

export GO111MODULE="off"

KATA_BUILD_OPTIONS = " \
    ARCH=${TARGET_ARCH} \
    SKIP_GO_VERSION_CHECK=true \
"

KATA_CONFIG_OPTIONS = " \
    DEFVIRTIOFSDAEMON=${bindir}/virtiofsd \
    DEFNETWORKMODEL_QEMU=macvtap \
"

do_compile:prepend() {
    cd ${B}/src/${GO_IMPORT}/src/runtime
    make ${KATA_BUILD_OPTIONS} clean-generated-files
    make ${KATA_BUILD_OPTIONS} pkg/katautils/config-settings.go
    make ${KATA_BUILD_OPTIONS} ${KATA_CONFIG_OPTIONS} generate-config
    cd ${B}
}

do_install:append() {
    install -d ${D}${sysconfdir}/kata-containers
    install -m 0644 ${B}/src/${GO_IMPORT}/src/runtime/config/configuration-qemu.toml ${D}${sysconfdir}/kata-containers/configuration.toml
}

RDEPENDS:${PN} = " \
    qemu \
    virtiofsd \
    kata-containers-guest \
"

RRECOMMENDS:${PN} = " \
    docker \
"

RDEPENDS:${PN}-dev = "bash"
