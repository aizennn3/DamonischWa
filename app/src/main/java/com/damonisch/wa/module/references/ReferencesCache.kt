package com.damonisch.wa.module.references

import java.util.concurrent.ConcurrentHashMap

object ReferencesCache {
    private val classCache = ConcurrentHashMap<String, Class<*>>()
    private val methodCache = ConcurrentHashMap<String, java.lang.reflect.Method>()
    private val fieldCache = ConcurrentHashMap<String, java.lang.reflect.Field>()

    fun getClass(className: String, classLoader: ClassLoader): Class<*> {
        return classCache.getOrPut(className) {
            Class.forName(className, false, classLoader)
        }
    }

    fun getMethod(cacheKey: String, block: () -> java.lang.reflect.Method): java.lang.reflect.Method {
        return methodCache.getOrPut(cacheKey) { block() }
    }

    fun getField(cacheKey: String, block: () -> java.lang.reflect.Field): java.lang.reflect.Field {
        return fieldCache.getOrPut(cacheKey) { block() }
    }

    fun clear() {
        classCache.clear()
        methodCache.clear()
        fieldCache.clear()
    }

    fun clearClass(className: String) {
        classCache.remove(className)
        methodCache.keys.filter { it.startsWith(className) }.forEach { methodCache.remove(it) }
        fieldCache.keys.filter { it.startsWith(className) }.forEach { fieldCache.remove(it) }
    }
}