package com.damonisch.wa.module.core.databases

import android.database.Cursor
import com.damonisch.wa.module.core.databases.utils.Database

class MsgstoreDatabase(db: android.database.sqlite.SQLiteDatabase) : Database(db) {

    fun getMessageRevokedInfo(remoteJid: String, messageId: String): Cursor {
        return rawQuery(
            "SELECT * FROM message WHERE key_remote_jid = ? AND _id = ?",
            arrayOf(remoteJid, messageId)
        )
    }

    fun getMediaMessages(remoteJid: String): Cursor {
        return rawQuery(
            "SELECT * FROM message_media WHERE key_remote_jid = ?",
            arrayOf(remoteJid)
        )
    }
}