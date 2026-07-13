FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append:freiheit93 = " file://0001-mlinux-fix-premature-cfg80211-7.0.0-wireless_dev-gate.patch"

# freiheit93's IW612 is SDIO-only (CONFIG_SD9177=y covers it by default).
# The stock Makefile also builds in PCIe chip variants (CONFIG_PCIE9098,
# CONFIG_PCIEAW693) by default, which pulls in PCIe-only code
# (pci_reset_function()) that moal_main.c doesn't #include <linux/pci.h>
# for - fails to build standalone. Disable the unused PCIe variants instead
# of patching in a header we don't need.
EXTRA_OEMAKE:append:freiheit93 = " CONFIG_PCIE9098=n CONFIG_PCIEAW693=n"
