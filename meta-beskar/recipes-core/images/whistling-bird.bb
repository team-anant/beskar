SUMMARY = "This is an image used for development and testing of extensible schedulers"

#include kernel source in this image, so that runtime builds can be done
SCHED_KERNEL_SRC ??= "kernel-devsrc"
# TEMP_SCHED_STUFF = "clang meson ninja rust jq libbpf protobuf \
#             protobuf-c coreutils pkgconfig libseccomp \
#             "

IMAGE_INSTALL:append = "packagegroup-core-full-cmdline \
    ${SCHED_KERNEL_SRC} \
    clang \
    meson \
    libbpf \
    git \
    libseccomp \
    elfutils \
    sched_ext \
    "

IMAGE_LINGUAS = " "

inherit core-image

#Allow for easy login
SCHED_ROOT_LOGIN_FEATURES ??= "allow-empty-password allow-root-login empty-root-password"
#utility for ssh(required as multiple shells will be required in testing)
SCHED_SSH_UTILS ??= "ssh-server-openssh"
#generic tools for testing
SCHED_DEV_TOOLS ??= "tools-debug tools-profile tools-sdk"
IMAGE_FEATURES += "\
    ${SCHED_ROOT_LOGIN_FEATURES} \
    ${SCHED_SSH_UTILS} \
    ${SCHED_DEV_TOOLS} \
    "
# Extra space for the rootfs to allow for runtime builds (change later based on requirement)
IMAGE_ROOTFS_EXTRA_SPACE:append = " + 3000000"

