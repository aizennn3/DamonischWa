package com.damonisch.wa.module.hooks.privacy

import com.damonisch.wa.module.hooks.core.HooksBase
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers

class FreezeLastSeenHook : HooksBase() {

    override fun executeHook() {
        try {
            // Baseado em: https://github.com/Dev4Mod/WaEnhancer/blob/master/app/src/main/java/com/wmods/wppenhacer/xposed/features/privacy/FreezeLastSeen.java

            val meManagerClass = findClass("com.whatsapp.meon.MeManager")
            val companionClass = findClass("com.whatsapp.meon.MeManagerCompanion")

            // Hook para congelar o último visto
            XposedHelpers.findAndHookMethod(
                meManagerClass,
                "setLastSeen",
                Long::class.javaPrimitiveType,
                object : XC_MethodHook() {
                    override fun beforeHookedMethod(param: XC_MethodHook.MethodHookParam) {
                        XposedBridge.log("WaRevamp: Freezing last seen - attempted timestamp: ${param.args[0]}")
                        param.result = null
                    }
                }
            )

            // Hook do companion se existir
            try {
                XposedHelpers.findAndHookMethod(
                    companionClass,
                    "setLastSeen",
                    meManagerClass,
                    Long::class.javaPrimitiveType,
                    object : XC_MethodHook() {
                        override fun beforeHookedMethod(param: XC_MethodHook.MethodHookParam) {
                            XposedBridge.log("WaRevamp: Freezing last seen via companion")
                            param.result = null
                        }
                    }
                )
            } catch (e: Throwable) {
                // Companion pode não existir, ignora
            }

            XposedBridge.log("WaRevamp: FreezeLastSeenHook loaded successfully")

        } catch (e: Throwable) {
            XposedBridge.log("WaRevamp: FreezeLastSeenHook error: ${e.message}")
        }
    }
}