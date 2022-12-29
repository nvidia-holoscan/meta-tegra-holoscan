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

# Parses the Holoscan dataset CMakeFile for dataset ExternalProject targets and
# returns them in a dictionary with a dictionary of their fields.
def get_datasets(d):
    import re

    dataset_cmakefile = f"{d.getVar('S')}/data/CMakeLists.txt"
    with open(dataset_cmakefile, 'r') as f:
        lines = f.readlines()

    pattern_start = re.compile(r'^\s*ExternalProject_Add\(\s*(\S+)\s*$')
    pattern_field = re.compile(r'^\s*(\S+)\s+(\S+)\s*$')

    datasets = {}
    current_dataset = None
    for line in lines:
        if current_dataset:
            if result := re.search(pattern_field, line):
                datasets[current_dataset][result.group(1)] = result.group(2)
            else:
                if not datasets[current_dataset]["DOWNLOAD_NAME"].endswith(".zip"):
                    datasets[current_dataset]["DOWNLOAD_NAME"] += ".zip"
                current_dataset = None
        elif result := re.search(pattern_start, line):
            current_dataset = re.sub(r"_data_download\s*$", "", result.group(1))
            datasets[current_dataset] = {}

    return datasets

# Returns the destination directory for downloaded datasets.
def get_download_dir(d):
    return f"{d.getVar('WORKDIR')}/datasets"

# Returns whether or not a file has the given MD5 checksum.
def md5_matches(filename, md5):
    import hashlib
    with open(filename, 'rb') as f:
        filesum = hashlib.md5(f.read()).hexdigest()
    return filesum == md5


# Task to fetches the datasets (run after the source repo is fetched/unpacked
# so that the CMakeFile is available for parsing to find the needed datasets).
python do_fetch_datasets() {
    import os
    import subprocess
    import sys

    datasets = get_datasets(d)
    download_dir = get_download_dir(d)
    if not os.path.exists(download_dir):
        os.mkdir(download_dir)
    for name, fields in datasets.items():
        print(f"Fetching dataset '{name}'", flush=True)
        filename = f"{download_dir}/{fields['DOWNLOAD_NAME']}"
        if os.path.exists(filename) and md5_matches(filename, fields["URL_MD5"]):
            print("\tFile already exists; skipping", flush=True)
        else:
            print(f"\tDownloading {filename}", flush=True)
            subprocess.run(["wget", "-q", fields["URL"], "-O", filename])
            if not md5_matches(filename, fields["URL_MD5"]):
                sys.exit(f"\tDownloaded dataset {filename} has incorrect MD5. Expected {fields['URL_MD5']}, got {filesum}")
}
do_fetch_datasets[network] = "1"
addtask fetch_datasets after do_patch before do_unpack_datasets

# Task to unpack the fetched datasets.
python do_unpack_datasets() {
    import os
    import subprocess
    import glob

    datasets = get_datasets(d)
    download_dir = get_download_dir(d)
    unpack_dir = f"{d.getVar('S')}/data"
    if not os.path.exists(unpack_dir):
        os.mkdir(unpack_dir)
    for name, fields in datasets.items():
        filename = f"{download_dir}/{fields['DOWNLOAD_NAME']}"
        dst_dir = fields["SOURCE_DIR"].replace("${CMAKE_CURRENT_SOURCE_DIR}/", "")
        print(f"Extracting dataset '{name}' to {unpack_dir}/{dst_dir}", flush=True)
        subprocess.run(["unzip", "-o", filename, "-d", f"{unpack_dir}/{dst_dir}"])

    # Remove the engine files.
    engine_files = glob.glob(f"{unpack_dir}/**/*engine*", recursive = True)
    engine_files = [f for f in engine_files if os.path.isfile(f)]
    for f in engine_files:
        os.remove(f)
}
do_unpack_datasets[depends] += "unzip-native:do_populate_sysroot"
addtask unpack_datasets after do_fetch_datasets before do_configure
