package com.damonisch.wa.module.hooks.functions

import com.damonisch.wa.module.hooks.core.HooksBase
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers

class AntiViewOnceHook : HooksBase() {

    override fun executeHook() {
        val messageClass = findWppClass("Message")

        XposedHelpers.findAndHookMethod(
            messageClass,
            "isViewOnce",
            object : XC_MethodHook() {
                override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam) {
                    // Sempre retornar false para view once
                    param.result = false
                }
            }
        )

        // Hook para permitir download de mídia view once
        hookViewOnceMedia()
    }

    private fun hookViewOnceMedia() {
        val mediaMessageClass = findWppClass("MediaMessage")

        XposedHelpers.findAndHookMethod(
            mediaMessageClass,
            "canDownload",
            object : XC_MethodHook() {
                override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam) {
                    // Sempre permitir download
                    param.result = true
                }
            }
        )
    }
}