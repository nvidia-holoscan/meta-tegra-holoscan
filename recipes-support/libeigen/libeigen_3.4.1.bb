# Copyright (c) 2026, NVIDIA CORPORATION. All rights reserved.
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
# Based on meta-openembedded/meta-oe/recipes-support/libeigen/libeigen_3.4.0.bb
# (scarthgap, commit e92d0173a8). Bumped to the upstream 3.4.1 maintenance
# tag (SRCREV d71c30c) so consumers get the templated
# 'min<NaNPropagation>' / 'max<NaNPropagation>' overloads added in
# Eigen/src/plugins/ArrayCwiseBinaryOps.h between 3.4.0 and 3.4.1, which
# ORT 1.24.2 source requires (e.g. element_wise_ops.cc, einsum_*.cc).

DESCRIPTION = "Eigen is a C++ template library for linear algebra: matrices, vectors, numerical solvers, and related algorithms."
HOMEPAGE = "http://eigen.tuxfamily.org/"
LICENSE = "MPL-2.0 & Apache-2.0 & BSD-3-Clause & GPL-2.0-only & MINPACK"
# The GPL code is only used for benchmark tests and does not affect what is installed.
LICENSE:${PN} = "MPL-2.0 & Apache-2.0 & BSD-3-Clause & MINPACK"
LICENSE:${PN}-dbg = "MPL-2.0 & Apache-2.0 & BSD-3-Clause & MINPACK"
LICENSE:${PN}-dev = "MPL-2.0 & Apache-2.0 & BSD-3-Clause & MINPACK"
LIC_FILES_CHKSUM = "file://COPYING.MPL2;md5=815ca599c9df247a0c7f619bab123dad \
                    file://COPYING.APACHE;md5=8de23b8e93c63005353056b2475e9aa5 \
                    file://COPYING.BSD;md5=2dd0510ee95e59ca28834b875bc96596 \
                    file://COPYING.GPL;md5=d32239bcb673463ab874e80d47fae504 \
                    file://COPYING.MINPACK;md5=71d91b0f75ce79a75d3108a72bef8116 \
"

SRC_URI = "git://gitlab.com/libeigen/eigen.git;protocol=http;branch=3.4 \
    file://0001-Default-eigen_packet_wrapper-constructor.patch \
    file://0002-Remove-LGPL-Code-and-references.patch \
"

# tag: 3.4.1
SRCREV = "d71c30c47858effcbd39967097a2d99ee48db464"

S = "${WORKDIR}/git"

inherit cmake

# Eigen 3.4.1 added a family of EIGEN_BUILD_* options at the top of
# CMakeLists.txt (lines 53-88) that default to ${PROJECT_IS_TOP_LEVEL}
# (i.e. ON) when Eigen is the top-level project, as it is in this OE recipe.
# Pin every top-level build switch explicitly so the recipe stays stable
# across upstream default changes:
#
#  - BLAS / LAPACK: turning these OFF avoids Eigen's unconditional
#    'include(CMakeDetermineFortranCompiler)' (CMakeLists.txt:62), which
#    hard-errors because the OE recipe-sysroot-native does not ship
#    aarch64-poky-linux-gfortran. Eigen's own
#    'if (NOT CMAKE_Fortran_COMPILER)' fallback never runs because the
#    include aborts first. 3.4.0 had no such block (verified). meta-tegra-
#    holoscan does not consume Eigen's bundled BLAS/LAPACK outputs (only
#    the Eigen3::Eigen headers / interface target are needed by the SDK
#    and ORT).
#  - TESTING / BTL / SPBENCH / DOC / DEMOS: not needed in a cross-build;
#    keeping them off avoids dragging in optional find_package() calls
#    (FFTW, GLEW, etc.) and host-side executables.
#  - PKGCONFIG / CMAKE_PACKAGE: keep ON so the .pc file and the
#    Eigen3Config.cmake package files are installed (consumers find Eigen
#    via pkg-config or find_package(Eigen3)).
#
# Lower-level EIGEN_TEST_* / EIGEN_SYCL_* / EIGEN_DEFAULT_TO_ROW_MAJOR
# options (CMakeLists.txt lines 294+) are intentionally NOT pinned here:
# they are declared inside 'if (EIGEN_BUILD_TESTING)' blocks and CMake
# would warn 'Manually-specified variables were not used by the project'
# when EIGEN_BUILD_TESTING is OFF.
EXTRA_OECMAKE = " \
    -DEIGEN_BUILD_TESTING=OFF \
    -DEIGEN_BUILD_BLAS=OFF \
    -DEIGEN_BUILD_LAPACK=OFF \
    -DEIGEN_BUILD_BTL=OFF \
    -DEIGEN_BUILD_SPBENCH=OFF \
    -DEIGEN_BUILD_DOC=OFF \
    -DEIGEN_BUILD_DEMOS=OFF \
    -DEIGEN_BUILD_PKGCONFIG=ON \
    -DEIGEN_BUILD_CMAKE_PACKAGE=ON \
"

FILES:${PN}-dev += "${datadir}/eigen3/cmake"

# ${PN} is empty so we need to tweak -dev and -dbg package dependencies
RDEPENDS:${PN}-dev = ""
RRECOMMENDS:${PN}-dbg = "${PN}-dev (= ${EXTENDPKGV})"

BBCLASSEXTEND = "native nativesdk"
