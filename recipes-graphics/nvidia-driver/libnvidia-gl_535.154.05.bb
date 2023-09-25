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

SRC_URI[sha256sum] = "cad6b57e8a5ff872b562555fcbc6ab35aed450177b22399709f0bd4eff9e05ea"

do_install:append() {
    install -d ${D}${libdir}/xorg/modules/extensions
    ln -s ${libdir}/nvidia/xorg/libglxserver_nvidia.so ${D}${libdir}/xorg/modules/extensions/
}

RDEPENDS:${PN} = " \
    libnvidia-common \
    libdrm \
    libgbm \
    libglvnd \
    libx11 \
    libxcb-glx \
    libxext \
"

TEGRA_LIBRARIES = " \
    egl-gbm \
    tegra-libraries-eglcore \
    tegra-libraries-glescore \
    tegra-libraries-glxcore \
    tegra-libraries-vulkan \
"

RPROVIDES:${PN} += "${TEGRA_LIBRARIES}"
RCONFLICTS:${PN} += "${TEGRA_LIBRARIES}"
