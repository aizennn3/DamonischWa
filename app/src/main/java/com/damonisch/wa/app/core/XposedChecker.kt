package com.damonisch.wa.app.core

import android.content.Context
import android.content.SharedPreferences

class XposedChecker(private val context: Context) {

    companion object {
        private const val PREFS_NAME = "xposed_checker_prefs"
        private const val KEY_XPOSED_ENABLED = "xposed_enabled"
        private const val KEY_LAST_CHECK = "last_check"
    }

    private val prefs: SharedPreferences by lazy {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun isXposedActive(): Boolean {
        // Verificação em cache para performance
        if (prefs.getBoolean(KEY_XPOSED_ENABLED, false)) {
            return true
        }

        return checkXposedStatus()
    }

    private fun checkXposedStatus(): Boolean {
        return try {
            // Tentar acessar classes do Xposed
            Class.forName("de.robv.android.xposed.XposedBridge")
            Class.forName("de.robv.android.xposed.XC_MethodHook")

            // Se chegou aqui, Xposed está ativo
            prefs.edit().putBoolean(KEY_XPOSED_ENABLED, true).apply()
            true
        } catch (e: ClassNotFoundException) {
            prefs.edit().putBoolean(KEY_XPOSED_ENABLED, false).apply()
            false
        }
    }

    fun resetXposedStatus() {
        prefs.edit().remove(KEY_XPOSED_ENABLED).apply()
    }

    fun getXposedStatus(): String {
        return if (isXposedActive()) "Xposed Framework está ativo"
        else "Xposed Framework não está ativo"
    }
}