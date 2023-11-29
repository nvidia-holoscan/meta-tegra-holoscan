# Copyright (c) 2022-2023, NVIDIA CORPORATION. All rights reserved.
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

SUMMARY = "AJA NTV2 SDK"

require ajantv2-common_${PV}.inc

S = "${WORKDIR}/git"

inherit pkgconfig cmake cuda

# Build demos and tools
EXTRA_OECMAKE:append = " \
  -DAJANTV2_DISABLE_DEMOS=OFF \
  -DAJANTV2_DISABLE_DRIVER=ON \
  -DAJANTV2_DISABLE_TESTS=ON \
  -DAJANTV2_DISABLE_TOOLS=OFF \
  -DAJANTV2_DISABLE_PLUGINS=ON \
"

DEPENDS:append = " \
    glew \
    freeglut \
"

FILES:${PN}-dev += " \
    ${prefix}/libajantv2 \
"

SYSROOT_DIRS += " \
    ${prefix}/libajantv2 \
"
