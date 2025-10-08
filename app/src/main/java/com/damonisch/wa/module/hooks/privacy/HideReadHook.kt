package com.damonisch.wa.module.hooks.privacy

import com.damonisch.wa.module.hooks.core.HooksBase
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers

class HideReadHook : HooksBase() {

    override fun executeHook() {
        try {
            // Baseado em: https://github.com/Dev4Mod/WaEnhancer/blob/master/app/src/main/java/com/wmods/wppenhacer/xposed/features/privacy/HideSeen.java

            val xmsMessageClass = findClass("com.whatsapp.protocol.message")
            val jidClass = findClass("com.whatsapp.jid.Jid")

            // Hook principal do ReadReceiptManager
            XposedHelpers.findAndHookMethod(
                "com.whatsapp.messaging.send.readreceipt.ReadReceiptManager",
                classLoader,
                "A00",
                jidClass, // chatJid
                jidClass, // participantJid
                String::class.java, // messageId
                Long::class.javaPrimitiveType, // timestamp
                object : XC_MethodHook() {
                    override fun beforeHookedMethod(param: XC_MethodHook.MethodHookParam) {
                        XposedBridge.log("WaRevamp: Blocking read receipt - chat: ${param.args[0]}, message: ${param.args[2]}")
                        param.result = null
                    }
                }
            )

            // Hook alternativo se o primeiro não funcionar
            XposedHelpers.findAndHookMethod(
                "com.whatsapp.messaging.send.readreceipt.ReadReceiptManager",
                classLoader,
                "sendReadReceipt",
                jidClass,
                xmsMessageClass,
                Boolean::class.javaPrimitiveType,
                Int::class.javaPrimitiveType,
                object : XC_MethodHook() {
                    override fun beforeHookedMethod(param: XC_MethodHook.MethodHookParam) {
                        XposedBridge.log("WaRevamp: Blocking read receipt via sendReadReceipt")
                        param.result = null
                    }
                }
            )

            XposedBridge.log("WaRevamp: HideReadHook loaded successfully")

        } catch (e: Throwable) {
            XposedBridge.log("WaRevamp: HideReadHook error: ${e.message}")
        }
    }
}