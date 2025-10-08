package com.damonisch.wa.broadcast

object Events {

    // Eventos do Módulo
    const val MODULE_STARTED = "com.damonisch.wa.MODULE_STARTED"
    const val MODULE_STOPPED = "com.damonisch.wa.MODULE_STOPPED"
    const val MODULE_ERROR = "com.damonisch.wa.MODULE_ERROR"

    // Eventos do WhatsApp
    const val WHATSAPP_STARTED = "com.damonisch.wa.WHATSAPP_STARTED"
    const val WHATSAPP_STOPPED = "com.damonisch.wa.WHATSAPP_STOPPED"
    const val WHATSAPP_MESSAGE_RECEIVED = "com.damonisch.wa.MESSAGE_RECEIVED"
    const val WHATSAPP_MESSAGE_SENT = "com.damonisch.wa.MESSAGE_SENT"

    // Eventos Customizados
    const val HOOK_EXECUTED = "com.damonisch.wa.HOOK_EXECUTED"
    const val HOOK_ERROR = "com.damonisch.wa.HOOK_ERROR"
    const val SETTINGS_CHANGED = "com.damonisch.wa.SETTINGS_CHANGED"

    // Chaves para extras
    const val EXTRA_HOOK_NAME = "hook_name"
    const val EXTRA_ERROR_MESSAGE = "error_message"
    const val EXTRA_TIMESTAMP = "timestamp"
    const val EXTRA_SETTING_KEY = "setting_key"
    const val EXTRA_SETTING_VALUE = "setting_value"
}