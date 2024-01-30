package com.hxy.factorytest.item.version

import android.os.Build
import com.hxy.factorytest.util.SystemProperties

class VersionInfo {
    var passVisible = true
    fun getVersionInfo(): Map<String, String> {
        var infos: Map<String, String>
        val android = Build.VERSION.RELEASE
        val prop = ""
        val build = ""
        val imei1 = getIMEI(1).toString()
        val imei2 = getIMEI(2).toString()
        val sn1 = getSN(1).toString()
        val sn2 = getSN(2).toString()
        infos = mapOf(
            "android" to android, "prop" to prop, "build" to build,
            "imei1" to imei1, "imei2" to imei2, "sn1" to sn1, "sn2" to sn2
        )
        passVisible = !infos.values.any { it.isEmpty() || it.equals("0") }
        return infos
    }

    fun getIMEI(index: Int): Int {
        return SystemProperties().getInt("persist.sys.hxyimei"+index,0)
    }

    fun getMEID(index: Int): Int {
        return SystemProperties().getInt("persist.sys.hxymeid"+index,0)
    }

    fun getSN(index: Int): Int {
        return SystemProperties().getInt("persist.sys.hxysn"+index,0)
    }
}