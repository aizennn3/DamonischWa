package com.damonisch.wa.module.hooks.functions

import com.damonisch.wa.module.core.FMessageInfo
import com.damonisch.wa.module.hooks.core.HooksBase
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers

class AntiRevokeHook : HooksBase() {

    override fun executeHook() {
        val messageClass = findWppClass("Message")
        val messageStorageClass = findWppClass("MessageStorage")

        // Hook para detectar e prevenir revogação
        XposedHelpers.findAndHookMethod(
            messageStorageClass,
            "deleteMessage",
            messageClass,
            object : XC_MethodHook() {
                override fun beforeHookedMethod(param: XC_MethodHook.MethodHookParam) {
                    val message = param.args[0] as? Any ?: return

                    // Verificar se é uma revogação
                    if (isRevokeMessage(message)) {
                        // Prevenir a deleção
                        param.result = null
                    }
                }
            }
        )

        // Hook para ocultar notificação de mensagem apagada
        hookRevokeNotification()
    }

    private fun isRevokeMessage(message: Any): Boolean {
        return try {
            val messageInfo = FMessageInfo.fromMessage(message)
            // Verificar se a mensagem tem conteúdo de revogação
            val data = XposedHelpers.getObjectField(message, "data") as? String
            data?.contains("revoked") == true
        } catch (e: Exception) {
            false
        }
    }

    private fun hookRevokeNotification() {
        val conversationClass = findWppClass("Conversation")

        XposedHelpers.findAndHookMethod(
            conversationClass,
            "onMessageRevoked",
            findWppClass("Message"),
            object : XC_MethodHook() {
                override fun beforeHookedMethod(param: XC_MethodHook.MethodHookParam) {
                    // Prevenir a execução do método de revogação
                    param.result = null
                }
            }
        )
    }
}