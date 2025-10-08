package com.damonisch.wa.module.hooks.media

import android.view.WindowManager
import com.damonisch.wa.module.hooks.core.HooksBase
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers

class DisableFlagSecureHook : HooksBase() {

    override fun executeHook() {
        val activityClass = findClass("android.app.Activity")

        XposedHelpers.findAndHookMethod(
            activityClass,
            "onResume",
            object : XC_MethodHook() {
                override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam) {
                    val activity = param.thisObject
                    val window = XposedHelpers.callMethod(activity, "getWindow") as? android.view.Window

                    window?.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
                }
            }
        )
    }
}