package com.damonisch.wa.module.references

import android.content.res.XResources
import de.robv.android.xposed.callbacks.XC_InitPackageResources

class ModuleResources(private val resparam: XC_InitPackageResources.InitPackageResourcesParam) {

    fun getString(resourceName: String): String? {
        return try {
            val resId = resparam.res.getIdentifier(resourceName, "string", "com.whatsapp")
            resparam.res.getString(resId)
        } catch (e: Exception) {
            null
        }
    }

    fun getColor(resourceName: String): Int {
        return try {
            val resId = resparam.res.getIdentifier(resourceName, "color", "com.whatsapp")
            resparam.res.getColor(resId, null)
        } catch (e: Exception) {
            -1
        }
    }

    fun setReplacement(resourceName: String, replacement: Any) {
        try {
            val resId = resparam.res.getIdentifier(resourceName, "string", "com.whatsapp")
            if (resId != 0) {
                // Correção: usar setReplacement no resparam.res
                when (replacement) {
                    is String -> resparam.res.setReplacement("com.whatsapp", "string", resourceName, replacement)
                    is Int -> resparam.res.setReplacement("com.whatsapp", "string", resourceName, replacement)
                    else -> {
                        // Para outros tipos, tentar converter para string
                        resparam.res.setReplacement("com.whatsapp", "string", resourceName, replacement.toString())
                    }
                }
            }
        } catch (e: Exception) {
            // Ignore
        }
    }

    // Método alternativo para setar replacements
    fun setReplacement(resourceType: String, resourceName: String, replacement: Any) {
        try {
            when (replacement) {
                is String -> resparam.res.setReplacement("com.whatsapp", resourceType, resourceName, replacement)
                is Int -> resparam.res.setReplacement("com.whatsapp", resourceType, resourceName, replacement)
                else -> {
                    resparam.res.setReplacement("com.whatsapp", resourceType, resourceName, replacement.toString())
                }
            }
        } catch (e: Exception) {
            // Ignore
        }
    }

    // Método para hook de recursos
    fun hookResources() {
        // Exemplo de hooks de recursos
        try {
            // Hook para strings
            // resparam.res.setReplacement("com.whatsapp", "string", "app_name", "WhatsApp Mod")

            // Hook para cores
            // resparam.res.setReplacement("com.whatsapp", "color", "primary_color", 0xFF00FF00.toInt())

        } catch (e: Exception) {
            // Ignore
        }
    }
}