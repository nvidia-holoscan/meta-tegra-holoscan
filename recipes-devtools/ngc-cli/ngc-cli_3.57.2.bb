# Copyright (c) 2025, NVIDIA CORPORATION. All rights reserved.
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

SUMMARY = "NVIDIA NGC CLI"
LICENSE = "CLOSED"

BBCLASSEXTEND = "native"
PACKAGES = "${PN}"

def ngc_pkg_arch(d):
    arch = d.getVar('TARGET_ARCH')
    return 'arm64' if arch == 'aarch64' else 'linux'

SRC_URI = "https://api.ngc.nvidia.com/v2/resources/nvidia/ngc-apps/ngc_cli/versions/${PV}/files/ngccli_${@ngc_pkg_arch(d)}.zip"
SHA256SUM:aarch64 = "0ab022b37d35010145815af473f6ef4fdfde0836e776b3433b36367eb27f903d"
SHA256SUM:x86-64 = "5e527b4514185c2d7794fee5f652545cff77a00d7530fd9142df0a3b9bb8501f"
SRC_URI[sha256sum] = "${SHA256SUM}"

INSTALL_PATH = "${base_prefix}/opt/nvidia"

do_install() {
    install -d ${D}${INSTALL_PATH}
    cp -rd --no-preserve=ownership ${WORKDIR}/ngc-cli ${D}${INSTALL_PATH}
    install -d ${D}${bindir}
    ln -s ../../opt/nvidia/ngc-cli/ngc ${D}${bindir}/ngc
}

FILES:${PN} = " \
    ${INSTALL_PATH} \
    ${bindir} \
"

SYSROOT_DIRS_NATIVE += " \
    ${INSTALL_PATH} \
"

RDEPENDS:${PN}:aarch64 = " \
    glibc \
"

do_patch[noexec] = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"

EXCLUDE_FROM_SHLIBS = "1"
INSANE_SKIP:${PN} += "ldflags already-stripped debug-files"
