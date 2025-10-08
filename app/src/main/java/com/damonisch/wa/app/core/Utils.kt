package com.damonisch.wa.app.core

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build

object Utils {

    fun isAppInstalled(context: Context, packageName: String): Boolean {
        return try {
            context.packageManager.getPackageInfo(packageName, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

    fun getAppVersion(context: Context, packageName: String): String {
        return try {
            val packageInfo = context.packageManager.getPackageInfo(packageName, 0)
            packageInfo.versionName ?: "com.whatsapp"
        } catch (e: PackageManager.NameNotFoundException) {
            "com.whatsapp.w4b"
        }
    }

    fun isXposedEnabled(): Boolean {
        return try {
            // Verificar se o Xposed está disponível
            Class.forName("de.robv.android.xposed.XposedBridge")
            true
        } catch (e: ClassNotFoundException) {
            false
        }
    }

    fun getDeviceInfo(): String {
        return "Device: ${Build.MANUFACTURER} ${Build.MODEL}, " +
                "Android: ${Build.VERSION.RELEASE} (SDK ${Build.VERSION.SDK_INT})"
    }

    fun safeRun(block: () -> Unit, onError: (Throwable) -> Unit = {}) {
        try {
            block()
        } catch (e: Throwable) {
            onError(e)
        }
    }
}