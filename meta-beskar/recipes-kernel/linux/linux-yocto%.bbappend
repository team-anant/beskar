#Enable Kernel debug features since this is a development image
KERNEL_DEBUG = "True"
# Some options depend on CONFIG_PAHOLE_VERSION, so need to make pahole-native available before do_kernel_configme
do_kernel_configme[depends] += '${@bb.utils.contains("KERNEL_DEBUG", "True", "pahole-native:do_populate_sysroot", "", d)}'
require recipes-kernel/linux/sched_ext.inc
