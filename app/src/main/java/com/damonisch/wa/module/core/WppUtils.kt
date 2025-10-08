package com.damonisch.wa.module.core

import android.content.Context
import android.content.pm.PackageManager
import de.robv.android.xposed.XposedHelpers

object WppUtils {
    const val PACKAGE_NAME = "com.whatsapp"

    fun getVersionCode(context: Context): Int {
        return try {
            val packageInfo = context.packageManager.getPackageInfo(PACKAGE_NAME, 0)
            packageInfo.longVersionCode.toInt()
        } catch (e: PackageManager.NameNotFoundException) {
            -1
        }
    }

    fun findClass(className: String, classLoader: ClassLoader): Class<*> {
        return XposedHelpers.findClass("$PACKAGE_NAME.$className", classLoader)
    }

    fun findClassIfExists(className: String, classLoader: ClassLoader): Class<*>? {
        return try {
            XposedHelpers.findClass("$PACKAGE_NAME.$className", classLoader)
        } catch (e: Throwable) {
            null
        }
    }
}