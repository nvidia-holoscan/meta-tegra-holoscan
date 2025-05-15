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

SUMMARY  = "A multi-platform library for OpenGL, OpenGL ES, Vulkan, window and input"
HOMEPAGE = "https://www.glfw.org/"
DESCRIPTION = "GLFW is an Open Source, multi-platform library for OpenGL, \
OpenGL ES and Vulkan application development. It provides a simple, \
platform-independent API for creating windows, contexts and surfaces, reading \
input, handling events, etc."
LICENSE  = "Zlib"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=98d93d1ddc537f9b9ea6def64e046b5f"
SECTION = "lib"

inherit pkgconfig cmake features_check

SRC_URI = "git://github.com/glfw/glfw.git;protocol=https;branch=master"
#tag: 3.4
SRCREV = "7b6aead9fb88b3623e3b3725ebb42670cbe4c579"
PV .= "+git${SRCPV}"

S = "${WORKDIR}/git"

EXTRA_OECMAKE += "-DBUILD_SHARED_LIBS=ON -DGLFW_BUILD_DOCS=OFF"

CFLAGS += "-fPIC"

DEPENDS = "libpng zlib"
REQUIRED_DISTRO_FEATURES = "opengl x11"
ANY_OF_DISTRO_FEATURES = "wayland x11"

PACKAGECONFIG ??= "x11 wayland"

PACKAGECONFIG[wayland] = "-DGLFW_BUILD_WAYLAND=ON,-DGLFW_BUILD_WAYLAND=OFF,wayland wayland-native wayland-protocols libxkbcommon"
PACKAGECONFIG[x11] = ",,libxrandr libxinerama libxi libxcursor libglu"

COMPATIBLE_HOST:libc-musl = "null"
