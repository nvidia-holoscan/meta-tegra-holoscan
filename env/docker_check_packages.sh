#!/bin/bash
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

#
# This script is run when the docker image is being built, and checks to make
# sure that the binary packages required by the meta-tegra-holoscan layer
# exist in the current build context.
#

CUDNN_PACKAGE="recipes-devtools/cudnn/files/cudnn-linux-sbsa-8.7.0.84_cuda11-archive.tar.xz"
TENSORRT_PACKAGE="recipes-devtools/tensorrt/files/TensorRT-8.5.3.1.Ubuntu-20.04.aarch64-gnu.cuda-11.8.cudnn8.6.tar.gz"
GXF_PACKAGE="recipes-devtools/gxf/files/gxf_22.11_20230223_6b2e34ec_holoscan-sdk_arm64.tar.gz"
HOLOSCAN_ENDOSCOPY_DATA="recipes-devtools/holoscan/files/holoscan_endoscopy_data_20221121.zip"
HOLOSCAN_ULTRASOUND_DATA="recipes-devtools/holoscan/files/holoscan_ultrasound_data_20220608.zip"
HOLOSCAN_MULTI_AI_DATA="recipes-devtools/holoscan/files/holoscan_multi_ai_ultrasound_data_20221201.zip"
RIVERMAX_PACKAGE="recipes-connectivity/rivermax/files/rivermax_ubuntu2004_1.20.10.tar.gz"
NSIGHT_SYSTEMS_PACKAGE="recipes-devtools/nsight-systems/files/nsight-systems-2023.1.1_2023.1.1.127-1_arm64.deb"

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
check_package ${HOLOSCAN_MULTI_AI_DATA}
check_package ${RIVERMAX_PACKAGE}
check_package ${NSIGHT_SYSTEMS_PACKAGE}

exit 0
