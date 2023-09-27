#!/bin/bash
# Copyright (c) 2022-2024, NVIDIA CORPORATION. All rights reserved.
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
# This script is run when the docker image is being built, and checks to make
# sure that the binary packages required by the meta-tegra-holoscan layer
# exist in the current build context.
#

RIVERMAX_PACKAGE="recipes-connectivity/rivermax/files/rivermax_ubuntu2204_1.40.11.tar.gz"

script_path=$(readlink -f ${0})
env_path=$(dirname ${script_path})
root_path=$(dirname ${env_path})

check_package() {
    if [ ! -f "${root_path}/${1}" ]; then
        echo "Missing package required for Docker build: ${1}"
        exit 1
    fi
}

check_package ${RIVERMAX_PACKAGE}

exit 0
