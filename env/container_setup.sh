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
# This script is run in a build container, and populates the /workspace
# directory with the components, scripts, and configuration defaults that are
# required for building. Ownership of these files are given to the provided user
# and group id, and the provided build image name is written to .buildimage
# to be used by future scripts (e.g. build.sh).
#
#  Usage: setup.sh {build image} {user id} {group id}
#

build_image=${1}
user=${2}
group=${3}

cd /workspace

git_clone() {
    repo_name=$(echo ${1} | sed -rn "s/.*\/([^/]*).git$/\1/p")
    if [ ! -d "${repo_name}" ]; then
        git clone -b master ${1}
        pushd ${repo_name} > /dev/null
        git -c advice.detachedHead=false checkout ${2}
        popd > /dev/null
        chown -R ${user}:${group} ${repo_name}
        echo
    else
        echo "${repo_name} already exists; skipping."
    fi
}

# Poky
POKY_REV="65dafea22018052fe7b2e17e6e4d7eb754224d38"
git_clone "https://git.yoctoproject.org/poky.git" ${POKY_REV}

# meta-openembedded layer
META_OPENEMBEDDED_REV="278ec081a64e6a7679d6def550101158126cd935"
git_clone "https://github.com/openembedded/meta-openembedded.git" ${META_OPENEMBEDDED_REV}

# meta-virtualization layer
META_VIRTUALIZATION_REV="9a94fa2ad76990b0eca40837a98aaf4cd83a7248"
git_clone "https://git.yoctoproject.org/meta-virtualization.git" ${META_VIRTUALIZATION_REV}

# meta-tegra layer
META_TEGRA_REV="f779da5c7028d44d6c1b3bf9f44e795128ce3d7b"
git_clone "https://github.com/nvidia-holoscan/meta-tegra.git" ${META_TEGRA_REV}

# meta-tegra-holoscan layer (copied from build container)
if [ ! -d "meta-tegra-holoscan" ]; then
    cp -r /opt/nvidia/meta-tegra-holoscan .
    chown -R ${user}:${group} meta-tegra-holoscan
    echo "Initialized meta-tegra-holoscan"
else
    echo "meta-tegra-holoscan already exists; skipping"
fi

# Default configuration (copied from build container)
if [ ! -d "build/conf" ]; then
    mkdir -p build/conf
    cp /opt/nvidia/meta-tegra-holoscan/env/templates/conf/* build/conf
    chown -R ${user}:${group} build
    echo "Initialized configuration defaults in build/conf"
else
    echo "build/conf already exists; skipping"
fi

# Host-side scripts (copied from build container)
cp /opt/nvidia/meta-tegra-holoscan/env/host_bitbake.sh bitbake.sh
cp /opt/nvidia/meta-tegra-holoscan/env/host_flash.sh flash.sh
chown ${user}:${group} bitbake.sh
chown ${user}:${group} flash.sh

# Write .buildimage file for use by host-side scripts.
echo ${build_image} > .buildimage
chown ${user}:${group} .buildimage
echo "Wrote '${build_image}' to .buildimage"

echo -e "\nSetup complete."
