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

require nvidia-driver-common.inc

SRC_URI[sha256sum] = "752023e57d5490ed94323a0eae0c774496655561c09f949149c9793d4912042f"

do_install:append() {
    install -d ${D}${libdir}/xorg/modules/drivers
    ln -s ${libdir}/nvidia/xorg/nvidia_drv.so ${D}${libdir}/xorg/modules/drivers/
}

RDEPENDS:${PN} += " \
    libnvidia-gl \
    xserver-xorg-module-libwfb \
"

# Add the ABI dependency at package generation time, as otherwise bitbake will
# attempt to find a provider for it (and fail) when it does the parse.
python populate_packages:prepend() {
    d.appendVar("RDEPENDS:" + d.getVar("PN", True), " xorg-abi-video-25")
}

RPROVIDES:${PN} += "xserver-xorg-extension-glx"
RCONFLICTS:${PN} = "xserver-xorg-extension-glx"
