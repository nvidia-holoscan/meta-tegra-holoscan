# Copyright (c) 2022-2025, NVIDIA CORPORATION. All rights reserved.
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

FROM ubuntu:22.04

ENV LC_ALL=C.UTF-8
ENV LANG=C.UTF-8
ENV DEBIAN_FRONTEND=noninteractive

RUN apt-get update -y \
 && apt-get upgrade -y \
 && apt-get install -y --no-install-recommends \
    gawk \
    wget \
    git-core \
    git-lfs \
    diffstat \
    unzip \
    texinfo \
    gcc \
    build-essential \
    chrpath \
    socat \
    cpio \
    python3 \
    python3-pip \
    python3-pexpect \
    python3-yaml \
    python3-git \
    python3-jinja2 \
    python3-subunit \
    xz-utils \
    debianutils \
    iputils-ping \
    libegl1-mesa \
    libsdl1.2-dev \
    mesa-common-dev \
    ca-certificates \
    locales \
    file \
    zstd \
    liblz4-tool \
    libssl-dev \
    libssl3 \
    openssl \
    autoconf \
    xmlto \
 && rm -rf /var/lib/apt/lists/* \
 && rm -rf /var/cache/apt/archives/partial/* \
 && pip3 install typing-extensions>=4.1.0

RUN locale-gen en_US.UTF-8

WORKDIR /opt/nvidia/meta-tegra-holoscan
COPY . .
RUN ln -s container_setup.sh env/setup.sh
ENV PATH="/opt/nvidia/meta-tegra-holoscan/env:${PATH}"

WORKDIR /workspace
