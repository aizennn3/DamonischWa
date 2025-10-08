package com.damonisch.wa.module.core.databases

import android.database.Cursor
import com.damonisch.wa.module.core.databases.utils.Database

class StickerDatabase(db: android.database.sqlite.SQLiteDatabase) : Database(db) {

    fun getStickerPacks(): Cursor {
        return rawQuery("SELECT * FROM sticker_packs", null)
    }

    fun getStickersByPack(packId: String): Cursor {
        return rawQuery(
            "SELECT * FROM stickers WHERE pack_id = ?",
            arrayOf(packId)
        )
    }

    fun getStickerById(stickerId: String): Cursor {
        return rawQuery(
            "SELECT * FROM stickers WHERE _id = ?",
            arrayOf(stickerId)
        )
    }
}