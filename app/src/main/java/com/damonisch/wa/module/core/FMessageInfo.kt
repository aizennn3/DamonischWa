package com.damonisch.wa.module.core

import de.robv.android.xposed.XposedHelpers

data class FMessageInfo(
    val keyFromMe: Boolean,
    val keyId: String,
    val remoteJid: String,
    val senderJid: String?,
    val fromMe: Boolean,
    val timestamp: Long,
    val status: Int,
    val broadcast: Boolean,
    val participant: String?
) {
    companion object {
        fun fromMessage(message: Any): FMessageInfo {
            return FMessageInfo(
                keyFromMe = XposedHelpers.getBooleanField(message, "key_from_me"),
                keyId = XposedHelpers.getObjectField(message, "key_id") as String,
                remoteJid = XposedHelpers.getObjectField(message, "key_remote_jid") as String,
                senderJid = XposedHelpers.getObjectField(message, "sender_jid") as? String,
                fromMe = XposedHelpers.getBooleanField(message, "key_from_me"),
                timestamp = XposedHelpers.getLongField(message, "timestamp"),
                status = XposedHelpers.getIntField(message, "status"),
                broadcast = XposedHelpers.getBooleanField(message, "broadcast"),
                participant = XposedHelpers.getObjectField(message, "participant") as? String
            )
        }
    }
}