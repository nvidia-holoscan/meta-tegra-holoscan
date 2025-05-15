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

DESCRIPTION = "A library that provides static reflection for enums, work with any enum type without any macro or boilerplate code."
HOMEPAGE = "https://github.com/Neargye/magic_enum"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b15f48588464ec8ef87d2b560aad2caa"

SRC_URI = "git://github.com/Neargye/magic_enum.git;protocol=https;branch=master"
# tag: v0.9.3
SRCREV = "e1ea11a93d0bdf6aae415124ded6126220fa4f28"
PV .= "+git${SRCPV}"

S = "${WORKDIR}/git"

inherit cmake

FILES:${PN}-dev += "${datadir}"
