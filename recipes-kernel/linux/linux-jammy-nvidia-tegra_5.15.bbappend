# Copyright (c) 2023-2026, NVIDIA CORPORATION. All rights reserved.
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

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI:append = " \
    file://Enable-module-signing.cfg \
    file://Disable-modules-provided-by-mlnx-ofed.cfg \
    ${@bb.utils.contains('IMAGE_FEATURES', 'kata-containers', 'file://Enable-kata-container-module-dependencies.cfg', '', d)} \
"

prepare_rt_patch () {
    cp -f ${S}/arch/arm64/configs/defconfig ${S}/arch/arm64/configs/.updated.defconfig
    cp -f ${S}/arch/arm64/configs/tegra_prod_defconfig ${S}/arch/arm64/configs/.updated.tegra_prod_defconfig
    ${S}/scripts/config --file ${S}/arch/arm64/configs/.updated.defconfig --enable PREEMPT_RT  --disable DEBUG_PREEMPT \
        --disable KVM \
        --enable EMBEDDED \
        --enable NAMESPACES \
        --enable OSNOISE_TRACER \
        --enable TIMERLAT_TRACER \
        --enable HWLAT_TRACER \
        --disable CPU_IDLE_TEGRA18X \
        --disable CPU_FREQ_GOV_INTERACTIVE \
        --disable CPU_FREQ_TIMES \
        --disable FAIR_GROUP_SCHED
    ${S}/scripts/config --file ${S}/arch/arm64/configs/.updated.tegra_prod_defconfig --enable PREEMPT_RT  --disable DEBUG_PREEMPT \
        --disable KVM \
        --enable EMBEDDED \
        --enable NAMESPACES \
        --enable OSNOISE_TRACER \
        --enable TIMERLAT_TRACER \
        --enable HWLAT_TRACER \
        --disable CPU_IDLE_TEGRA18X \
        --disable CPU_FREQ_GOV_INTERACTIVE \
        --disable CPU_FREQ_TIMES \
        --disable FAIR_GROUP_SCHED
    rm -f ${S}/arch/arm64/configs/defconfig ${S}/arch/arm64/configs/tegra_prod_defconfig
    cp -f ${S}/arch/arm64/configs/.updated.defconfig ${S}/arch/arm64/configs/defconfig
    cp -f ${S}/arch/arm64/configs/.updated.tegra_prod_defconfig ${S}/arch/arm64/configs/tegra_prod_defconfig
}

python __anonymous () {
    if bb.utils.to_boolean(d.getVar("RT_PATCH")):
        d.appendVarFlag("do_patch", "postfuncs", " prepare_rt_patch")
}
