# Copyright (c) 2022, NVIDIA CORPORATION. All rights reserved.
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

LICENSE = "CLOSED"

inherit cuda

do_unpack[depends] += " cuda-package-download:do_unpack"
do_unpack() {
    cp -r ${CUDA_UNPACK}/usr/local/cuda-${CUDA_DOTTED_VER}/samples/* ${S}
}

CUDA_SAMPLES ?= " \
    0_Simple/asyncAPI \
    0_Simple/binaryPartitionCG \
    0_Simple/clock \
    0_Simple/cppIntegration \
    0_Simple/cppOverload \
    0_Simple/cudaOpenMP \
    0_Simple/cudaTensorCoreGemm \
    0_Simple/dmmaTensorCoreGemm \
    0_Simple/fp16ScalarProduct \
    0_Simple/globalToShmemAsyncCopy \
    0_Simple/immaTensorCoreGemm \
    0_Simple/inlinePTX \
    0_Simple/matrixMul \
    0_Simple/matrixMulCUBLAS \
    0_Simple/matrixMulDrv \
    0_Simple/simpleAssert \
    0_Simple/simpleAtomicIntrinsics \
    0_Simple/simpleAttributes \
    0_Simple/simpleAWBarrier \
    0_Simple/simpleCallback \
    0_Simple/simpleCooperativeGroups \
    0_Simple/simpleCubemapTexture \
    0_Simple/simpleCudaGraphs \
    0_Simple/simpleDrvRuntime \
    0_Simple/simpleLayeredTexture \
    0_Simple/simpleMultiCopy \
    0_Simple/simpleMultiGPU \
    0_Simple/simpleOccupancy \
    0_Simple/simplePitchLinearTexture \
    0_Simple/simplePrintf \
    0_Simple/simpleSeparateCompilation \
    0_Simple/simpleStreams \
    0_Simple/simpleSurfaceWrite \
    0_Simple/simpleTemplates \
    0_Simple/simpleTexture \
    0_Simple/simpleTextureDrv \
    0_Simple/simpleVoteIntrinsics \
    0_Simple/simpleZeroCopy \
    0_Simple/streamOrderedAllocation \
    0_Simple/streamOrderedAllocationP2P \
    0_Simple/template \
    0_Simple/tf32TensorCoreGemm \
    0_Simple/UnifiedMemoryStreams \
    0_Simple/vectorAdd \
    0_Simple/vectorAddDrv \
    1_Utilities/bandwidthTest \
    1_Utilities/deviceQuery \
    1_Utilities/deviceQueryDrv \
    1_Utilities/p2pBandwidthLatencyTest \
    1_Utilities/UnifiedMemoryPerf \
    2_Graphics/bindlessTexture \
    2_Graphics/Mandelbrot \
    2_Graphics/marchingCubes \
    2_Graphics/simpleGL \
    2_Graphics/simpleTexture3D \
    2_Graphics/volumeFiltering \
    2_Graphics/volumeRender \
    3_Imaging/bicubicTexture \
    3_Imaging/bilateralFilter \
    3_Imaging/boxFilter \
    3_Imaging/convolutionFFT2D \
    3_Imaging/convolutionSeparable \
    3_Imaging/convolutionTexture \
    3_Imaging/dct8x8 \
    3_Imaging/dwtHaar1D \
    3_Imaging/dxtc \
    3_Imaging/EGLStream_CUDA_CrossGPU \
    3_Imaging/EGLStreams_CUDA_Interop \
    3_Imaging/histogram \
    3_Imaging/HSOpticalFlow \
    3_Imaging/imageDenoising \
    3_Imaging/NV12toBGRandResize \
    3_Imaging/postProcessGL \
    3_Imaging/recursiveGaussian \
    3_Imaging/simpleCUDA2GL \
    3_Imaging/SobelFilter \
    3_Imaging/stereoDisparity \
    4_Finance/binomialOptions \
    4_Finance/BlackScholes \
    4_Finance/MonteCarloMultiGPU \
    4_Finance/quasirandomGenerator \
    4_Finance/SobolQRNG \
    5_Simulations/fluidsGL \
    5_Simulations/nbody \
    5_Simulations/oceanFFT \
    5_Simulations/particles \
    5_Simulations/smokeParticles \
    6_Advanced/alignedTypes \
    6_Advanced/c++11_cuda \
    6_Advanced/concurrentKernels \
    6_Advanced/conjugateGradientMultiBlockCG \
    6_Advanced/conjugateGradientMultiDeviceCG \
    6_Advanced/cudaCompressibleMemory \
    6_Advanced/eigenvalues \
    6_Advanced/fastWalshTransform \
    6_Advanced/FDTD3d \
    6_Advanced/FunctionPointers \
    6_Advanced/interval \
    6_Advanced/jacobiCudaGraphs \
    6_Advanced/lineOfSight \
    6_Advanced/matrixMulDynlinkJIT \
    6_Advanced/mergeSort \
    6_Advanced/newdelete \
    6_Advanced/ptxjit \
    6_Advanced/radixSortThrust \
    6_Advanced/reduction \
    6_Advanced/reductionMultiBlockCG \
    6_Advanced/scalarProd \
    6_Advanced/scan \
    6_Advanced/segmentationTreeThrust \
    6_Advanced/shfl_scan \
    6_Advanced/simpleHyperQ \
    6_Advanced/sortingNetworks \
    6_Advanced/threadFenceReduction \
    6_Advanced/threadMigration \
    6_Advanced/transpose \
    6_Advanced/warpAggregatedAtomicsCG \
    7_CUDALibraries/batchCUBLAS \
    7_CUDALibraries/conjugateGradient \
    7_CUDALibraries/conjugateGradientCudaGraphs \
    7_CUDALibraries/conjugateGradientPrecond \
    7_CUDALibraries/conjugateGradientUM \
    7_CUDALibraries/cuSolverDn_LinearSolver \
    7_CUDALibraries/cuSolverRf \
    7_CUDALibraries/cuSolverSp_LinearSolver \
    7_CUDALibraries/cuSolverSp_LowlevelCholesky \
    7_CUDALibraries/cuSolverSp_LowlevelQR \
    7_CUDALibraries/MC_EstimatePiInlineP \
    7_CUDALibraries/MC_EstimatePiInlineQ \
    7_CUDALibraries/MC_EstimatePiP \
    7_CUDALibraries/MC_EstimatePiQ \
    7_CUDALibraries/MC_SingleAsianOptionP \
    7_CUDALibraries/MersenneTwisterGP11213 \
    7_CUDALibraries/nvJPEG \
    7_CUDALibraries/nvJPEG_encoder \
    7_CUDALibraries/randomFog \
    7_CUDALibraries/simpleCUBLAS \
    7_CUDALibraries/simpleCUBLAS_LU \
    7_CUDALibraries/simpleCUBLASXT \
    7_CUDALibraries/simpleCUFFT \
    7_CUDALibraries/simpleCUFFT_2d_MGPU \
    7_CUDALibraries/simpleCUFFT_MGPU \
    7_CUDALibraries/watershedSegmentationNPP \
"

# These samples are disabled for now due to build errors or dependency issues.
DISABLED_CUDA_SAMPLES = " \
    0_Simple/bf16TensorCoreGemm \
    0_Simple/cdpSimplePrint \
    0_Simple/cdpSimpleQuicksort \
    0_Simple/clock_nvrtc \
    0_Simple/inlinePTX_nvrtc \
    0_Simple/simpleMPI \
    0_Simple/matrixMul_nvrtc \
    0_Simple/simpleAssert_nvrtc \
    0_Simple/simpleAtomicIntrinsics_nvrtc \
    0_Simple/simpleTemplates_nvrtc \
    0_Simple/simpleVoteIntrinsics_nvrtc \
    0_Simple/vectorAdd_nvrtc \
    2_Graphics/simpleVulkan \
    2_Graphics/simpleVulkanMMAP \
    3_Imaging/vulkanImageCUDA \
    4_Finance/binomialOptions_nvrtc \
    4_Finance/BlackScholes_nvrtc \
    4_Finance/quasirandomGenerator_nvrtc \
    6_Advanced/cdpAdvancedQuicksort \
    6_Advanced/cdpBezierTessellation \
    6_Advanced/cdpQuadtree \
    7_CUDALibraries/boxFilterNPP \
    7_CUDALibraries/cannyEdgeDetectorNPP \
    7_CUDALibraries/batchedLabelMarkersAndLabelCompressionNPP \
    7_CUDALibraries/FilterBorderControlNPP \
    7_CUDALibraries/freeImageInteropNPP \
    7_CUDALibraries/histEqualizationNPP \
    7_CUDALibraries/simpleCUFFT_callback \
"

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
    GENCODE_FLAGS="${CUDA_NVCC_ARCH_FLAGS}" SMS="${CUDA_ARCHITECTURE}" OPENMP=yes \
    CUDA_PATH="${CUDA_PATH}" HOST_COMPILER="${CUDA_CC}" CCFLAGS="${CUDA_CCFLAGS}" \
    LDFLAGS="-L${STAGING_DIR_HOST}${CUDA_PATH}/lib64 ${TOOLCHAIN_OPTIONS} ${@cuda_filtered_ldflags(d)} -lstdc++ -lm" \
    TARGET_ARCH="${CUDA_TARGET_ARCH}" HOST_ARCH="${BUILD_ARCH}" \
    TARGET_OS="${TARGET_OS}" TARGET_FS="${STAGING_DIR_HOST}" \
    NVCC="${CUDA_NVCC} -ccbin ${CUDA_CC} ${CUDA_NVCC_PATH_FLAGS}" \
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

    mkdir -p ${D}/usr/local/cuda-${CUDA_VERSION}
    cp -rd --no-preserve=ownership ${CUDA_UNPACK}/usr/local/cuda-${CUDA_VERSION}/samples  ${D}/usr/local/cuda-${CUDA_VERSION}
}

FILES:${PN} = " \
    ${bindir} \
"

FILES:${PN}-dev = " \
    /usr/local \
"

DEPENDS:append = " \
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
