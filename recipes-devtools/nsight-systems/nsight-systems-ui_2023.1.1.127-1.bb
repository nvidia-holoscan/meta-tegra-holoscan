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

SUMMARY = "NVIDIA Nsight Systems UI"

require nsight-systems-common.inc

do_install() {
    install -d ${D}${NSIGHT_DIR}
    cp -rd --no-preserve=ownership ${WORKDIR}${NSIGHT_DIR}/host-linux-armv8 ${D}${NSIGHT_DIR}

    # Create nsys-ui symlink in the system bin directory.
    install -d ${D}${bindir}
    ln -s ${NSIGHT_DIR}/host-linux-armv8/nsys-ui ${D}${bindir}/nsys-ui

    # Remove the broken Wayland shell plugin due to missing QT6 prebuilt.
    rm -rf ${D}${NSIGHT_DIR}/host-linux-armv8/Plugins/wayland-shell-integration
}

RDEPENDS:${PN} += " \
    bash \
    dbus-lib \
    expat \
    fontconfig \
    freetype \
    glib-2.0 \
    glibc \
    gstreamer1.0 \
    libcurl \
    libgcc \
    libglapi \
    libglvnd \
    libgstallocators-1.0 \
    libgstapp-1.0 \
    libgstaudio-1.0 \
    libgstgl-1.0 \
    libgstpbutils-1.0 \
    libgstphotography-1.0 \
    libgstvideo-1.0 \
    libx11 \
    libx11-xcb \
    libxcb \
    libxcb-glx \
    libxcb-randr \
    libxcb-render \
    libxcb-shape \
    libxcb-shm \
    libxcb-sync \
    libxcb-xfixes \
    libxcb-xkb \
    libxcomposite \
    libxdamage \
    libxext \
    libxfixes \
    libxkbcommon \
    libxkbcommon-x11 \
    libxkbfile \
    libxrandr \
    libxrender \
    libxshmfence \
    libxtst \
    libzstd \
    nspr \
    nss \
    tiff \
    wayland \
    xcb-util-image \
    xcb-util-keysyms \
    xcb-util-renderutil \
    xcb-util-wm \
    zlib \
"
