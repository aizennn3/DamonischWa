package com.damonisch.wa.module.hooks.functions

import com.damonisch.wa.module.hooks.core.HooksBase
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers

class CustomPrivacyHook : HooksBase() {

    override fun executeHook() {
        hookLastSeenPrivacy()
        hookProfilePhotoPrivacy()
        hookStatusPrivacy()
    }

    private fun hookLastSeenPrivacy() {
        val privacySettingsClass = findWppClass("PrivacySettings")

        XposedHelpers.findAndHookMethod(
            privacySettingsClass,
            "getLastSeen",
            object : XC_MethodHook() {
                override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam) {
                    // Definir privacidade do último visto
                    param.result = "all" // ou "contacts", "nobody"
                }
            }
        )
    }

    private fun hookProfilePhotoPrivacy() {
        val privacySettingsClass = findWppClass("PrivacySettings")

        XposedHelpers.findAndHookMethod(
            privacySettingsClass,
            "getProfilePhoto",
            object : XC_MethodHook() {
                override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam) {
                    // Definir privacidade da foto de perfil
                    param.result = "all" // ou "contacts", "nobody"
                }
            }
        )
    }

    private fun hookStatusPrivacy() {
        val privacySettingsClass = findWppClass("PrivacySettings")

        XposedHelpers.findAndHookMethod(
            privacySettingsClass,
            "getStatus",
            object : XC_MethodHook() {
                override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam) {
                    // Definir privacidade do status
                    param.result = "all" // ou "contacts", "nobody"
                }
            }
        )
    }
}