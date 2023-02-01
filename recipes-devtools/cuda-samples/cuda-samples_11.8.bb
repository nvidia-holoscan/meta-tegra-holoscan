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

SUMMARY = "NVIDIA CUDA Samples"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=bb28b97ff25ae39de442985ec577dbd8"

SRC_URI = "git://github.com/NVIDIA/cuda-samples.git;branch=master;protocol=https"
SRCREV = "81992093d2b8c33cab22dbf6852c070c330f1715"

S = "${WORKDIR}/git/Samples"

inherit cuda

CUDA_SAMPLES = " \
    0_Introduction/asyncAPI \
    0_Introduction/c++11_cuda \
    0_Introduction/clock \
    0_Introduction/clock_nvrtc \
    0_Introduction/concurrentKernels \
    0_Introduction/cppIntegration \
    0_Introduction/cppOverload \
    0_Introduction/cudaOpenMP \
    0_Introduction/fp16ScalarProduct \
    0_Introduction/matrixMul \
    0_Introduction/matrixMulDrv \
    0_Introduction/matrixMulDynlinkJIT \
    0_Introduction/matrixMul_nvrtc \
    0_Introduction/mergeSort \
    0_Introduction/simpleAssert \
    0_Introduction/simpleAssert_nvrtc \
    0_Introduction/simpleAtomicIntrinsics \
    0_Introduction/simpleAtomicIntrinsics_nvrtc \
    0_Introduction/simpleAttributes \
    0_Introduction/simpleAWBarrier \
    0_Introduction/simpleCallback \
    0_Introduction/simpleCooperativeGroups \
    0_Introduction/simpleCubemapTexture \
    0_Introduction/simpleDrvRuntime \
    0_Introduction/simpleHyperQ \
    0_Introduction/simpleLayeredTexture \
    0_Introduction/simpleMultiCopy \
    0_Introduction/simpleMultiGPU \
    0_Introduction/simpleOccupancy \
    0_Introduction/simplePitchLinearTexture \
    0_Introduction/simplePrintf \
    0_Introduction/simpleSeparateCompilation \
    0_Introduction/simpleStreams \
    0_Introduction/simpleSurfaceWrite \
    0_Introduction/simpleTemplates \
    0_Introduction/simpleTemplates_nvrtc \
    0_Introduction/simpleTexture \
    0_Introduction/simpleTexture3D \
    0_Introduction/simpleTextureDrv \
    0_Introduction/simpleVoteIntrinsics \
    0_Introduction/simpleVoteIntrinsics_nvrtc \
    0_Introduction/simpleZeroCopy \
    0_Introduction/template \
    0_Introduction/UnifiedMemoryStreams \
    0_Introduction/vectorAdd \
    0_Introduction/vectorAddDrv \
    0_Introduction/vectorAdd_nvrtc \
    1_Utilities/bandwidthTest \
    1_Utilities/deviceQuery \
    1_Utilities/deviceQueryDrv \
    2_Concepts_and_Techniques/boxFilter \
    2_Concepts_and_Techniques/convolutionSeparable \
    2_Concepts_and_Techniques/convolutionTexture \
    2_Concepts_and_Techniques/dct8x8 \
    2_Concepts_and_Techniques/EGLStream_CUDA_CrossGPU \
    2_Concepts_and_Techniques/EGLStream_CUDA_Interop \
    2_Concepts_and_Techniques/eigenvalues \
    2_Concepts_and_Techniques/FunctionPointers \
    2_Concepts_and_Techniques/histogram \
    2_Concepts_and_Techniques/imageDenoising \
    2_Concepts_and_Techniques/inlinePTX \
    2_Concepts_and_Techniques/inlinePTX_nvrtc \
    2_Concepts_and_Techniques/interval \
    2_Concepts_and_Techniques/MC_EstimatePiInlineP \
    2_Concepts_and_Techniques/MC_EstimatePiInlineQ \
    2_Concepts_and_Techniques/MC_EstimatePiP \
    2_Concepts_and_Techniques/MC_EstimatePiQ \
    2_Concepts_and_Techniques/MC_SingleAsianOptionP \
    2_Concepts_and_Techniques/particles \
    2_Concepts_and_Techniques/radixSortThrust \
    2_Concepts_and_Techniques/reduction \
    2_Concepts_and_Techniques/reductionMultiBlockCG \
    2_Concepts_and_Techniques/scalarProd \
    2_Concepts_and_Techniques/scan \
    2_Concepts_and_Techniques/segmentationTreeThrust \
    2_Concepts_and_Techniques/shfl_scan \
    2_Concepts_and_Techniques/sortingNetworks \
    2_Concepts_and_Techniques/streamOrderedAllocation \
    2_Concepts_and_Techniques/streamOrderedAllocationP2P \
    2_Concepts_and_Techniques/threadFenceReduction \
    2_Concepts_and_Techniques/threadMigration \
    3_CUDA_Features/bf16TensorCoreGemm \
    3_CUDA_Features/binaryPartitionCG \
    3_CUDA_Features/bindlessTexture \
    3_CUDA_Features/cdpAdvancedQuicksort \
    3_CUDA_Features/cdpBezierTessellation \
    3_CUDA_Features/cdpQuadtree \
    3_CUDA_Features/cdpSimplePrint \
    3_CUDA_Features/cdpSimpleQuicksort \
    3_CUDA_Features/cudaCompressibleMemory \
    3_CUDA_Features/cudaTensorCoreGemm \
    3_CUDA_Features/dmmaTensorCoreGemm \
    3_CUDA_Features/globalToShmemAsyncCopy \
    3_CUDA_Features/graphMemoryFootprint \
    3_CUDA_Features/graphMemoryNodes \
    3_CUDA_Features/immaTensorCoreGemm \
    3_CUDA_Features/jacobiCudaGraphs \
    3_CUDA_Features/newdelete \
    3_CUDA_Features/ptxjit \
    3_CUDA_Features/simpleCudaGraphs \
    3_CUDA_Features/tf32TensorCoreGemm \
    3_CUDA_Features/warpAggregatedAtomicsCG \
    4_CUDA_Libraries/batchCUBLAS \
    4_CUDA_Libraries/batchedLabelMarkersAndLabelCompressionNPP \
    4_CUDA_Libraries/conjugateGradient \
    4_CUDA_Libraries/conjugateGradientCudaGraphs \
    4_CUDA_Libraries/conjugateGradientMultiBlockCG \
    4_CUDA_Libraries/conjugateGradientMultiDeviceCG \
    4_CUDA_Libraries/conjugateGradientPrecond \
    4_CUDA_Libraries/conjugateGradientUM \
    4_CUDA_Libraries/cuSolverDn_LinearSolver \
    4_CUDA_Libraries/cuSolverRf \
    4_CUDA_Libraries/cuSolverSp_LinearSolver \
    4_CUDA_Libraries/cuSolverSp_LowlevelCholesky \
    4_CUDA_Libraries/cuSolverSp_LowlevelQR \
    4_CUDA_Libraries/lineOfSight \
    4_CUDA_Libraries/matrixMulCUBLAS \
    4_CUDA_Libraries/MersenneTwisterGP11213 \
    4_CUDA_Libraries/oceanFFT \
    4_CUDA_Libraries/randomFog \
    4_CUDA_Libraries/simpleCUBLAS \
    4_CUDA_Libraries/simpleCUBLAS_LU \
    4_CUDA_Libraries/simpleCUBLASXT \
    4_CUDA_Libraries/simpleCUFFT \
    4_CUDA_Libraries/simpleCUFFT_2d_MGPU \
    4_CUDA_Libraries/simpleCUFFT_callback \
    4_CUDA_Libraries/simpleCUFFT_MGPU \
    4_CUDA_Libraries/watershedSegmentationNPP \
    5_Domain_Specific/bicubicTexture \
    5_Domain_Specific/bilateralFilter \
    5_Domain_Specific/binomialOptions \
    5_Domain_Specific/binomialOptions_nvrtc \
    5_Domain_Specific/BlackScholes \
    5_Domain_Specific/BlackScholes_nvrtc \
    5_Domain_Specific/convolutionFFT2D \
    5_Domain_Specific/dwtHaar1D \
    5_Domain_Specific/dxtc \
    5_Domain_Specific/fastWalshTransform \
    5_Domain_Specific/FDTD3d \
    5_Domain_Specific/fluidsGL \
    5_Domain_Specific/HSOpticalFlow \
    5_Domain_Specific/Mandelbrot \
    5_Domain_Specific/marchingCubes \
    5_Domain_Specific/MonteCarloMultiGPU \
    5_Domain_Specific/nbody \
    5_Domain_Specific/NV12toBGRandResize \
    5_Domain_Specific/p2pBandwidthLatencyTest \
    5_Domain_Specific/postProcessGL \
    5_Domain_Specific/quasirandomGenerator \
    5_Domain_Specific/quasirandomGenerator_nvrtc \
    5_Domain_Specific/recursiveGaussian \
    5_Domain_Specific/simpleGL \
    5_Domain_Specific/smokeParticles \
    5_Domain_Specific/SobelFilter \
    5_Domain_Specific/SobolQRNG \
    5_Domain_Specific/stereoDisparity \
    5_Domain_Specific/volumeFiltering \
    5_Domain_Specific/volumeRender \
    6_Performance/alignedTypes \
    6_Performance/transpose \
    6_Performance/UnifiedMemoryPerf \
"

# These samples are disabled for now due to build errors or dependency issues.
DISABLED_CUDA_SAMPLES = " \
    0_Introduction/simpleCUDA2GL \
    0_Introduction/simpleIPC \
    0_Introduction/simpleMPI \
    0_Introduction/simpleP2P \
    0_Introduction/systemWideAtomics \
    0_Introduction/vectorAddMMAP \
    1_Utilities/topologyQuery \
    2_Concepts_and_Techniques/cuHook \
    2_Concepts_and_Techniques/EGLSync_CUDAEvent_Interop \
    2_Concepts_and_Techniques/streamOrderedAllocationIPC \
    3_CUDA_Features/memMapIPCDrv \
    3_CUDA_Features/StreamPriorities \
    4_CUDA_Libraries/boxFilterNPP \
    4_CUDA_Libraries/cannyEdgeDetectorNPP \
    4_CUDA_Libraries/cudaNvSci \
    4_CUDA_Libraries/cudaNvSciNvMedia \
    4_CUDA_Libraries/cuDLAErrorReporting \
    4_CUDA_Libraries/cuDLAHybridMode \
    4_CUDA_Libraries/cuDLAStandaloneMode \
    4_CUDA_Libraries/FilterBorderControlNPP \
    4_CUDA_Libraries/freeImageInteropNPP \
    4_CUDA_Libraries/histEqualizationNPP \
    4_CUDA_Libraries/nvJPEG \
    4_CUDA_Libraries/nvJPEG_encoder \
    5_Domain_Specific/fluidsD3D9 \
    5_Domain_Specific/fluidsGLES \
    5_Domain_Specific/nbody_opengles \
    5_Domain_Specific/nbody_screen \
    5_Domain_Specific/simpleD3D10 \
    5_Domain_Specific/simpleD3D10RenderTarget \
    5_Domain_Specific/simpleD3D10Texture \
    5_Domain_Specific/simpleD3D11 \
    5_Domain_Specific/simpleD3D11Texture \
    5_Domain_Specific/simpleD3D12 \
    5_Domain_Specific/simpleD3D9 \
    5_Domain_Specific/simpleD3D9Texture \
    5_Domain_Specific/simpleGLES \
    5_Domain_Specific/simpleGLES_EGLOutput \
    5_Domain_Specific/simpleGLES_screen \
    5_Domain_Specific/simpleVulkan \
    5_Domain_Specific/simpleVulkanMMAP \
    5_Domain_Specific/SLID3D10Texture \
    5_Domain_Specific/VFlockingD3D10 \
    5_Domain_Specific/vulkanImageCUDA \
"

def extract_sm(d):
    archflags = d.getVar('CUDA_NVCC_ARCH_FLAGS').split()
    for flag in archflags:
        parts = flag.split('=')
        if len(parts) == 2 and parts[0] == '--gpu-code':
            return parts[1].split('_')[1]
    return ''

def cuda_target_arch(d):
    arch = d.getVar('TARGET_ARCH')
    return 'sbsa' if arch == 'aarch64' else arch

def cuda_filtered_ldflags(d):
    newflags = []
    for flag in d.getVar('LDFLAGS').split():
        if flag.startswith('-f'):
            continue
        if flag.startswith('-Wl,'):
            newflags.append(flag[4:])
        else:
            newflags.append(flag)
    return ' '.join(newflags)

CUDA_CC =  "${@cuda_extract_compiler('CC_FOR_CUDA', d)[0]}"
CUDA_CCFLAGS = "${@cuda_extract_compiler('CC_FOR_CUDA', d, prefix='')[1]}"
CUDA_TARGET_ARCH = "${@cuda_target_arch(d)}"
CUDA_NVCC = "${CUDACXX}"

EXTRA_OEMAKE:append = " \
    GENCODE_FLAGS="${CUDA_NVCC_ARCH_FLAGS}" SMS="${@extract_sm(d)}" OPENMP=yes \
    CUDA_PATH="${CUDA_PATH}" HOST_COMPILER="${CUDA_CC}" CCFLAGS="${CUDA_CCFLAGS}" \
    LDFLAGS="-L${STAGING_DIR_HOST}${CUDA_PATH}/lib64 ${TOOLCHAIN_OPTIONS} ${@cuda_filtered_ldflags(d)} -lstdc++ -lm" \
    TARGET_ARCH="${CUDA_TARGET_ARCH}" HOST_ARCH="${BUILD_ARCH}" \
    TARGET_OS="${TARGET_OS}" TARGET_FS="${STAGING_DIR_HOST}" \
    NVCC="${CUDA_NVCC} -ccbin ${CUDA_CC} ${CUDA_NVCC_PATH_FLAGS}" \
    CUDA_SEARCH_PATH="${STAGING_DIR_HOST}${libdir}" \
    GLPATH="${STAGING_DIR_HOST}${libdir}" \
    EGLHEADER="${STAGING_DIR_HOST}${includedir}/EGL/egl.h" \
    EGLEXTHEADER="${STAGING_DIR_HOST}${includedir}/EGL/eglext.h" \
"

do_configure() {
    oldwd="$PWD"
    for subdir in ${CUDA_SAMPLES}; do
        cd "$subdir"
        oe_runmake clean
        cd "$oldwd"
    done
}

do_compile() {
    oldwd="$PWD"
    for subdir in ${CUDA_SAMPLES}; do
        cd "$subdir"
        oe_runmake
        cd "$oldwd"
    done
}

do_install() {
    mkdir -p ${D}${bindir}/cuda-samples
    for subdir in ${CUDA_SAMPLES}; do
        export sample_name=`basename ${subdir}`
        export sample_dest=${D}${bindir}/cuda-samples/${subdir}
        mkdir -p ${sample_dest}
        install -m 0755 ${subdir}/${sample_name} ${sample_dest}
        if [ -d ${subdir}/data ]; then
            cp -rd --no-preserve=ownership ${subdir}/data ${sample_dest}
        fi
    done
}

FILES:${PN} = " \
    ${bindir} \
"

FILES:${PN}-dev = " \
    /usr/local \
"

DEPENDS:append = " \
    cuda-profiler-api \
    libglvnd \
    libglu \
    freeglut \
"

RRECOMMENDS:${PN}-dev += " \
    packagegroup-core-buildessential \
    os-release \
    freeglut-dev \
    libglu-dev \
    libx11-dev \
    libxi-dev \
    libxmu-dev \
"

INSANE_SKIP:${PN}-dev += "staticdev"
