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

#
# Use SWIG to translate ajantv2.i into a python module that wraps calls to the
# AJA NTV2 library.
#

cmake_minimum_required(VERSION 3.22)

project(python3_ajantv2 LANGUAGES C CXX)

set(CMAKE_CXX_STANDARD 11)

list(APPEND CMAKE_PREFIX_PATH ${ajantv2_DIR})

find_package(ajantv2 REQUIRED)
get_target_property(ajantv2_include_directories AJA::ajantv2 INTERFACE_INCLUDE_DIRECTORIES)
include_directories(${ajantv2_include_directories})

find_package(SWIG REQUIRED)
include(${SWIG_USE_FILE})

find_package(PythonLibs)
include_directories(${PYTHON_INCLUDE_PATH})

include_directories(${CMAKE_CURRENT_SOURCE_DIR})

list(APPEND CMAKE_SWIG_FLAGS "-py3" "-threads" "-O")

set_source_files_properties(ajantv2.i PROPERTIES CPLUSPLUS ON)
swig_add_module(ajantv2 python ajantv2.i)
swig_link_libraries(ajantv2 ${PYTHON_LIBRARIES} AJA::ajantv2)

install(TARGETS ${SWIG_MODULE_ajantv2_REAL_NAME} DESTINATION ${PYTHON_SITE_PACKAGES})
install(FILES ${CMAKE_BINARY_DIR}/ajantv2.py DESTINATION ${PYTHON_SITE_PACKAGES})
