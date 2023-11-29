/*
 * Copyright (c) 2023, NVIDIA CORPORATION. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

/**
 * SWIG script with instructions allowing access to AJA's NTV2 library from
 * python.
 */

%module ajantv2

%{
#define AJALinux 1
#define AJA_LINUX 1
#define AJAExport
#define AJA_USE_CPLUSPLUS11

#include <ajantv2/includes/ajatypes.h>
#include <ajantv2/includes/ntv2enums.h>
#include <ajantv2/includes/ntv2driverinterface.h>
#include <ajantv2/src/lin/ntv2linuxdriverinterface.h>
#include <ajantv2/includes/ntv2card.h>
#include <ajantv2/includes/ntv2devicescanner.h>
%}

#define AJALinux 1
#define AJA_LINUX 1
#define AJAExport
#define AJA_USE_CPLUSPLUS11

%include <std_string.i>
%include <stdint.i>
%include <typemaps.i>

// ntv2utils.h declares this but its not in the library.
%ignore NTV2SmpteLineNumber::operator==;
// other problematic definitions (don't declare global variables in header files)
%ignore gNTV2_DEPRECATE;
%ignore __AJA_trigger_link_error_if_incompatible__;
// ambiguous declaration
%ignore SetAudioOutputMonitorSource;

%apply std::string & OUTPUT { std::string & outSerialNumberString };
%apply ULWord & OUTPUT { ULWord & outPCIDeviceID };
%apply ULWord & OUTPUT { ULWord & outNumBytes };
%apply std::string & OUTPUT { std::string & outDateStr };
%apply std::string & OUTPUT { std::string & outTimeStr };
%apply bool & OUTPUT { bool & outIsFailSafe };

%include <ajantv2/includes/ajatypes.h>
%include <ajantv2/includes/ntv2enums.h>
%include <ajantv2/includes/ntv2driverinterface.h>
%include <ajantv2/src/lin/ntv2linuxdriverinterface.h>
%include <ajantv2/includes/ntv2card.h>
%include <ajantv2/includes/ntv2devicescanner.h>
