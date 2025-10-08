package com.damonisch.wa.module.hooks.privacy

import com.damonisch.wa.module.hooks.core.HooksBase
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers

class HideReceiptHook : HooksBase() {

    override fun executeHook() {
        try {
            // Baseado em: https://github.com/Dev4Mod/WaEnhancer/blob/master/app/src/main/java/com/wmods/wppenhacer/xposed/features/privacy/HideReceipt.java

            val xmsMessageClass = findClass("com.whatsapp.protocol.message")
            val jidClass = findClass("com.whatsapp.jid.Jid")

            // Hook para não enviar confirmações de entrega (double check)
            XposedHelpers.findAndHookMethod(
                "com.whatsapp.messaging.send.receipt.ReceiptManager",
                classLoader,
                "A00",
                jidClass, // chatJid
                jidClass, // participantJid
                String::class.java, // messageId
                Long::class.javaPrimitiveType, // timestamp
                object : XC_MethodHook() {
                    override fun beforeHookedMethod(param: XC_MethodHook.MethodHookParam) {
                        XposedBridge.log("WaRevamp: Blocking delivery receipt - chat: ${param.args[0]}, message: ${param.args[2]}")
                        param.result = null
                    }
                }
            )

            // Hook alternativo
            XposedHelpers.findAndHookMethod(
                "com.whatsapp.messaging.send.receipt.ReceiptManager",
                classLoader,
                "sendReceipt",
                jidClass,
                xmsMessageClass,
                Boolean::class.javaPrimitiveType,
                Int::class.javaPrimitiveType,
                object : XC_MethodHook() {
                    override fun beforeHookedMethod(param: XC_MethodHook.MethodHookParam) {
                        XposedBridge.log("WaRevamp: Blocking delivery receipt via sendReceipt")
                        param.result = null
                    }
                }
            )

            XposedBridge.log("WaRevamp: HideReceiptHook loaded successfully")

        } catch (e: Throwable) {
            XposedBridge.log("WaRevamp: HideReceiptHook error: ${e.message}")
        }
    }
}