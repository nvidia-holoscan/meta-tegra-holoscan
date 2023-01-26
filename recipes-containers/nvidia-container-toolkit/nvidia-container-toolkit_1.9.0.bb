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

SUMMARY = "NVIDIA Container Toolkit"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://${GO_IMPORT};branch=main;protocol=https"
SRCREV = "56ad97b8e51245795c7610e275116ae5417a7c34"

SRC_URI += " \
    file://docker-daemon.json \
    file://runtime-config.toml \
"

inherit go

GO_IMPORT = "github.com/NVIDIA/nvidia-container-toolkit"
GO_INSTALL = " \
    ${GO_IMPORT}/cmd/nvidia-container-runtime \
    ${GO_IMPORT}/cmd/nvidia-container-toolkit \
"

export GO111MODULE="off"

do_install:append() {
    ln -s nvidia-container-toolkit ${D}${bindir}/nvidia-container-runtime-hook

    install -m 0644 -D ${WORKDIR}/docker-daemon.json ${D}${sysconfdir}/docker/daemon.json
    install -m 0644 -D ${WORKDIR}/runtime-config.toml ${D}${sysconfdir}/nvidia-container-runtime/config.toml
}

RDEPENDS:${PN} = " \
    libnvidia-container \
"

RDEPENDS:${PN}-dev = " \
    bash \
    make \
"

RRECOMMENDS:${PN} = " \
    docker-ce \
"
