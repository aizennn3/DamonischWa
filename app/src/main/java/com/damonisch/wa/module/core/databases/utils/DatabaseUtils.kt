package com.damonisch.wa.module.core.databases.utils

import android.database.Cursor

object DatabaseUtils {

    fun Cursor.getString(columnName: String): String? {
        val index = getColumnIndex(columnName)
        return if (index != -1) getString(index) else null
    }

    fun Cursor.getInt(columnName: String): Int {
        val index = getColumnIndex(columnName)
        return if (index != -1) getInt(index) else -1
    }

    fun Cursor.getLong(columnName: String): Long {
        val index = getColumnIndex(columnName)
        return if (index != -1) getLong(index) else -1L
    }

    fun Cursor.getBlob(columnName: String): ByteArray? {
        val index = getColumnIndex(columnName)
        return if (index != -1) getBlob(index) else null
    }
}