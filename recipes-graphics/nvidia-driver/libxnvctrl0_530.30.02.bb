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

SRC_COMMON_DEBS = "${BPN}_${PV}-0ubuntu1_${DEB_PKGARCH}.deb;subdir=${BP};name=${DEB_PKGARCH}"
SRC_URI[arm64.sha256sum] = "73b290f3f523ce40fa27eec7be11bf979d71db88c23625bd8831a285856fa417"
SRC_URI[amd64.sha256sum] = "b130db87414d680790bed089cc08cd75ef7576800d6a2251bf77cd8cc78bc313"

RDEPENDS:${PN} = " \
    libx11 \
    libxext \
"
