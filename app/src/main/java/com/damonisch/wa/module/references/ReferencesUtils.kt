package com.damonisch.wa.module.references

import de.robv.android.xposed.XposedHelpers

object ReferencesUtils {

    fun findClass(className: String, classLoader: ClassLoader): Class<*> {
        return ReferencesCache.getClass(className, classLoader)
    }

    fun findMethod(
        clazz: Class<*>,
        methodName: String,
        vararg parameterTypes: Class<*>
    ): java.lang.reflect.Method {
        val cacheKey = "${clazz.name}.$methodName.${parameterTypes.joinToString(",") { it.name }}"

        return ReferencesCache.getMethod(cacheKey) {
            clazz.getDeclaredMethod(methodName, *parameterTypes).apply {
                isAccessible = true
            }
        }
    }

    fun findField(clazz: Class<*>, fieldName: String): java.lang.reflect.Field {
        val cacheKey = "${clazz.name}.$fieldName"

        return ReferencesCache.getField(cacheKey) {
            clazz.getDeclaredField(fieldName).apply {
                isAccessible = true
            }
        }
    }

    fun getObjectField(obj: Any, fieldName: String): Any? {
        return XposedHelpers.getObjectField(obj, fieldName)
    }

    fun setObjectField(obj: Any, fieldName: String, value: Any?) {
        XposedHelpers.setObjectField(obj, fieldName, value)
    }
}