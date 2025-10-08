package com.damonisch.wa.module.hooks.media

import com.damonisch.wa.module.hooks.core.HooksBase
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers

class DownloadViewOnceHook : HooksBase() {

    override fun executeHook() {
        val viewOnceMessageClass = findWppClass("ViewOnceMessage")

        XposedHelpers.findAndHookMethod(
            viewOnceMessageClass,
            "canBeDownloaded",
            object : XC_MethodHook() {
                override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam) {
                    param.result = true
                }
            }
        )

        XposedHelpers.findAndHookMethod(
            viewOnceMessageClass,
            "isAutoDownloadAllowed",
            object : XC_MethodHook() {
                override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam) {
                    param.result = true
                }
            }
        )
    }
}