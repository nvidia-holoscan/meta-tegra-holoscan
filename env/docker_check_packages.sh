#!/bin/bash
# Copyright (c) 2022, NVIDIA CORPORATION. All rights reserved.
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
# sure that the binary packages required by the meta-tegra-clara-holoscan-mgx
# layer exist in the current build context.
#

CUDNN_PACKAGE="recipes-devtools/cudnn/files/cudnn-11.4-linux-aarch64sbsa-v8.2.4.15.tgz"
TENSORRT_PACKAGE="recipes-devtools/tensorrt/files/TensorRT-8.2.1.8.Ubuntu-20.04.aarch64-gnu.cuda-11.4.cudnn8.2.tar.gz"
GXF_PACKAGE="recipes-devtools/gxf/files/gxf_2.4.2_20220516_f90116f2_holoscan-sdk_arm64.tar.gz"
HOLOSCAN_ENDOSCOPY_DATA="recipes-devtools/holoscan/files/holoscan_endoscopy_data.zip"
HOLOSCAN_ULTRASOUND_DATA="recipes-devtools/holoscan/files/holoscan_ultrasound_data.zip"

script_path=$(readlink -f ${0})
env_path=$(dirname ${script_path})
root_path=$(dirname ${env_path})

check_package() {
    if [ ! -f "${root_path}/${1}" ]; then
        echo "Missing package required for Docker build: ${1}"
        exit 1
    fi
}

check_package ${CUDNN_PACKAGE}
check_package ${TENSORRT_PACKAGE}
check_package ${GXF_PACKAGE}
check_package ${HOLOSCAN_ENDOSCOPY_DATA}
check_package ${HOLOSCAN_ULTRASOUND_DATA}

exit 0
