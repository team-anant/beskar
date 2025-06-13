SUMMARY = "This is an image used for development and testing of extensible schedulers"

IMAGE_INSTALL = "packagegroup-core-boot clang"

IMAGE_LINGUAS = " "

inherit core-image

IMAGE_FEATURES += "\
    tools-debug \
    allow-empty-password \
    allow-root-login \
    empty-root-password \
    "
