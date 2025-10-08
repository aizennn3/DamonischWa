package com.damonisch.wa.module.core.databases

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.damonisch.wa.module.core.databases.utils.Database
import com.damonisch.wa.module.core.databases.utils.DatabaseUtils

class WaDatabase(db: SQLiteDatabase) : Database(db) {

    fun getMessageById(messageId: String): Cursor {
        return rawQuery(
            "SELECT * FROM message WHERE _id = ?",
            arrayOf(messageId)
        )
    }

    fun getMessagesByJid(remoteJid: String): Cursor {
        return rawQuery(
            "SELECT * FROM message WHERE key_remote_jid = ? ORDER BY _id DESC",
            arrayOf(remoteJid)
        )
    }

    fun deleteMessage(messageId: String): Boolean {
        return try {
            // Usar execSQL com prepared statement
            execSQL("DELETE FROM message WHERE _id = ?", arrayOf(messageId))
            true
        } catch (e: Exception) {
            false
        }
    }
}