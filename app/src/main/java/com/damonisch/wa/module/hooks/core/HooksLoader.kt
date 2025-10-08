package com.damonisch.wa.module.hooks.core

import com.damonisch.wa.module.core.WppCallback

class HooksLoader(private val hooks: List<WppCallback>) : WppCallback {

    override fun invoke(classLoader: ClassLoader) {
        hooks.forEach { hook ->
            try {
                hook.invoke(classLoader)
            } catch (error: Throwable) {
                hook.onError(error)
            }
        }
    }

    override fun onError(error: Throwable) {
        // Log de erro do loader
    }
}