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

SUMMARY = "JSON for Modern C++"
HOMEPAGE = "https://json.nlohmann.me/"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${S}/LICENSE.MIT;md5=dd0607f896f392c8b7d0290a676efc24"

SRC_URI = "git://github.com/nlohmann/json.git;branch=develop;protocol=https"
SRCREV = "db78ac1d7716f56fc9f1b030b715f872f93964e4"

S = "${WORKDIR}/git"

inherit cmake

EXTRA_OECMAKE:append = " \
    -DBUILD_TESTING=OFF \
"
