package com.damonisch.wa.module.hooks.others

import com.damonisch.wa.module.hooks.core.HooksBase
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers

class OthersHook : HooksBase() {

    override fun executeHook() {
        hookForwardLimit()
        hookBroadcastLimit()
        hookGroupParticipantsLimit()
    }

    private fun hookForwardLimit() {
        val forwardingClass = findWppClass("Forwarding")

        XposedHelpers.findAndHookMethod(
            forwardingClass,
            "getMaxForward",
            object : XC_MethodHook() {
                override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam) {
                    // Aumentar limite de encaminhamento
                    param.result = 1000
                }
            }
        )
    }

    private fun hookBroadcastLimit() {
        val broadcastClass = findWppClass("Broadcast")

        XposedHelpers.findAndHookMethod(
            broadcastClass,
            "getMaxParticipants",
            object : XC_MethodHook() {
                override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam) {
                    // Aumentar limite de broadcast
                    param.result = 1000
                }
            }
        )
    }

    private fun hookGroupParticipantsLimit() {
        val groupClass = findWppClass("Group")

        XposedHelpers.findAndHookMethod(
            groupClass,
            "getMaxParticipants",
            object : XC_MethodHook() {
                override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam) {
                    // Aumentar limite de participantes no grupo
                    param.result = 1000
                }
            }
        )
    }
}