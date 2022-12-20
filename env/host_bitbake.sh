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
# This script is copied to and run on the host, and launches bitbake within
# a build container.
#
# The name of the build image that is used by this script must exist in the
# .buildimage file in the workspace root (which is created by setup.sh).
#
#  Usage: bitbake.sh {bitbake arguments}
#

script_path=$(readlink -f ${0})
workspace_path=$(dirname ${script_path})
build_image=$(<${workspace_path}/.buildimage)

if [ -z "${1}" ]; then
    echo "Usage: bitbake.sh {bitbake arguments}"
    exit 1
fi

docker run --rm -it -v ${workspace_path}:/workspace --network host --user $(id -u):$(id -g) ${build_image} container_bitbake.sh ${@}
