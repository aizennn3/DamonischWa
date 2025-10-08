package com.damonisch.wa.module.hooks.media

import com.damonisch.wa.module.hooks.core.HooksBase
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers

class MediaQualityHook : HooksBase() {

    override fun executeHook() {
        hookImageQuality()
        hookVideoQuality()
        hookCompressionSettings()
    }

    private fun hookImageQuality() {
        val mediaSettingsClass = findWppClass("MediaSettings")

        XposedHelpers.findAndHookMethod(
            mediaSettingsClass,
            "getImageQuality",
            object : XC_MethodHook() {
                override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam) {
                    // Forçar qualidade máxima
                    param.result = 100
                }
            }
        )
    }

    private fun hookVideoQuality() {
        val mediaSettingsClass = findWppClass("MediaSettings")

        XposedHelpers.findAndHookMethod(
            mediaSettingsClass,
            "getVideoQuality",
            object : XC_MethodHook() {
                override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam) {
                    // Forçar qualidade máxima
                    param.result = 2 // HIGH_QUALITY
                }
            }
        )
    }

    private fun hookCompressionSettings() {
        val mediaUploadClass = findWppClass("MediaUpload")

        XposedHelpers.findAndHookMethod(
            mediaUploadClass,
            "shouldCompress",
            object : XC_MethodHook() {
                override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam) {
                    // Desativar compressão
                    param.result = false
                }
            }
        )
    }
}