# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://rust/scx_loader/LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://rust/scx_rustland_core/LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://rust/scx_stats/LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://rust/scx_stats/scx_stats_derive/LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://rust/scx_utils/LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://scheds/rust/scx_bpfland/LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://scheds/rust/scx_flash/LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://scheds/rust/scx_lavd/LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://scheds/rust/scx_layered/LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://scheds/rust/scx_mitosis/LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://scheds/rust/scx_p2dq/LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://scheds/rust/scx_rlfifo/LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://scheds/rust/scx_rustland/LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://scheds/rust/scx_rusty/LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://scheds/rust/scx_tickless/LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://scheds/rust/scx_wd40/LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://tools/scxctl/LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "gitsm://github.com/Alcyeron/scx.git;protocol=https;branch=main"

# Modify these as desired
PV = "1.0+git"
SRCREV = "c06d8fecc400a6d39984df3f1e7e53165145601f"

S = "${WORKDIR}/git"
DEPENDS = "clang-cross-arm llvm-native meson-native ninja-native rust-native jq-native protobuf-native \
            protobuf-c-native coreutils-native pkgconfig-native libbpf-native libbpf libseccomp elfutils-native zstd-native cargo-native\
            cargo-c-native linux-libc-headers kernel-devsrc bpftool-native\
            "
RDEPENDS:${PN} = " llvm libbpf libseccomp"
RDEPENDS:${PN} = " \
    libbpf \
    libseccomp \
    "
#             "
# NOTE: no Makefile found, unable to determine what needs to be done
SCX_CLANGCFLAGS = "-O2?-g?-fmacro-prefix-map=/build/tmp/work/cortexa15t2hf-neon-beskar-linux-gnueabi/scx/1.0+git/git=/usr/src/debug/scx/1.0+git?-fdebug-prefix-map=/build/tmp/work/cortexa15t2hf-neon-beskar-linux-gnueabi/scx/1.0+git/git=/usr/src/debug/scx/1.0+git?-fmacro-prefix-map=/build/tmp/work/cortexa15t2hf-neon-beskar-linux-gnueabi/scx/1.0+git/build=/usr/src/debug/scx/1.0+git?-fdebug-prefix-map=/build/tmp/work/cortexa15t2hf-neon-beskar-linux-gnueabi/scx/1.0+git/build=/usr/src/debug/scx/1.0+git?-fdebug-prefix-map=/build/tmp/work/cortexa15t2hf-neon-beskar-linux-gnueabi/scx/1.0+git/recipe-sysroot=?-fmacro-prefix-map=/build/tmp/work/cortexa15t2hf-neon-beskar-linux-gnueabi/scx/1.0+git/recipe-sysroot=?-fdebug-prefix-map=/build/tmp/work/cortexa15t2hf-neon-beskar-linux-gnueabi/scx/1.0+git/recipe-sysroot-native=?-fmacro-prefix-map=/build/tmp/work/cortexa15t2hf-neon-beskar-linux-gnueabi/scx/1.0+git/recipe-sysroot-native=?-pipe"
inherit meson pkgconfig
EXTRA_OEMESON:append = " -Dlibbpf_a=${STAGING_LIBDIR}/libbpf.a -Dlibbpf_h=${STAGING_INCDIR}/ \
    -Dbpftool=disabled -Denable_rust=false \
    -Dbpf_extra_cflags=${SCX_CLANGCFLAGS} \
                        "
MESON_BUILDTYPE = "release"
MESON_TARGET = "scx_simple"
# MESONOPTS = " --buildtype ${MESON_BUILDTYPE} \
#               --sysconfdir ${sysconfdir} \
#               --localstatedir ${localstatedir} \
#               --sharedstatedir ${sharedstatedir} \
#               --wrap-mode nodownload \
#               --native-file ${WORKDIR}/meson.native"
# MESON_BUILD_DIR = "${S}/build"
# MESON_INSTALL_PREFIX = "${D}${prefix}"
