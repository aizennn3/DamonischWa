package com.damonisch.wa.module.hooks.utils

import com.damonisch.wa.module.hooks.core.HooksBase
import de.robv.android.xposed.XposedBridge

class DebugHook : HooksBase() {

    override fun executeHook() {
        try {
            XposedBridge.log("WaRevamp: === DebugHook Started ===")

            // Testar as classes específicas que estamos usando
            val testClasses = arrayOf(
                "com.whatsapp.protocol.message",
                "com.whatsapp.jid.Jid",
                "com.whatsapp.messaging.send.readreceipt.ReadReceiptManager",
                "com.whatsapp.messaging.send.receipt.ReceiptManager",
                "com.whatsapp.meon.MeManager",
                "com.whatsapp.meon.MeManagerCompanion"
            )

            testClasses.forEach { className ->
                try {
                    val clazz = Class.forName(className, false, classLoader)
                    XposedBridge.log("WaRevamp: ✓ Found class: $className")

                    // Listar métodos para debugging
                    val methods = clazz.declaredMethods.take(5) // Pegar apenas os primeiros 5 métodos
                    methods.forEach { method ->
                        XposedBridge.log("WaRevamp:   Method: ${method.name} - Params: ${method.parameterTypes.map { it.simpleName }}")
                    }

                } catch (e: ClassNotFoundException) {
                    XposedBridge.log("WaRevamp: ✗ Class not found: $className")
                } catch (e: Throwable) {
                    XposedBridge.log("WaRevamp: ! Error with class $className: ${e.message}")
                }
            }

            XposedBridge.log("WaRevamp: === DebugHook Completed ===")

        } catch (e: Throwable) {
            XposedBridge.log("WaRevamp: DebugHook error: ${e.message}")
        }
    }
}