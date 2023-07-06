# Copyright (c) 2023, NVIDIA CORPORATION. All rights reserved.
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

SUMMARY = "Tensors and Dynamic neural network computation with strong GPU acceleration"
HOMEPAGE = "https://pytorch.org/"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=5c853508d63a8090fa952ff1af58217d"

SRC_URI = "https://github.com/pytorch/pytorch/releases/download/v${PV}/pytorch-v${PV}.tar.gz"
SRC_URI[sha256sum] = "9c564ca440265c69400ef5fdd48bf15e28af5aa4bed84c95efaad960a6699998"

SRC_URI += " \
    file://0001-Remove-Modules_CUDA_fix.patch \
    file://0002-Fix-CUDA-build-rules.patch \
    file://0003-Fix-RPATH.patch \
    file://0004-Fix-nvfuser-install-path.patch \
    file://0005-Remove-nvToolsExt-dependency-from-TorchConfig.cmake.patch \
"

S = "${WORKDIR}/${PN}-v${PV}"

inherit cmake cuda

EXTRA_OECMAKE += " \
    -DGLIBCXX_USE_CXX11_ABI=1 \
    -DBUILD_PYTHON=0 \
    -DBUILD_CUSTOM_PROTOBUF=0 \
    -DUSE_OPENMP=OFF \
    -DUSE_XNNPACK=OFF \
    -DUSE_NCCL=OFF \
    -DCAFFE2_USE_TENSORRT=OFF \
    -DCAFFE2_USE_CUDNN=OFF \
"

# Set flags required for CUDA compilation (which have been removed from
# the problematic CMake files by the patches included above).
CUDA_NVCC_EXTRA_FLAGS += " \
    --expt-relaxed-constexpr \
    --expt-extended-lambda \
    -Xfatbin=-compress-all \
    -DCUDA_HAS_FP16=1 \
    -D__CUDA_NO_HALF_OPERATORS__ \
    -D__CUDA_NO_HALF_CONVERSIONS__ \
    -D__CUDA_NO_HALF2_OPERATORS__ \
    -D__CUDA_NO_BFLOAT16_CONVERSIONS__ \
    ${@'-DCUB_WRAPPED_NAMESPACE=at_cuda_detail' if float(d.getVar('CUDA_VERSION')) > 11.4 else ''} \
"

# Suppress various CUDA warnings.
CUDA_DIAG_SUPPRESS = " \
    cc_clobber_ignored \
    integer_sign_change \
    useless_using_declaration \
    set_but_not_used \
    field_without_dll_interface \
    base_class_has_different_dll_interface \
    dll_interface_conflict_none_assumed \
    dll_interface_conflict_dllexport_assumed \
    implicit_return_from_non_void_function \
    unsigned_compare_with_zero \
    declared_but_not_referenced \
    bad_friend_decl \
"
CUDA_NVCC_EXTRA_FLAGS += "${@' '.join(['-Xcudafe "--diag_suppress=%s"' % s for s in d.getVar('CUDA_DIAG_SUPPRESS').split()])}"

# Fix the "#include_next <math.h>" compilation error when building CUDA files.
EXTRA_OECMAKE += " \
    -DCMAKE_CUDA_IMPLICIT_INCLUDE_DIRECTORIES=${STAGING_INCDIR} \
"

# Generate the sleef binaries used at build time.
EXTRA_OECMAKE += " -DNATIVE_BUILD_DIR=${STAGING_DIR_NATIVE}/usr"
do_compile:prepend() {
	${BUILD_CC} -o ${STAGING_BINDIR_NATIVE}/mkalias ${S}/third_party/sleef/src/libm/mkalias.c
	${BUILD_CC} -o ${STAGING_BINDIR_NATIVE}/mkrename ${S}/third_party/sleef/src/libm/mkrename.c
}

# PyTorch builds tend to fail or crash with too many threads.
# Limit the thread count to 1/4 of the CPUs available.
PARALLEL_MAKE = "-j ${@int(oe.utils.cpu_count() / 4)}"

DEPENDS += " \
    cuda-nvml \
    cuda-nvrtc-native \
    cuda-nvtx \
    cuda-nvtx-native \
    libcublas-native \
    libcusolver-native \
    libcusparse-native \
    protobuf \
    protobuf-native \
    python3-pyyaml-native \
    python3-typing-extensions-native \
"

FILES:${PN} += " \
    ${datadir}/ATen \
    ${datadir}/cpuinfo \
"

SOLIBS = "*.so*"
FILES_SOLIBSDEV = ""
