# meta-tegra-holoscan — historical Holoscan Deployment Stack

> [!IMPORTANT]
> **[NVIDIA/meta-tegra-community](https://github.com/NVIDIA/meta-tegra-community/tree/master/recipes-devtools/holoscan)**
> is the public home for **Holoscan SDK 5.x recipes**.

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

The Holoscan SDK 5.x Deployment Stack separates recipes from the configuration
that selects and pins them:

| Repository | Role |
| --- | --- |
| [NVIDIA/meta-tegra-community](https://github.com/NVIDIA/meta-tegra-community) | Shared OpenEmbedded recipes, dependency versions, and patches. |
| [NVIDIA/tegra-demo-distro](https://github.com/NVIDIA/tegra-demo-distro) | Build configuration that pins the recipe layers and selects the machine, distribution, and image. |

Start with the build configuration for the intended release, then use its
pinned layer revisions. Both repositories use `main` as an administrative
control branch; it is not a Holoscan build configuration. Consult their branch
documentation and the instructions for the chosen Holoscan release before
building.

### OpenEmbedded build environment

For newer deployments, start with the build-host requirements and setup
instructions in [NVIDIA/tegra-demo-distro](https://github.com/NVIDIA/tegra-demo-distro)
for the selected release. That configuration selects the recipe layers and
their revisions. Build-environment tooling is maintained separately; follow
the selected release's instructions for any required container or host tools.
The historical builder under this repository's
[`env/` directory](https://github.com/nvidia-holoscan/meta-tegra-holoscan/tree/v4.6.0/env)
belongs to the 4.6.0 workflow.

## License

See [LICENSE](LICENSE). For historical source and bundled components, consult
the license notices at the selected release tag.
