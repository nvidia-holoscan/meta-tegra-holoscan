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

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# Pseudo 1.9.1 incorporated the PIE and glibc 2.38 fixes carried by these
# patches in the pinned Poky recipe.
SRC_URI:remove = " \
    file://0001-configure-Prune-PIE-flags.patch \
    file://glibc238.patch \
"

SRCREV = "823895ba708c63f6ae4dcbfc266210f26c02c698"
PV = "1.9.8"

# Ensure every runtime dependency is available before pseudo-native setscene
# tasks execute under fakeroot (Yocto #15963).
PSEUDO_SETSCENE_DEPS = ""
PSEUDO_SETSCENE_DEPS:class-native = "sqlite3-native:do_populate_sysroot"
do_populate_sysroot_setscene[depends] += "${PSEUDO_SETSCENE_DEPS}"
