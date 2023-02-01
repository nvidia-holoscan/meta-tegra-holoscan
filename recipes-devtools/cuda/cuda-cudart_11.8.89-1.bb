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

DEPENDS = "cuda-driver cuda-nvcc-headers cuda-cccl"

require cuda-binaries-common.inc

MAINSUM:aarch64 = "c7078c3f419ec55d890a030aa182245977318017c255d670d3f876da5d7120a0"
MAINSUM:x86-64 = "4f603955b63d228e980f14f32dbdabcfa154f4b9659dcc222b746a6c4f450239"

DEVSUM:aarch64 = "d25b28b101b85a7781727d8a3eff9f9d315208ff24da0b9d17dffa9616889bd6"
DEVSUM:x86-64 = "c3ad62dbe3e80a7ba09fb9a69e5819abb274bb398fdcffbd7c7d8fa583dd700d"

inherit siteinfo

do_compile:append() {
    echo "${prefix}/local/cuda-${CUDA_VERSION}/${baselib}" > ${B}/cuda-${CUDA_VERSION_DASHED}.conf
    if [ "${baselib}" != "lib64" -a "${SITEINFO_BITS}" = "64" ]; then
        if [ -e ${B}/usr/local/cuda-${CUDA_VERSION}/${baselib} ]; then
            ln -s ${baselib} ${B}/usr/local/cuda-${CUDA_VERSION}/lib64
        fi
    fi
}

do_install:append:class-target() {
    install -d ${D}${sysconfdir}/ld.so.conf.d
    install -m 0644 ${B}/cuda-${CUDA_VERSION_DASHED}.conf ${D}${sysconfdir}/ld.so.conf.d/
}

FILES:${PN}-dev += " \
    ${prefix}/local/cuda-${CUDA_VERSION}/${baselib}/*.a \
    ${@' ${prefix}/local/cuda-${CUDA_VERSION}/lib64' if d.getVar('baselib') != 'lib64' and d.getVar('SITEINFO_BITS') == '64' else ''} \
"
FILES:${PN}-staticdev = ""
INSANE_SKIP:${PN}-dev += "staticdev"
RDEPENDS:${PN}-dev:append:class-target = " cuda-nvcc-headers cuda-cccl cuda-target-environment"

BBCLASSEXTEND = "native nativesdk"
