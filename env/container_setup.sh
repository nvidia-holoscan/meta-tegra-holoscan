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

# BitBake
BITBAKE_REV="f89a35155e3b8d12ac609a165c63ed206751b8da"
git_clone "https://github.com/openembedded/bitbake.git" ${BITBAKE_REV}

# OpenEmbedded Core Layer
OPENEMBEDDED_REV="95cc2d041ad651cfb81b2e55251acf1b86f9ddfc"
git_clone "https://github.com/openembedded/openembedded-core.git" ${OPENEMBEDDED_REV}

# meta-openembedded Layer
META_OPENEMBEDDED_REV="2d4090a7233274e2f876d812c13803ae1e1a2acf"
git_clone "https://github.com/openembedded/meta-openembedded.git" ${META_OPENEMBEDDED_REV}

# meta-tegra layer
META_TEGRA_REV="515f4005add33836e5d4442d8f067ce2848ca194"
git_clone "https://github.com/OE4T/meta-tegra.git" ${META_TEGRA_REV}

# meta-tegra-clara-holoscan-mgx layer (copied from build container)
if [ ! -d "meta-tegra-clara-holoscan-mgx" ]; then
    cp -r /opt/nvidia/meta-tegra-clara-holoscan-mgx .
    chown -R ${user}:${group} meta-tegra-clara-holoscan-mgx
    echo "Initialized meta-tegra-clara-holoscan-mgx"
else
    echo "meta-tegra-clara-holoscan-mgx already exists; skipping"
fi

# Default configuration (copied from build container)
if [ ! -d "build/conf" ]; then
    mkdir -p build/conf
    cp /opt/nvidia/meta-tegra-clara-holoscan-mgx/env/templates/conf/* build/conf
    chown -R ${user}:${group} build
    echo "Initialized configuration defaults in build/conf"
else
    echo "build/conf already exists; skipping"
fi

# Host-side scripts (copied from build container)
cp /opt/nvidia/meta-tegra-clara-holoscan-mgx/env/host_bitbake.sh bitbake.sh
cp /opt/nvidia/meta-tegra-clara-holoscan-mgx/env/host_flash.sh flash.sh
chown ${user}:${group} bitbake.sh
chown ${user}:${group} flash.sh

# Write .buildimage file for use by host-side scripts.
echo ${build_image} > .buildimage
chown ${user}:${group} .buildimage
echo "Wrote '${build_image}' to .buildimage"

echo -e "\nSetup complete."
