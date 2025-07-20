SUMMARY = "Linux Kernel Full Development Source"
DESCRIPTION = "Development source for the Linux kernel. This recipe packages the complete \
kernel source tree and build directory from the preferred virtual/kernel provider for \
use in full kernel development or external module builds."

SECTION = "kernel"
LICENSE = "GPL-2.0-only"

inherit linux-kernel-base
inherit module-base

do_install[depends] += "virtual/kernel:do_shared_workdir"
do_install[depends] += "virtual/kernel:do_install"

do_fetch[noexec] = "1"
do_unpack[noexec] = "1"
do_patch[noexec] = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"
deltask do_populate_sysroot

S = "${STAGING_KERNEL_DIR}"
B = "${STAGING_KERNEL_BUILDDIR}"

PACKAGE_ARCH = "${MACHINE_ARCH}"
KERNEL_BUILD_ROOT = "${nonarch_base_libdir}/modules/"

do_install() {
    kerneldir=${D}${KERNEL_BUILD_ROOT}${KERNEL_VERSION}
    install -d $kerneldir

    # Create symlink structure
    rm -f $kerneldir/build $kerneldir/source
    mkdir -p $kerneldir/build

    mkdir -p ${D}/usr/src
    (
        cd ${D}/usr/src
        ln -rs ${D}${KERNEL_BUILD_ROOT}${KERNEL_VERSION}/source kernel
    )

    (
        cd $kerneldir
        ln -s build source
    )

    # Copy entire source tree from ${S}
    echo "Copying full kernel source from ${S} to $kerneldir/build"
    cp -a ${S}/* $kerneldir/build/

    # Copy entire build output from ${B} (includes .config, System.map, etc.)
    echo "Copying full kernel build tree from ${B} to $kerneldir/build"
    cp -a ${B}/* $kerneldir/build/

    # Clean up unnecessary object files and temporary build artifacts
    echo "Cleaning up object and temporary files"
    find $kerneldir/build -name "*.o" -type f -delete
    find $kerneldir/build -name "*.cmd" -type f -delete
    find $kerneldir/build -name "*.mod.c" -type f -delete
    find $kerneldir/build -name ".*.o.d" -type f -delete
    find $kerneldir/build -name "*.ko" -type f -delete
}

FILES:${PN} += "${nonarch_base_libdir}/modules/${KERNEL_VERSION} /usr/src/kernel"
