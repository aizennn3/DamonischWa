package com.damonisch.wa.module.hooks.others

import com.damonisch.wa.module.hooks.core.HooksBase
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers

class PinnedLimit : HooksBase() {

    override fun executeHook() {
        val conversationClass = findWppClass("Conversation")

        XposedHelpers.findAndHookMethod(
            conversationClass,
            "getMaxPinnedChats",
            object : XC_MethodHook() {
                override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam) {
                    // Aumentar limite de conversas fixadas
                    param.result = 100
                }
            }
        )
    }
}