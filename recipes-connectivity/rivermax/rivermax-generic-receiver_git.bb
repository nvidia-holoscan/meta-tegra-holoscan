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

SUMMARY = "NVIDIA Rivermax Generic Receiver Sample Application"
LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://${S}/../License.md;md5=b5b0ba1bc19282f7e114407166a20719"

SRC_URI = "git://github.com/NVIDIA/Rivermax.git;branch=master;protocol=https"
SRCREV = "eafc9affb751eec082f5257768ca04e2995bfcfa"

S = "${WORKDIR}/git/generic_receiver"

inherit pkgconfig cmake cuda

EXTRA_OECMAKE += " \
    -DCMAKE_SKIP_RPATH=TRUE \
    ${@'-DRIVERMAX_ENABLE_TEGRA=ON' if d.getVar('TEGRA_DGPU') == '0' else ''} \
"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/generic_receiver ${D}${bindir}
}

DEPENDS = " \
    rivermax \
    ${@'cuda-nvml-native' if d.getVar('TEGRA_DGPU') == '1' else ''} \
"
