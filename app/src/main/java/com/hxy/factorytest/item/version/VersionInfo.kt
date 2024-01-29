package com.hxy.factorytest.item.version

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.telephony.TelephonyManager


class VersionInfo {

    fun getIMEI(context: Context, index: Int): String? {
        // check if has the permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                return "";
            }
        }
        return "000";
        /*val telephonyManager =
            context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return telephonyManager?.getImei(index) ?: ""*/
    }

    /*fun getMEID(context: Context, index: Int): String? {
        val manager = context
            .getSystemService(Activity.TELEPHONY_SERVICE) as TelephonyManager
        return manager.getMeid(index)
    }*/

    fun getSN(index: Int): String? {
        return "sn"
    }
}