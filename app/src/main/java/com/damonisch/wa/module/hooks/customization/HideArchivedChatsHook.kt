package com.damonisch.wa.module.hooks.customization

import com.damonisch.wa.module.hooks.core.HooksBase
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers

class HideArchivedChatsHook : HooksBase() {

    override fun executeHook() {
        try {
            // Classes reais do WhatsApp baseadas no WaRevamp
            val conversationsFragment = findWppClass("home.conversations.ConversationsFragment")

            XposedHelpers.findAndHookMethod(
                conversationsFragment,
                "A00",
                object : XC_MethodHook() {
                    override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam) {
                        val list = param.result as? java.util.ArrayList<*> ?: return

                        val iterator = list.iterator()
                        while (iterator.hasNext()) {
                            val conversation = iterator.next()
                            if (isConversationArchived(conversation)) {
                                iterator.remove()
                            }
                        }
                        param.result = list
                    }
                }
            )

            XposedBridge.log("WaRevamp: HideArchivedChatsHook loaded successfully")

        } catch (e: Throwable) {
            XposedBridge.log("WaRevamp: HideArchivedChatsHook error: ${e.message}")
        }
    }

    private fun isConversationArchived(conversation: Any?): Boolean {
        return try {
            XposedHelpers.getBooleanField(conversation, "A0N")
        } catch (e: Exception) {
            false
        }
    }
}