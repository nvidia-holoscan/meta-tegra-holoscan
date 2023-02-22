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
# This script is copied to and run on the host, and flashes
# an image to the target device.
#
# The name of the machine to flash is read from the MACHINE
# configuration in the build config (build/conf/local.conf)
#
#  Usage: flash.sh {image name}
#

script_path=$(readlink -f ${0})
workspace_path=$(dirname ${script_path})
machine=$(cat ${workspace_path}/build/conf/local.conf | sed -n "s/^MACHINE[^\"]*\"\(.*\)\"$/\1/p")

if [ -z "${1}" ]; then
    echo "Usage: flash.sh {image name}"
    exit 1
fi

if ! command -v dtc &> /dev/null; then
    echo "The device tree compiler (dtc) is not installed. Please install it with the following:"
    echo "    sudo apt-get install device-tree-compiler"
    exit 1
fi

image_name="${1}"
image_file="${image_name}-${machine}.tegraflash.tar.gz"
image_dir="${workspace_path}/build/tmp/deploy/images/${machine}"
image_path="${image_dir}/${image_file}"

echo -e "Machine: ${machine}"
echo -e "Image:   ${image_name}"
echo -e "Path:    ${image_path}\n"
if [ ! -f "${image_path}" ]; then
    echo "Error: image does not exist; exiting"
    exit 1
fi

tmpdir=$(mktemp -d)
pushd ${tmpdir} > /dev/null

echo -n "Extracting to ${tmpdir}..."
tar -xf ${image_path}
echo -e "Done!\n"

echo "Flashing..."
set -e
sudo ./doflash.sh
echo "Done!"

popd > /dev/null
sudo rm -rf ${tmpdir}
