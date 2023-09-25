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

# This class extends the meta-tegra l4t_deb_pkgfeed class in order to
# configure deb package downloads from the NVIDIA SBSA repository.
inherit l4t_deb_pkgfeed

def nvidia_package_arch(d):
    arch = d.getVar('HOST_ARCH')
    return 'sbsa' if arch == 'aarch64' else arch

# Override the meta-tegra L4T deb package feed.
L4T_DEB_FEED_BASE = "https://developer.download.nvidia.com/compute/cuda/repos/ubuntu2204/${@nvidia_package_arch(d)}"
L4T_DEB_FEED_SKIP_POOL_APPEND = "1"
