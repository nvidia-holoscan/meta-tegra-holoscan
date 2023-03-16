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

SUMMARY = "NVIDIA Discrete GPU (dGPU) Drivers"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = "file://${NVIDIA_SRC}/LICENSE;md5=2cc00be68c1227a7c42ff3620ef75d05"

NVIDIA_ARCHIVE_NAME = "NVIDIA-Linux-${TARGET_ARCH}-${PV}"
NVIDIA_SRC = "${WORKDIR}/${NVIDIA_ARCHIVE_NAME}"

SRC_URI = "https://us.download.nvidia.com/XFree86/${TARGET_ARCH}/${PV}/${NVIDIA_ARCHIVE_NAME}.run"
SRC_URI[sha256sum] = "c2a5478397b1559bd3dd2b4211ef0fcfd6ab182e0f206592fa62b521667e4e8e"

inherit features_check

do_unpack() {
       chmod +x ${DL_DIR}/${NVIDIA_ARCHIVE_NAME}.run
       rm -rf ${NVIDIA_SRC}
       ${DL_DIR}/${NVIDIA_ARCHIVE_NAME}.run -x --target ${NVIDIA_SRC}
}

do_unpack[depends] += "coreutils-native:do_populate_sysroot"

do_install:append() {
    # Core Libraries
    install -d ${D}${libdir}
    install -m 0644 ${NVIDIA_SRC}/libcuda.so.${PV} ${D}${libdir}
    install -m 0644 ${NVIDIA_SRC}/libEGL_nvidia.so.${PV} ${D}${libdir}
    install -m 0644 ${NVIDIA_SRC}/libGLESv1_CM_nvidia.so.${PV} ${D}${libdir}
    install -m 0644 ${NVIDIA_SRC}/libGLESv2_nvidia.so.${PV} ${D}${libdir}
    install -m 0644 ${NVIDIA_SRC}/libGLX_nvidia.so.${PV} ${D}${libdir}
    install -m 0644 ${NVIDIA_SRC}/libnvcuvid.so.${PV} ${D}${libdir}
    install -m 0644 ${NVIDIA_SRC}/libnvidia-allocator.so.${PV} ${D}${libdir}
    install -m 0644 ${NVIDIA_SRC}/libnvidia-cfg.so.${PV} ${D}${libdir}
    install -m 0644 ${NVIDIA_SRC}/libnvidia-eglcore.so.${PV} ${D}${libdir}
    install -m 0644 ${NVIDIA_SRC}/libnvidia-encode.so.${PV} ${D}${libdir}
    install -m 0644 ${NVIDIA_SRC}/libnvidia-fbc.so.${PV} ${D}${libdir}
    install -m 0644 ${NVIDIA_SRC}/libnvidia-glcore.so.${PV} ${D}${libdir}
    install -m 0644 ${NVIDIA_SRC}/libnvidia-glsi.so.${PV} ${D}${libdir}
    install -m 0644 ${NVIDIA_SRC}/libnvidia-glvkspirv.so.${PV} ${D}${libdir}
    install -m 0644 ${NVIDIA_SRC}/libnvidia-gtk2.so.${PV} ${D}${libdir}
    install -m 0644 ${NVIDIA_SRC}/libnvidia-ml.so.${PV} ${D}${libdir}
    install -m 0644 ${NVIDIA_SRC}/libnvidia-ngx.so.${PV} ${D}${libdir}
    install -m 0644 ${NVIDIA_SRC}/libnvidia-nvvm.so.${PV} ${D}${libdir}
    install -m 0644 ${NVIDIA_SRC}/libnvidia-opencl.so.${PV} ${D}${libdir}
    install -m 0644 ${NVIDIA_SRC}/libnvidia-opticalflow.so.${PV} ${D}${libdir}
    install -m 0644 ${NVIDIA_SRC}/libnvidia-ptxjitcompiler.so.${PV} ${D}${libdir}
    install -m 0644 ${NVIDIA_SRC}/libnvidia-rtcore.so.${PV} ${D}${libdir}
    install -m 0644 ${NVIDIA_SRC}/libnvidia-tls.so.${PV} ${D}${libdir}
    install -m 0644 ${NVIDIA_SRC}/libnvoptix.so.${PV} ${D}${libdir}
    ln -sf libcuda.so.${PV} ${D}${libdir}/libcuda.so.1
    ln -sf libcuda.so.1 ${D}${libdir}/libcuda.so
    ln -sf libEGL_nvidia.so.${PV} ${D}${libdir}/libEGL_nvidia.so.0
    ln -sf libGLESv1_CM_nvidia.so.${PV} ${D}${libdir}/libGLESv1_CM_nvidia.so.1
    ln -sf libGLESv2_nvidia.so.${PV} ${D}${libdir}/libGLESv2_nvidia.so.2
    ln -sf libGLX_nvidia.so.${PV} ${D}${libdir}/libGLX_indirect.so.0
    ln -sf libGLX_nvidia.so.${PV} ${D}${libdir}/libGLX_nvidia.so.0
    ln -sf libnvcuvid.so.${PV} ${D}${libdir}/libnvcuvid.so.1
    ln -sf libnvidia-allocator.so.${PV} ${D}${libdir}/libnvidia-allocator.so.1
    ln -sf libnvidia-cfg.so.${PV} ${D}${libdir}/libnvidia-cfg.so.1
    ln -sf libnvidia-encode.so.${PV} ${D}${libdir}/libnvidia-encode.so.1
    ln -sf libnvidia-fbc.so.${PV} ${D}${libdir}/libnvidia-fbc.so.1
    ln -sf libnvidia-ml.so.${PV} ${D}${libdir}/libnvidia-ml.so.1
    ln -sf libnvidia-ngx.so.${PV} ${D}${libdir}/libnvidia-ngx.so.1
    ln -sf libnvidia-nvvm.so.4.0.0 ${D}${libdir}/libnvidia-nvvm.so.4
    ln -sf libnvidia-opencl.so.${PV} ${D}${libdir}/libnvidia-opencl.so.1
    ln -sf libnvidia-opticalflow.so.${PV} ${D}${libdir}/libnvidia-opticalflow.so.1
    ln -sf libnvidia-ptxjitcompiler.so.${PV} ${D}${libdir}/libnvidia-ptxjitcompiler.so.1
    ln -sf libnvoptix.so.${PV} ${D}${libdir}/libnvoptix.so.1

    # VDPAU
    install -d ${D}${libdir}/vdpau
    install -m 0644 ${NVIDIA_SRC}/libvdpau_nvidia.so.${PV} ${D}${libdir}/vdpau
    ln -sf libvdpau_nvidia.so.${PV} ${D}${libdir}/vdpau/libvdpau_nvidia.so.1
    ln -sf vdpau/libvdpau_nvidia.so.1 ${D}${libdir}/libvdpau_nvidia.so

    # XOrg
    install -d ${D}${libdir}/xorg/modules/drivers
    install -d ${D}${libdir}/xorg/modules/extensions
    install -m 0644 ${NVIDIA_SRC}/nvidia_drv.so ${D}${libdir}/xorg/modules/drivers
    install -m 0644 ${NVIDIA_SRC}/libglxserver_nvidia.so.${PV} ${D}${libdir}/xorg/modules/extensions
    ln -sf libglxserver_nvidia.so.${PV} ${D}${libdir}/xorg/modules/extensions/libglxserver_nvidia.so

    # Binaries
    install -d ${D}${bindir}
    install -m 0755 ${NVIDIA_SRC}/nvidia-cuda-mps-control ${D}${bindir}
    install -m 0755 ${NVIDIA_SRC}/nvidia-cuda-mps-server ${D}${bindir}
    install -m 0755 ${NVIDIA_SRC}/nvidia-debugdump ${D}${bindir}
    install -m 0755 ${NVIDIA_SRC}/nvidia-modprobe ${D}${bindir}
    install -m 0755 ${NVIDIA_SRC}/nvidia-persistenced ${D}${bindir}
    install -m 0755 ${NVIDIA_SRC}/nvidia-settings ${D}${bindir}
    install -m 0755 ${NVIDIA_SRC}/nvidia-smi ${D}${bindir}
    install -m 0755 ${NVIDIA_SRC}/nvidia-xconfig ${D}${bindir}

    # Registry key file
    install -d ${D}${datadir}/nvidia
    install -m 0755 ${NVIDIA_SRC}/nvidia-application-profiles-${PV}-key-documentation ${D}${datadir}/nvidia

    # Docs
    install -d ${D}${mandir}/man1
    install -m 0644 ${NVIDIA_SRC}/nvidia-cuda-mps-control.1.gz ${D}${mandir}/man1
    install -m 0644 ${NVIDIA_SRC}/nvidia-modprobe.1.gz ${D}${mandir}/man1
    install -m 0644 ${NVIDIA_SRC}/nvidia-persistenced.1.gz ${D}${mandir}/man1
    install -m 0644 ${NVIDIA_SRC}/nvidia-settings.1.gz ${D}${mandir}/man1
    install -m 0644 ${NVIDIA_SRC}/nvidia-smi.1.gz ${D}${mandir}/man1
    install -m 0644 ${NVIDIA_SRC}/nvidia-xconfig.1.gz ${D}${mandir}/man1

    # GSP Firmware
    install -d ${D}${nonarch_base_libdir}/firmware/nvidia/${PV}
    install -m 0644 ${NVIDIA_SRC}/firmware/*.bin ${D}${nonarch_base_libdir}/firmware/nvidia/${PV}

    # Vulkan ICD
    install -d ${D}${sysconfdir}/vulkan/icd.d
    install -m 0644 ${NVIDIA_SRC}/nvidia_icd.json ${D}${sysconfdir}/vulkan/icd.d/

    # EGL glvnd config
    install -d ${D}${datadir}/glvnd/egl_vendor.d
    install -m 0644 ${NVIDIA_SRC}/10_nvidia.json ${D}${datadir}/glvnd/egl_vendor.d/
}

python populate_packages:prepend() {
    d.appendVar("RDEPENDS:" + d.getVar("PN", True), " xorg-abi-video-25")
}

REQUIRED_DISTRO_FEATURES = "x11"

FILES_SOLIBSDEV = ""

FILES:${PN} += " \
    ${libdir} \
    ${bindir} \
    ${datadir} \
    ${nonarch_base_libdir} \
"

RDEPENDS:${PN} += " \
    libglvnd \
    libvdpau \
    libx11 \
    libxau \
    libxdmcp \
    libxext \
    gtk+ \
    glib-2.0 \
    atk \
    pango \
    gdk-pixbuf \
    xserver-xorg-module-libwfb \
"

TEGRA_LIBRARIES = " \
    tegra-libraries-cuda \
    tegra-libraries-eglcore \
    tegra-libraries-glescore \
    tegra-libraries-glxcore \
    tegra-libraries-vulkan \
"

RPROVIDES:${PN} += "${TEGRA_LIBRARIES}"
RCONFLICTS:${PN} += "${TEGRA_LIBRARIES}"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP:${PN} += "ldflags already-stripped dev-so arch"
