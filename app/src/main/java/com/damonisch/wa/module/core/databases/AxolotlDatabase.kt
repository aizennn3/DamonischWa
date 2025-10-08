package com.damonisch.wa.module.core.databases

import android.database.Cursor
import com.damonisch.wa.module.core.databases.utils.Database

class AxolotlDatabase(db: android.database.sqlite.SQLiteDatabase) : Database(db) {

    fun getSession(recipientId: String, deviceId: Int): Cursor {
        return rawQuery(
            "SELECT * FROM sessions WHERE recipient_id = ? AND device_id = ?",
            arrayOf(recipientId, deviceId.toString())
        )
    }

    fun getPreKeys(): Cursor {
        return rawQuery("SELECT * FROM prekeys", null)
    }

    fun getSignedPreKey(): Cursor {
        return rawQuery("SELECT * FROM signed_prekeys", null)
    }
}