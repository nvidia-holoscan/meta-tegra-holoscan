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

DESCRIPTION = "Dear ImGui: Bloat-free Graphical User interface for C++ with minimal dependencies"
HOMEPAGE = "https://github.com/ocornut/imgui"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=2c45028b0f14d9585774c12615de461b"

SRC_URI = " \
    git://github.com/ocornut/imgui.git;protocol=https;nobranch=1 \
    file://0001-add-ImGuiContext-structure-and-define-GImGui-variabl.patch \
"

SRCREV = "f3373780668fba1f9bd64c208d05c20b781c9a39"

PV = "1.88+git${SRCREV}"

S = "${WORKDIR}/git"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}/opt/nvidia/imgui
    cp -R --preserve=mode,links,timestamps ${S}/* ${D}/opt/nvidia/imgui
}

ALLOW_EMPTY:${PN} = "1"

FILES:${PN}-dev += "/opt/nvidia/imgui"
RDEPENDS:${PN}-dev += "bash"

SYSROOT_DIRS:append = " /opt/nvidia/imgui"
