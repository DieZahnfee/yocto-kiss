# Copyright 2026 NXP
SUMMARY = "NXP i.MX93 Cortex-M33 remoteproc/rpmsg demo firmware"
DESCRIPTION = "Prebuilt M33 TCM demo binaries (power-mode-switch, \
rpmsg pingpong, rpmsg str_echo) for the i.MX93 Cortex-M33 core, loaded \
via Linux remoteproc from /lib/firmware."
SECTION = "base"

LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://COPYING;md5=bc649096ad3928ec06a8713b8d787eac"
LICENSE_FLAGS = "NXP_EULA_v63"
LICENSE_FLAGS_DETAILS[NXP_EULA_v63] = "For further details, see ${THISDIR}/files/EULAv63."

NXP_FIRMWARE_ARCHIVE = "imx93-m33-demo-${PV}.bin"
SRC_URI = "https://www.nxp.com/lgfiles/NMG/MAD/YOCTO/${NXP_FIRMWARE_ARCHIVE};fsl-eula=true"
SRC_URI[sha256sum] = "bc504d30cdbc4f3b606bbcf39d78be62c6f7d717439b9d809bda32215b70f3de"

S = "${UNPACKDIR}/imx93-m33-demo-${PV}"

python do_unpack:append() {
    cmd = "sh %s --auto-accept --force" % d.getVar('NXP_FIRMWARE_ARCHIVE')
    bb.process.run(cmd, shell=True, cwd=d.getVar('UNPACKDIR', True))
}

do_install() {
    install -d ${D}${nonarch_base_libdir}/firmware
    install -m 0644 ${S}/*.bin ${D}${nonarch_base_libdir}/firmware/
}

FILES:${PN} += "${nonarch_base_libdir}/firmware/*"
INSANE_SKIP:${PN} += "arch"

COMPATIBLE_MACHINE = "^freiheit93$"
PACKAGE_ARCH = "${MACHINE_ARCH}"
