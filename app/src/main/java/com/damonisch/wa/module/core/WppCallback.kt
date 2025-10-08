package com.damonisch.wa.module.core

interface WppCallback {
    fun invoke(classLoader: ClassLoader)

    fun onError(error: Throwable) {
        // Log de erro padrão
    }
}