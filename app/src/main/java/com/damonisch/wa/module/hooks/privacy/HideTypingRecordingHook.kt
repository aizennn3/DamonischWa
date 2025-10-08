package com.damonisch.wa.module.hooks.privacy

import com.damonisch.wa.module.hooks.core.HooksBase
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers

class HideTypingRecordingHook : HooksBase() {

    override fun executeHook() {
        val typingStatusClass = findWppClass("TypingStatus")

        XposedHelpers.findAndHookMethod(
            typingStatusClass,
            "setTyping",
            object : XC_MethodHook() {
                override fun beforeHookedMethod(param: XC_MethodHook.MethodHookParam) {
                    // Não enviar status de digitando
                    param.result = null
                }
            }
        )

        val recordingStatusClass = findWppClass("RecordingStatus")

        XposedHelpers.findAndHookMethod(
            recordingStatusClass,
            "setRecording",
            object : XC_MethodHook() {
                override fun beforeHookedMethod(param: XC_MethodHook.MethodHookParam) {
                    // Não enviar status de gravando
                    param.result = null
                }
            }
        )
    }
}