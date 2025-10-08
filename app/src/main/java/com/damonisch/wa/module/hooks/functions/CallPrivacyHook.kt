package com.damonisch.wa.module.hooks.functions

import com.damonisch.wa.module.hooks.core.HooksBase
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers

class CallPrivacyHook : HooksBase() {

    override fun executeHook() {
        val privacySettingsClass = findWppClass("PrivacySettings")

        XposedHelpers.findAndHookMethod(
            privacySettingsClass,
            "getCallPrivacy",
            object : XC_MethodHook() {
                override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam) {
                    // Forçar privacidade de chamadas para "Todos"
                    param.result = "all"
                }
            }
        )
    }
}