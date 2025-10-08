package com.damonisch.wa.module.hooks.customization

import com.damonisch.wa.module.hooks.core.HooksBase
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers

class SeparateGroupsHook : HooksBase() {

    override fun executeHook() {
        val conversationListClass = findWppClass("ConversationListFragment")

        XposedHelpers.findAndHookMethod(
            conversationListClass,
            "getConversations",
            object : XC_MethodHook() {
                override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam) {
                    val list = param.result as? MutableList<*> ?: return

                    val groups = mutableListOf<Any>()
                    val individuals = mutableListOf<Any>()

                    list.forEach { conversation ->
                        conversation?.let { conv ->
                            if (isGroupConversation(conv)) {
                                groups.add(conv)
                            } else {
                                individuals.add(conv)
                            }
                        }
                    }

                    val sortedList = mutableListOf<Any>()
                    sortedList.addAll(individuals)
                    sortedList.addAll(groups)

                    param.result = sortedList
                }
            }
        )
    }

    private fun isGroupConversation(conversation: Any): Boolean {
        return try {
            val jid = XposedHelpers.getObjectField(conversation, "jid") as? String
            jid?.contains("@g.us") == true
        } catch (e: Exception) {
            false
        }
    }
}