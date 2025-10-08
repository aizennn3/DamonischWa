package com.damonisch.wa.module.hooks.media

import com.damonisch.wa.module.hooks.core.HooksBase
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers

class DownloadStatusHook : HooksBase() {

    override fun executeHook() {
        val statusDownloadClass = findWppClass("StatusDownload")

        XposedHelpers.findAndHookMethod(
            statusDownloadClass,
            "canDownload",
            object : XC_MethodHook() {
                override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam) {
                    // Sempre permitir download de status
                    param.result = true
                }
            }
        )

        // Hook para remover limitações de download
        hookStatusLimitations()
    }

    private fun hookStatusLimitations() {
        val statusSessionClass = findWppClass("StatusSession")

        XposedHelpers.findAndHookMethod(
            statusSessionClass,
            "isDownloadAllowed",
            object : XC_MethodHook() {
                override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam) {
                    param.result = true
                }
            }
        )
    }
}