package com.damonisch.wa.module.hooks.core

import com.damonisch.wa.module.core.WppCallback
import de.robv.android.xposed.XposedBridge

abstract class HooksBase : WppCallback {

    protected var classLoader: ClassLoader? = null

    override fun invoke(classLoader: ClassLoader) {
        this.classLoader = classLoader
        try {
            XposedBridge.log("WaRevamp: Loading hook ${this::class.java.simpleName}")
            executeHook()
            XposedBridge.log("WaRevamp: Hook ${this::class.java.simpleName} loaded successfully")

        } catch (error: Throwable) {
            onError(error)
            XposedBridge.log("WaRevamp: Hook ${this::class.java.simpleName} error: ${error.message}")
        }
    }

    abstract fun executeHook()

    override fun onError(error: Throwable) {
        // Log específico do hook
    }

    protected fun findClass(className: String): Class<*> {
        return Class.forName(className, false, classLoader)
    }

    protected fun findWppClass(className: String): Class<*> {
        return Class.forName("com.whatsapp.$className", false, classLoader)
    }
}