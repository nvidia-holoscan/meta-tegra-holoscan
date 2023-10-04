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

require nvidia-driver-common.inc

SRC_URI[arm64.sha256sum] = "f256f6ba002a8b40976450b074db02d1a5f68ca50c03dad1a01ac2c73c9905ce"
SRC_URI[amd64.sha256sum] = "25c91a776883f9004778e2f48c733642aac451058653eb435d80ea929f964b79"

do_install:append() {
    cp -rd --no-preserve=ownership ${S}/${baselib} ${D}${libdir}
}

FILES:${PN} += " \
    ${base_libdir} \
"

RDEPENDS:${PN} += "nvidia-open-gpu-kernel-modules"

# The GPU firmware binaries do not match the arm64 expected by bitbake's arch check.
INSANE_SKIP:${PN} += "arch"
