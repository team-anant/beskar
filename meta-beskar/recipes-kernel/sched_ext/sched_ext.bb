SUMMARY = "Build extensible schedulers"
DESCRIPTION = "sched_ext is a kernel tool for building extensible schedulers"
LICENSE = "GPL-2.0-only"
DEPENDS = "binutils elfutils elfutils-native bpftool-native pahole-native rust-native rust-llvm-native"
RPROVIDES:${PN} = "sched_ext"

inherit bash-completion kernelsrc kernel-arch

do_populate_lic[depends] += "virtual/kernel:do_shared_workdir"

TOOLCHAIN = "clang"

EXTRA_OEMAKE = "\
    V=1 \
    -C ${S}/tools/sched_ext \
    O=${B} \
    CROSS=${TARGET_PREFIX} \
    CC="${CC} ${DEBUG_PREFIX_MAP} -ffile-prefix-map=${STAGING_KERNEL_DIR}=${KERNEL_SRC_PATH} ${CFLAGS}" \
    HOSTCC="${BUILD_CC} ${BUILD_CFLAGS}" \
    LD="${LD}" \
    AR=${AR} \
    ARCH=${ARCH} \
    bash_compdir=${prefix}/share/bash-completion \
"

do_configure[depends] += "virtual/kernel:do_shared_workdir"

COMPATIBLE_HOST = ".*-linux"
COMPATIBLE_HOST:libc-musl = 'null'

do_compile() {
    oe_runmake
}

do_install() {
    oe_runmake DESTDIR=${D} install
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

python do_package:prepend() {
    d.setVar('PKGV', d.getVar("KERNEL_VERSION").split("-")[0])
}

B = "${WORKDIR}/${BPN}-${PV}"

FILES:${PN} += "${exec_prefix}/bin/*"
