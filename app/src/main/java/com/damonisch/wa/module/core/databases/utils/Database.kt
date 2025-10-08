package com.damonisch.wa.module.core.databases.utils

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

abstract class Database(private val db: SQLiteDatabase) {

    fun rawQuery(sql: String, selectionArgs: Array<String>? = null): Cursor {
        return db.rawQuery(sql, selectionArgs)
    }

    fun execSQL(sql: String) {
        db.execSQL(sql)
    }

    // Adicionar overload para execSQL com argumentos
    fun execSQL(sql: String, bindArgs: Array<Any>) {
        db.execSQL(sql, bindArgs)
    }

    fun close() {
        db.close()
    }

    fun isOpen(): Boolean = db.isOpen
}