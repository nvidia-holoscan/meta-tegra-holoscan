# meta-tegra-holoscan — historical Holoscan Deployment Stack

> [!IMPORTANT]
> **This page was written in October 2026.** This repository is archived and
> the page will not be maintained. The instructions, links, and references
> below reflect that point in time and may become outdated.
>
> For current Holoscan guidance, see the
> [Holoscan SDK User Guide](https://docs.nvidia.com/holoscan/sdk-user-guide),
> including [Yocto on Jetson](https://docs.nvidia.com/holoscan/sdk-user-guide/yocto).
> Upstream **Holoscan SDK 5.x recipes** are available in
> [OE4T/meta-tegra-community](https://github.com/OE4T/meta-tegra-community).

This repository preserves the OpenEmbedded/Yocto layer and build instructions
for the Holoscan Deployment Stack through **Holoscan SDK 4.6.0**.

This landing branch contains documentation only. The recipes, patches, and
build configurations remain available in the existing release tags and source
branches.

## Use the 4.6.0 release

The latest public release of this layer is
[`v4.6.0`](https://github.com/nvidia-holoscan/meta-tegra-holoscan/tree/v4.6.0).
Use its [build instructions](https://github.com/nvidia-holoscan/meta-tegra-holoscan/blob/v4.6.0/env/README.md)
and [changelog](https://github.com/nvidia-holoscan/meta-tegra-holoscan/blob/v4.6.0/CHANGELOG.md).

```sh
git clone --branch v4.6.0 --single-branch \
  https://github.com/nvidia-holoscan/meta-tegra-holoscan.git
```

Earlier versions remain available in the
[release tags](https://github.com/nvidia-holoscan/meta-tegra-holoscan/tags).
Use the instructions from the selected tag; this landing branch cannot be
added to a BitBake build as a layer.

## Holoscan SDK 5.x transition

Follow the maintained
[Yocto on Jetson guide](https://docs.nvidia.com/holoscan/sdk-user-guide/yocto)
for build-host setup, the demo image, and integration into your own distro.
The guide explains release-validation snapshots, upstream development, and
maintained OE4T release/LTS branches, including compatibility requirements.

OE4T maintains the [demo distro](https://github.com/OE4T/tegra-demo-distro)
and [Holoscan recipes](https://github.com/OE4T/meta-tegra-community).
Matching `holoscan-sdk-X.Y.Z` tags in the NVIDIA
[distro](https://github.com/NVIDIA/tegra-demo-distro/tags) and
[recipe](https://github.com/NVIDIA/meta-tegra-community/tags) forks record the
revisions validated for a Holoscan release.

The historical builder in
[`env/`](https://github.com/nvidia-holoscan/meta-tegra-holoscan/tree/v4.6.0/env)
belongs to the 4.6.0 workflow.

## License

See [LICENSE](LICENSE). For historical source and bundled components, consult
the license notices at the selected release tag.
