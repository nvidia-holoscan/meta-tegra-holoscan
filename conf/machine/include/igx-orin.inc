TEGRA_BOARDID ?= "3701"
TEGRA_FAB ?= "500"
TEGRA_BOARDSKU ?= "0008"
TEGRA_BOARDREV ?= "P.0"
TEGRA_CHIPREV ?= "1"
NVPMODEL ?= "nvpmodel_p3701_0008_3740_0002"
NVFANCONTROL ?= "nvfancontrol_p3740_0002_c01"

MACHINEOVERRIDES =. "igx-orin:"
TNSPEC_BOOTDEV_DEFAULT ?= "mmcblk0p1"
require conf/machine/include/tegra234.inc

TEGRA_BUPGEN_SPECS ?= "fab=000;boardsku=0008;boardrev=;bup_type=bl \
                       fab=000;boardsku=0008;boardrev=;bup_type=kernel"

# Extracted from jetson_board_spec.cfg for BOARDID=3701 and board=igx-orin-devkit
KERNEL_DEVICETREE ?= "tegra234-p3740-0002+p3701-0008-nv.dtb"
KERNEL_ARGS ?= "mminit_loglevel=4 console=tty0 console=ttyTCU0,115200 firmware_class.path=/etc/firmware fbcon=map:0 net.ifnames=0 video=efifb:off console=tty0"

EMMC_SIZE ?= ""
EMMC_DEVSECT_SIZE ?= "512"
BOOTPART_LIMIT ?= "10485760"
BOOTPART_SIZE ?= "8388608"
ROOTFSPART_SIZE_DEFAULT ?= "30064771072"
ODMDATA ?= "gbe-uphy-config-8,hsio-uphy-config-0,nvhs-uphy-config-0"
EMMC_BCT ?= "tegra234-p3701-0008-sdram-l4t.dts"
NVIDIA_BOARD ?= "generic"
TEGRA_AUDIO_DEVICE ?= "tegra-hda-jetson-agx"

OTABOOTDEV ?= "/dev/mtdblock0"
OTAGPTDEV ?= "/dev/mtdblock0"

TEGRA_PLUGIN_MANAGER_OVERLAYS ?= "tegra234-carveouts.dtbo tegra-optee.dtbo"

TEGRA_FLASHVAR_BPFDTB_FILE ?= "tegra234-bpmp-3701-0008-3740-0002-c00.dtb"
TEGRA_FLASHVAR_BPF_FILE ?= "bpmp_t234-TA990SA-A1_prod.bin"
TEGRA_FLASHVAR_BR_CMD_CONFIG ?= "tegra234-mb1-bct-reset-p3701-0002-p3740-0002.dts"
TEGRA_FLASHVAR_DEVICEPROD_CONFIG ?= "tegra234-mb1-bct-cprod-p3701-0002-p3740-0002.dts"
TEGRA_FLASHVAR_DEVICE_CONFIG ?= "tegra234-mb1-bct-device-p3701-0002-p3740-0002.dts"
TEGRA_FLASHVAR_DEV_PARAMS ?= "tegra234-br-bct-p3701-0002-p3740-0002.dts"
TEGRA_FLASHVAR_DEV_PARAMS_B ?= "tegra234-br-bct_b-p3701-0002-p3740-0002.dts"
TEGRA_FLASHVAR_EMC_FUSE_DEV_PARAMS ?= "tegra234-br-bct-diag-boot.dts"
TEGRA_FLASHVAR_GPIOINT_CONFIG ?= "tegra234-mb1-bct-gpioint-p3701-0002-p3740-0002.dts"
TEGRA_FLASHVAR_MB2BCT_CFG ?= "tegra234-mb2-bct-misc-p3701-0002-p3740-0002.dts"
TEGRA_FLASHVAR_MINRATCHET_CONFIG ?= "--minratchet_config tegra234-mb1-bct-ratchet-p3701-0000.dts"
TEGRA_FLASHVAR_MISC_CONFIG ?= "tegra234-mb1-bct-misc-p3701-0008-flash.dts"
TEGRA_FLASHVAR_MISC_COLD_BOOT_CONFIG ?= "tegra234-mb1-bct-misc-p3701-0008-p3740-0002.dts"
TEGRA_FLASHVAR_PINMUX_CONFIG ?= "tegra234-mb1-bct-pinmux-p3701-0008-p3740-0002-c01.dtsi"
TEGRA_FLASHVAR_PMC_CONFIG ?= "tegra234-mb1-bct-padvoltage-p3701-0002-p3740-0002.dtsi"
TEGRA_FLASHVAR_PMIC_CONFIG ?= "tegra234-mb1-bct-pmic-p3701-0008-p3740-0002-c01.dts"
TEGRA_FLASHVAR_PROD_CONFIG ?= "tegra234-mb1-bct-prod-p3701-0002-p3740-0002.dts"
TEGRA_FLASHVAR_SCR_CONFIG ?= "tegra234-mb2-bct-firewall-si-p3701-0002-p3740-0002.dts"
TEGRA_FLASHVAR_UPHY_CONFIG ?= ""
TEGRA_FLASHVAR_WB0SDRAM_BCT ?= "tegra234-p3701-0008-wb0sdram-l4t.dts"

TEGRA_FLASHVAR_RAMCODE ?= "4"
TEGRA_FLASHVAR_CHIP_SKU ?= "00:00:00:90"

USE_REDUNDANT_FLASH_LAYOUT_DEFAULT ?= "0"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += " \
    kernel-module-8021q kernel-module-garp kernel-module-mrp kernel-module-stp \
    kernel-module-llc kernel-module-cdc-ether kernel-module-cdc-subset kernel-module-usbnet \
    kernel-module-snd-hda-intel kernel-module-snd-intel-dspcfg kernel-module-nvme \
    kernel-module-nvme-core kernel-module-cfg80211 kernel-module-btrtl kernel-module-btintel \
    kernel-module-btbcm kernel-module-tegra210-adma kernel-module-ina2xx kernel-module-tegra-aconnect \
    kernel-module-stusb160x kernel-module-phy-tegra194-p2u kernel-module-typec kernel-module-at24 \
    kernel-module-tegra-xudc kernel-module-pcie-tegra194 kernel-module-crct10dif-ce \
    kernel-module-v4l2-dv-timings kernel-module-lm90 kernel-module-tegra-bpmp-thermal \
    kernel-module-governor-userspace kernel-module-v4l2-fwnode kernel-module-v4l2-async \
    kernel-module-videobuf2-dma-contig kernel-module-videobuf2-memops kernel-module-videobuf2-v4l2 \
    kernel-module-videobuf2-common kernel-module-videodev kernel-module-cec kernel-module-drm-kms-helper \
    kernel-module-crypto-engine kernel-module-pwm-fan kernel-module-ina3221 kernel-module-ipv6 \
"
