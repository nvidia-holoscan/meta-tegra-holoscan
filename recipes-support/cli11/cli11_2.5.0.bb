# Copyright (c) 2026, NVIDIA CORPORATION. All rights reserved.
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
#
# Based on meta-openembedded/meta-oe/recipes-support/cli11/cli11_2.3.2.bb
# (scarthgap, commit e92d0173a8). Bumped to upstream tag v2.5.0
# (SRCREV 4160d259) because the Holoscan SDK 4.2.0 patch
# 0002-Use-external-dependencies-deps.patch raises the CLI11 floor from
# 2.3.2 to 2.5.0 to match SDK 4.2.0's CLI11_VERSION 2.5.0 requirement.
#
# Differences vs the meta-oe scarthgap recipe:
#  - Bumped SRCREV / LIC_FILES_CHKSUM for v2.5.0.
#  - Dropped the catch.hpp pre-fetch and the do_configure:prepend hack;
#    set -DCLI11_BUILD_TESTS=OFF / -DCLI11_BUILD_EXAMPLES=OFF / -DCLI11_BUILD_DOCS=OFF
#    instead. CLI11 is header-only and we only consume its CMake config
#    (CLI11Config.cmake), so tests/examples/docs add no value here and
#    pulling Catch2 in just to satisfy them is gratuitous.

SUMMARY = "C++11 command line parser"
DESCRIPTION = "A command line parser for C++11 and beyond that provides a rich feature set with a simple and intuitive interface."
HOMEPAGE = "https://github.com/CLIUtils/CLI11"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b8bdde6bda8508bef68a39f3e0d7e939"

# tag: v2.5.0
SRCREV = "4160d259d961cd393fd8d67590a8c7d210207348"

SRC_URI = "git://github.com/CLIUtils/CLI11;branch=main;protocol=https"

S = "${WORKDIR}/git"

inherit cmake

EXTRA_OECMAKE = " \
    -DCLI11_BUILD_TESTS=OFF \
    -DCLI11_BUILD_EXAMPLES=OFF \
    -DCLI11_BUILD_DOCS=OFF \
"

# cli11 is a header only C++ library, so the main package will be empty.
RDEPENDS:${PN}-dev = ""

BBCLASSEXTEND = "native nativesdk"
