FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

#Add SCHED_EXT specific cfgs
SRC_URI:append = " file://sched_ext.cfg"
